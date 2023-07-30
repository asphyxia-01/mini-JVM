package tzy.tinyPros.JVM09.instructions.reference;

import tzy.tinyPros.JVM09.instructions.base.ByteReader;
import tzy.tinyPros.JVM09.instructions.base.Instruction;
import tzy.tinyPros.JVM09.instructions.base.MethodLogicInvoke;
import tzy.tinyPros.JVM09.rtda.heap.constantpool.MethodRef;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Klass;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Method;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.MethodLookup;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Object;
import tzy.tinyPros.JVM09.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/22 0:16
 * <p>
 * invoke_interface 指令调用所属接口的方法
 * <p>
 * 对于这个指令JVM有所优化使用itable，每个接口都有itable，用于记录实现了该接口的类的偏移量（地址），区别于vtable，itable是接口特有的
 * <p>
 * 注意：invoke_interface 指令后面跟着的是4个字节三个操作数，而不是同其他invoke方法一样的2个字节的方法引用的索引；方法引用的索引(2byte)，方法传入的参数个数(1byte，效果同argSlotCnt一样可以被计算出来，存在仅仅是历史原因)，保留值固定为0(1byte，留给不同JVM使用，目的是为了向后兼容)
 **/
public class INVOKE_INTERFACE implements Instruction {

    private int idx;

    @Override
    public void fetchOperands(ByteReader br) {
        // 方法引用索引
        this.idx = br.readShort();
        // 参数个数（没用）
        br.readByte();
        // 保留值
        br.readByte();
    }

    @Override
    public void execute(Frame frame) {
        Klass visitor = frame.getMethod().clazz;
        MethodRef mr = (MethodRef) visitor.runTimeConstantPool.constants[this.idx];
        Klass citeClass = mr.getClazz();
        Method method = mr.resolvedMethod();
        if (method.isStatic() || method.isPrivate()) {
            throw new IncompatibleClassChangeError();
        }
        Object ref = frame.getOperandStack().getRefFromTop(method.argSlotCnt - 1);
        if (ref == null) {
            throw new NullPointerException();
        }
        if (!ref.clazz.isImplementFrom(citeClass)) {
            throw new IncompatibleClassChangeError();
        }
        // 因为动态绑定，此处找到要调用的真实方法
        Method invokedMethod = MethodLookup.loopupMethodInClass(ref.clazz, mr.name, mr.descriptor);
        if (invokedMethod == null && invokedMethod.isAbstract()) {
            throw invokedMethod == null ? new NoSuchMethodError() : new AbstractMethodError();
        }
        if (!invokedMethod.isPublic()) {
            throw new IllegalAccessError();
        }

        MethodLogicInvoke.invokeMethod(frame, invokedMethod);

    }
}
