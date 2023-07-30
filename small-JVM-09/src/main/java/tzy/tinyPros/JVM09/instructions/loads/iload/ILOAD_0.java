package tzy.tinyPros.JVM09.instructions.loads.iload;

import tzy.tinyPros.JVM09.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM09.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 2:17
 **/
public class ILOAD_0 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(frame.getLocalVarsTable().getInt(0));
    }
}
