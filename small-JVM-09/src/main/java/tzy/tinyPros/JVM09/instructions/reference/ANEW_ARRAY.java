package tzy.tinyPros.JVM09.instructions.reference;

import tzy.tinyPros.JVM09.instructions.base.Index16Instruction;
import tzy.tinyPros.JVM09.rtda.heap.constantpool.ClassRef;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Klass;
import tzy.tinyPros.JVM09.rtda.thread.Frame;

/**
 * 创建引用类型数组
 * <p>
 * 两个操作数：运行时常量池索引（数组元素的类Ref，2byte，来自指令），指向一个类符号引用，借此可以得到对应的Class；数组长度（来自操作数栈）
 *
 * @author TPureZY
 * @since 2023/7/26 21:11
 **/
public class ANEW_ARRAY extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        int cnt = frame.getOperandStack().popInt();
        if (cnt < 0) {
            throw new NegativeArraySizeException();
        }
        // 常量池中的是组成数组的类描述符，还需转换为引用数组的描述符
        Klass arrClass = ((ClassRef) frame.getMethod().clazz.runTimeConstantPool.constants[this.getIdx()]).getClazz().transformAndGetArrayClass();
        frame.getOperandStack().pushRef(arrClass.newArray(cnt));
    }
}
