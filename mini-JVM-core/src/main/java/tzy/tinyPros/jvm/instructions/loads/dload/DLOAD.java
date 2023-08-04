package tzy.tinyPros.jvm.instructions.loads.dload;

import tzy.tinyPros.jvm.instructions.base.Index8Instruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 2:10
 **/
public class DLOAD extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushDouble(frame.getLocalVarsTable().getDouble(this.getIdx()));
    }
}
