package tzy.tinyPros.JVM05.instructions.constants.consts;

import tzy.tinyPros.JVM05.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM05.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/15 23:52
 **/
public class DCONST_1 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushDouble(1);
    }
}