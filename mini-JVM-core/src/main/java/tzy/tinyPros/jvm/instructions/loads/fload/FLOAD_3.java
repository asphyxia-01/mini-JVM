package tzy.tinyPros.jvm.instructions.loads.fload;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 2:15
 **/
public class FLOAD_3 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushFloat(frame.getLocalVarsTable().getFloat(3));
    }
}
