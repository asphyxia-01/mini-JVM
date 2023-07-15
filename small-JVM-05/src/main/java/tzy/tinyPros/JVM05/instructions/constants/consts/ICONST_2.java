package tzy.tinyPros.JVM05.instructions.constants.consts;

import tzy.tinyPros.JVM05.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM05.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/15 23:54
 **/
public class ICONST_2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(2);
    }
}