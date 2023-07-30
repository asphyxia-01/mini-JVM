package tzy.tinyPros.JVM09.instructions.reference;

import tzy.tinyPros.JVM09.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Object;
import tzy.tinyPros.JVM09.rtda.thread.Frame;

/**
 * 获取数组的长度
 *
 * @author TPureZY
 * @since 2023/7/26 21:45
 **/
public class ARRAY_LENGTH extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Object arrObj = frame.getOperandStack().popRef();
        if (arrObj == null) {
            throw new NullPointerException();
        }
        frame.getOperandStack().pushInt(arrObj.arrayLength());
    }
}
