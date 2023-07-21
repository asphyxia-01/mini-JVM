package tzy.tinyPros.JVM07.rtda.heap.methodarea;

import tzy.tinyPros.JVM07.classfile.ClassFile;
import tzy.tinyPros.JVM07.rtda.heap.constantpool.AccessFlags;
import tzy.tinyPros.JVM07.rtda.heap.constantpool.RunTimeConstantPool;
import tzy.tinyPros.JVM07.rtda.heap.ClassLoader;

/**
 * @author TPureZY
 * @since 2023/7/18 16:27
 **/
public class Class {

    public final int accessFlags;
    public final String name;
    public final String superClassName;
    public final String[] interfaceNames;
    public final RunTimeConstantPool runTimeConstantPool;
    public final Field[] fields;
    public final Method[] methods;
    /**
     * 存放类加载器指针
     */
    public ClassLoader loader;
    public Class superClass;
    public Class[] interfaces;
    /**
     * 实例变量占用的空间大小
     */
    public int instanceSlotCount;
    /**
     * 类变量占用的空间大小
     */
    public int staticSlotCount;
    /**
     * 类变量会在类加载时候进行初始化，和类是直接绑定关系，不需要 new 实例化
     */
    public Slots staticVars;

    public Class(ClassFile file) {
        this.accessFlags = file.getAccessFlags();
        this.name = file.getClassName();
        this.superClassName = file.getSuperClassName();
        this.interfaceNames = file.getInterfaceNames();
        this.runTimeConstantPool = new RunTimeConstantPool(this, file.getConstantPool());
        this.fields = Field.newFields(this, file.getFields());
        this.methods = Method.newMethods(this, file.getMethods());
    }

    public boolean isPublic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_PUBLIC);
    }

    public boolean isFinal() {
        return 0 != (this.accessFlags & AccessFlags.ACC_FINAL);
    }

    public boolean isSuper() {
        return 0 != (this.accessFlags & AccessFlags.ACC_SUPER);
    }

    public boolean isInterface() {
        return 0 != (this.accessFlags & AccessFlags.ACC_INTERFACE);
    }

    public boolean isAbstract() {
        return 0 != (this.accessFlags & AccessFlags.ACC_ABSTRACT);
    }

    public boolean isSynthetic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_SYNTHETIC);
    }

    public boolean isAnnotation() {
        return 0 != (this.accessFlags & AccessFlags.ACC_ANNOTATION);
    }

    public boolean isEnum() {
        return 0 != (this.accessFlags & AccessFlags.ACC_ENUM);
    }

    public Slots getStaticVars() {
        return this.staticVars;
    }

    /**
     * 判断其他类能否访问这个类
     * <p>
     * 1.当前类是public的；2.同包（default）
     */
    public boolean isAccessibleTo(Class other) {
        return this.isPublic() || this.getPackageName().equals(other.getPackageName());
    }

    public String getPackageName() {
        int i = this.name.lastIndexOf("/");
        if (i >= 0) {
            return this.name.substring(0, i);
        }
        // 说明是默认包
        return "";
    }

    public Method getStaticMethod(String name, String descriptor) {
        for (Method method : this.methods) {
            if (method.name.equals(name) && method.descriptor.equals(descriptor)) {
                return method;
            }
        }
        return null;
    }

    public Method getMainMethod() {
        return this.getStaticMethod("main", "([Ljava/lang/String;)V");
    }

    public Object newObject() {
        // 通过Class类生成对象
        return new Object(this);
    }

    /**
     * 是否是other的子类
     * <p>
     * 由于是单继承，一个Class只能有一个SuperClass，但是SuperClass也还可以有个SuperClass，所以需要不断向上比对
     */
    public boolean isExtendFrom(Class other) {
        for (Class c = this.superClass; c != null; c = c.superClass) {
            if (c == other) {
                return true;
            }
        }
        return false;
    }

    /**
     * other是否是当前Class的父接口之一
     */
    public boolean isImplementFrom(Class other) {
        for (Class c = this; c != null; c = c.superClass) {
            if (c.isSubImplementFrom(other)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSubImplementFrom(Class other) {
        for (Class item : this.interfaces) {
            if (item == other || item.isSubImplementFrom(other)) {
                return true;
            }
        }
        return false;
    }

}
