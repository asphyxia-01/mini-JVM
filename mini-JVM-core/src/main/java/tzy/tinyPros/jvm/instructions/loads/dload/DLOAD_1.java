package tzy.tinyPros.jvm.instructions.loads.dload;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 2:12
 **/
public class DLOAD_1 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushDouble(frame.getLocalVarsTable().getDouble(1));
    }
}
