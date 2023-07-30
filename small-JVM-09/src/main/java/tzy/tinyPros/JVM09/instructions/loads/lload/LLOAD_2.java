package tzy.tinyPros.JVM09.instructions.loads.lload;

import tzy.tinyPros.JVM09.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM09.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 2:19
 **/
public class LLOAD_2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushLong(frame.getLocalVarsTable().getLong(2));
    }
}
