package tzy.tinyPros.JVM09.instructions.reference;

import tzy.tinyPros.JVM09.instructions.base.Index16Instruction;
import tzy.tinyPros.JVM09.instructions.base.MethodLogicInvoke;
import tzy.tinyPros.JVM09.rtda.heap.constantpool.MethodRef;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Klass;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Method;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.MethodLookup;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Object;
import tzy.tinyPros.JVM09.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/22 0:18
 * <p>
 * invoke_special 指令用来调用无须动态绑定的实例方法（构造函数、私有方法、通过super关键字调用的超类方法）
 * <p>
 * 编译器是十分聪明的，它知道不同情况该使用什么指令，所以JVM要做的是从会出现这个指令的不同情况中剥离出真实的情况然后执行
 **/
public class INVOKE_SPECIAL extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        Klass visitor = frame.getMethod().clazz;
        MethodRef mr = (MethodRef) visitor.runTimeConstantPool.constants[this.getIdx()];
        // 声明该方法时候使用的类
        Klass holder = mr.getClazz();
        Method method = mr.resolvedMethod();

        // 是否是调用构造方法
        if ("<init>".equals(method.name) && method.clazz != holder) {
            throw new NoSuchMethodError();
        }

        // 不能是静态方法
        if (method.isStatic()) {
            throw new IncompatibleClassChangeError();
        }

        // 相当于获取原隐藏传入的 this 参数，其实就是调用该方法的对象实例指针
        Object ref = frame.getOperandStack().getRefFromTop(method.argSlotCnt - 1);
        if (ref == null) {
            throw new NullPointerException();
        }

        // TODO: 看不懂以后再看 0_0
        // 此判断保证protected方法只能被声明该方法的类或子类调用
        if (method.isProtected() &&
                visitor.isExtendFrom(method.clazz) &&
                !visitor.getPackageName().equals(method.clazz.getPackageName()) &&
                ref.clazz != visitor &&
                !ref.clazz.isExtendFrom(visitor)) {
            throw new IllegalAccessError();
        }

        // 如果是调用的超类中的函数而不是构造函数，则需要额外的查找过程
        Method invokedMethod = method;
        if (visitor.isSuper() &&
                visitor.isExtendFrom(holder) &&
                !method.name.equals("<init>")) {
            invokedMethod = MethodLookup.loopupMethodInClass(visitor.superClass, mr.name, mr.descriptor);
        }

        if (invokedMethod == null && invokedMethod.isAbstract()) {
            throw invokedMethod == null ? new NoSuchMethodError() : new AbstractMethodError();
        }

        MethodLogicInvoke.invokeMethod(frame, invokedMethod);
    }
}
