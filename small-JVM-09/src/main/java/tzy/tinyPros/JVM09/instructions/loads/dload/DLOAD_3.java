package tzy.tinyPros.JVM09.instructions.loads.dload;

import tzy.tinyPros.JVM09.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM09.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 2:12
 **/
public class DLOAD_3 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushDouble(frame.getLocalVarsTable().getDouble(3));
    }
}
