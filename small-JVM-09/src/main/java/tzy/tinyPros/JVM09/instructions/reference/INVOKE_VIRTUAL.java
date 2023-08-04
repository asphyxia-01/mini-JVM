package tzy.tinyPros.JVM09.instructions.reference;

import tzy.tinyPros.JVM09.instructions.base.Index16Instruction;
import tzy.tinyPros.JVM09.instructions.base.MethodLogicInvoke;
import tzy.tinyPros.JVM09.rtda.heap.constantpool.MethodRef;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.*;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Klass;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Object;
import tzy.tinyPros.JVM09.rtda.thread.Frame;
import tzy.tinyPros.JVM09.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/22 0:18
 * <p>
 * invoke_virtual 指令调用普通方法
 * <p>
 * 每个类都有vtable，当有继承关系时候会复制一份父类的vtable然后自己在做更改，旨在加快方法的查找速度（实现动态绑定）
 **/
public class INVOKE_VIRTUAL extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        Klass visitor = frame.getMethod().clazz;
        MethodRef mr = (MethodRef) visitor.runTimeConstantPool.constants[this.getIdx()];
        // 获取调用mr的引用的类型，这个类型一般都是对象变量的类型，因为多态，比如 A obj = new B()，记录还是A，所以后面还要根据隐式传入的 this 参数找到真正要调用的对象的方法，例子中即是B
        Klass citeClass = mr.getClazz();
        // 这个method不一定是最终要调用的method，如上面的例子所示，是A的method，但是动态绑定实际要调用的是B的method
        Method method = mr.resolvedMethod();
        if (method.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        // 得到隐式传入的 this 参数
        Object ref = frame.getOperandStack().getRefFromTop(method.argSlotCnt - 1);
        if (ref == null) {
            // 对println()方法做中止操作，内部实现有synchronized锁机制，此JVM未实现锁操作，接管println()方法的执行逻辑，其对应的栈帧不如栈执行，JVM模拟执行然后返回
            if ("println".equals(mr.name)) {
                _println(frame.getOperandStack(), mr.descriptor);
                return;
            }
            throw new NullPointerException();
        }

        // TODO: 看不懂以后再看 0_0
        if (method.isProtected() &&
                visitor.isExtendFrom(citeClass) &&
                !citeClass.getPackageName().equals(visitor.getPackageName()) &&
                ref.clazz != visitor &&
                !ref.clazz.isExtendFrom(visitor)) {
            throw new IllegalAccessError();
        }

        // 由于动态绑定，这里查找调用的真正方法体
        Method invokedMethod = MethodLookup.lookupMethodInClass(ref.clazz, mr.name, mr.descriptor);
        if (invokedMethod == null && invokedMethod.isAbstract()) {
            throw invokedMethod == null ? new NoSuchMethodError() : new AbstractMethodError();
        }

        MethodLogicInvoke.invokeMethod(frame, invokedMethod);
    }

    private void _println(OperandStack stack, String descriptor) {
        switch (descriptor) {
            case "(Z)V":
                System.out.println(stack.popInt() != 0);
                break;
            case "(C)V":
            case "(I)V":
            case "(B)V":
            case "(S)V":
                System.out.println(stack.popInt());
                break;
            case "(F)V":
                System.out.println(stack.popFloat());
                break;
            case "(J)V":
                System.out.println(stack.popLong());
                break;
            case "(D)V":
                System.out.println(stack.popDouble());
                break;
            case "(Ljava/lang/String;)V":
                System.out.println(
                        StringPool.convertAndGetOriginalUtf8(stack.popRef())
                );
                break;
            default:
                System.out.println(descriptor);
                break;
        }
        stack.popRef();
    }
}