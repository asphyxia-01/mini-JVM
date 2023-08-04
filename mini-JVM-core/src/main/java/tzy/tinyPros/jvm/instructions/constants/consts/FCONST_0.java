package tzy.tinyPros.jvm.instructions.constants.consts;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/15 23:52
 **/
public class FCONST_0 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushFloat(0);
    }
}
