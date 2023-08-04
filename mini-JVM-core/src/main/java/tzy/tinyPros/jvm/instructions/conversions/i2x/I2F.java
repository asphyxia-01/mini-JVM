package tzy.tinyPros.jvm.instructions.conversions.i2x;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 14:20
 **/
public class I2F extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int i = stack.popInt();
        // Java中这样的转换是值尽量不变的，但是底层比特位改变，浮点数是IEEE754标准
        // int、long向float可以直接转，但是精度会丢失，不需要强转是因为在float能表示的数据的范围内，并没有溢出
        stack.pushFloat(i);
    }
}
