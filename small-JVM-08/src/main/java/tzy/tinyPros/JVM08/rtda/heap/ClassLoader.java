package tzy.tinyPros.JVM08.rtda.heap;

import tzy.tinyPros.JVM08.classfile.ClassFile;
import tzy.tinyPros.JVM08.classpath.Classpath;
import tzy.tinyPros.JVM08.rtda.heap.constantpool.AccessFlags;
import tzy.tinyPros.JVM08.rtda.heap.constantpool.RunTimeConstantPool;
import tzy.tinyPros.JVM08.rtda.heap.methodarea.Class;
import tzy.tinyPros.JVM08.rtda.heap.methodarea.Field;
import tzy.tinyPros.JVM08.rtda.heap.methodarea.Slots;

import java.util.HashMap;

/**
 * @author TPureZY
 * @since 2023/7/18 16:19
 * <blockquote><pre>
 *     class names:
 *     - primitive types: boolean, byte, int ...
 *     - primitive arrays: [Z, [B, [I ...
 *     - non-array classes: java/lang/Object ...
 *     - array classes: [Ljava/lang/Object; ...
 * </pre></blockquote>
 **/
public class ClassLoader {

    /**
     * Classpath中包含 启动类、拓展类、用户类，主要用来从外存中读入class文件的字节码数据
     * <p>
     * byte[] data
     */
    private Classpath cp;

    /**
     * 相当于方法区的具体实现，用来存储已被加载的类数据（字段、方法、方法字节码、运行时常量池）
     */
    private HashMap<String, Class> classMap;

    public ClassLoader(Classpath cp) {
        this.cp = cp;
        this.classMap = new HashMap<>();
    }

