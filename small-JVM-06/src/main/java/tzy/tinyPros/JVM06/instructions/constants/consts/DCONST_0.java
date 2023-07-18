package tzy.tinyPros.JVM06.instructions.constants.consts;

import tzy.tinyPros.JVM06.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM06.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/15 23:51
 **/
public class DCONST_0 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushDouble(0);
    }
}