    public Class loadClass(String className) {
        if (this.classMap.containsKey(className)) {
            return this.classMap.get(className);
        }
        Class clazz = null;
        // 数组类描述符以'['开头
        if (className.charAt(0) == '[') {
            clazz = this.loadArrayClass(className);
        } else {
            try {
                clazz = this.loadNonArrayClass(className);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (clazz != null) {
            this.classMap.put(clazz.name, clazz);
        }
        return clazz;
    }

    /**
     * 加载普通类信息，不负责加载数组类（数组类是JVM中第一类对象，是由JVM在运行期间生成的，不是来自于 .class 文件）
     * <p>
     * 加载 -> 链接 (验证 -> 准备 -> 解析) -> 初始化(初始化只有实例化 new 时候才会执行)
     */
    private Class loadNonArrayClass(String className) throws Exception {
        byte[] data = this.cp.readClass(className);
        if (data == null) {
            throw new ClassNotFoundException("not find class: " + className);
        }
        Class clazz = this.defineClass(data);
        link(clazz);
        return clazz;
    }

    private Class loadArrayClass(String className) {
        return new Class(
                AccessFlags.ACC_PUBLIC,
                className,
                this,
                true,
                this.loadClass("java/lang/Object"),
                new Class[]{
                        this.loadClass("java/lang/Cloneable"),
                        this.loadClass("java/io/Serializable")
                }
        );
    }

    /**
     * parseClass()将字节码转为Class结构体
     * <p>
     * 上一步中Class结构体中存放的SuperClass和Interfaces都是符号引用
     * <p>
     * 调用resolveSuperClass()和resolveInterfaces()方法解析这些符号引用同时加载这些类
     */
    private Class defineClass(byte[] data) throws Exception {
        // 转为Class结构体
        Class clazz = this.parseClass(data);
        // 保存一个类加载器的指针
        clazz.loader = this;
        // 解析超类（也就是父类）
        this.resolveSuperClass(clazz);
        // 解析接口表
        this.resolveInterfaces(clazz);
        return clazz;
    }

    private Class parseClass(byte[] data) {
        ClassFile classFile = new ClassFile(data);
        return new Class(classFile);
    }

    /**
     * 解析超类的符号引用，然后加载对应的超类
     */
    private void resolveSuperClass(Class clazz) {
        // 只有java/lang/Object类没有超类，所以作为终止判定条件
        if (!clazz.name.equals("java/lang/Object")) {
            // 加载clazz的超类，除了java/lang/Object类以外其他任何类一定有超类s
            clazz.superClass = clazz.loader.loadClass(clazz.superClassName);
        }
    }

    /**
     * 解析接口的符号引用，然后加载对应的接口
     */
    private void resolveInterfaces(Class clazz) {
        int length = clazz.interfaceNames.length;
        if (length > 0) {
            clazz.interfaces = new Class[length];
            for (int i = 0; i < length; i++) {
                clazz.interfaces[i] = clazz.loader.loadClass(clazz.interfaceNames[i]);
            }
        }
    }

    /**
     * 链接 (验证 -> 准备 -> 解析)
     * <p>
     * 解析和上面对超类、接口的解析一样都是由符号引用加载完整的信息；在这里就是构建字段、方法等结构体。解析就是符号引用转为直接引用的过程，解析动作主要针对类、接口、字段、类方法、接口方法分别对应常量池中不同的 Constant_{?}Ref_Info
     */
    private void link(Class clazz) {
        // 验证
        verify(clazz);
        // 准备
        prepare(clazz);
    }

    /**
     * JVM规范要求执行类的任何代码之前，都需要进行严格的验证
     * <p>
     * JVM规范的4.10节做了对验证算法做了详细介绍，这里暂时不做实现
     */
    private void verify(Class clazz) {
        // TODO：暂时不对类的验证算法做实现
    }

    private void prepare(Class clazz) {
        // 计算实例变量的个数并编号便于管理
        calcInstanceFieldSlotIds(clazz);
        // 计算类变量的个数并编号便于管理
        calcStaticFieldSlotIds(clazz);
        // 对于类变量会在 准备阶段 进行初始化，而在编译阶段就会分配空间了
        allocAndInitStaticVars(clazz);
    }

    private void calcInstanceFieldSlotIds(Class clazz) {
        int slotId = 0;
        // 每个当前类的Class只会记录直接属于自己的字段，如果有超类，则子类字段的编号要延后，防止和超类字段编号相冲突
        // 不用考虑接口，因为接口中不能定义实例字段，都是默认 static final 的类字段
        if (clazz.superClass != null) {
            slotId = clazz.superClass.instanceSlotCount;
        }
        for (Field field : clazz.fields) {
            // 提取实例字段
            if (!field.isStatic()) {
                field.slotId = slotId;
                slotId++;
                // long和double占用两个slot
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        // 更新这个Class拥有的实例变量(即字段)的个数
        clazz.instanceSlotCount = slotId;
    }

    private void calcStaticFieldSlotIds(Class clazz) {
        int slotId = 0;
        // 注意静态字段是类变量，直接属于某一具体类的，不会因为继承而归属于子类，不受继承、实现等的影响，每个类独立持有，其他类不会产生干扰
        for (Field field : clazz.fields) {
            // 提取类字段
            if (field.isStatic()) {
                field.slotId = slotId;
                slotId++;
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        clazz.staticSlotCount = slotId;
    }

    /**
     * 分配空间并初始化
     * <p>
     * 但一般而言：类变量(静态变量)在编译时候分配空间，并在运行时候在类的链接阶段的准备阶段中进行初始化
     * <p>
     * 不过如果仅仅被static修饰则默认就是 0(基本类型) 或者 null(引用类型)，不需要多余的初始化动作
     * <p>
     * 但如果是被static和final修饰的则需要显式的初始化操作，部分数据如果是基本类型或者String类型，则在编译时候就可以知道，会存放在 .class 文件的常量池中，取出后赋值放到对应的slots的格子中即可(即初始化的表现)
     */
    private void allocAndInitStaticVars(Class clazz) {
        clazz.staticVars = new Slots(clazz.staticSlotCount);
        for (Field field : clazz.fields) {
            if (field.isStatic() && field.isFinal()) {
                initStaticFinalVar(clazz, field);
            }
        }
    }

    private void initStaticFinalVar(Class clazz, Field field) {
        Slots staticVars = clazz.staticVars;
        // 在常量池中的索引
        int rpId = field.constValueIndex;
        // 在类变量表中的索引
        int slotId = field.slotId;
        RunTimeConstantPool rp = clazz.runTimeConstantPool;
        // 如果存在常量索引(满足是被static和final修饰，同时赋值是基本类型或者String类型的类变量，非前两者的对象实例则是在运行时分配)
        if (rpId > 0) {
            switch (field.descriptor) {
                case "B":
                case "Z":
                case "C":
                case "S":
                case "I":
                    Object val0 = rp.constants[rpId];
                    staticVars.setInt(slotId, (Integer) val0);
                    break;
                case "J":
                    Object val1 = rp.constants[rpId];
                    staticVars.setLong(slotId, (Long) val1);
                    break;
                case "D":
                    Object val2 = rp.constants[rpId];
                    staticVars.setDouble(slotId, (Double) val2);
                    break;
                case "Ljava/lang/String":
                    System.out.println("TODO");
            }
        }
    }

}
