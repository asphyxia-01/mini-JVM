package tzy.tinyPros.JVM09.instructions.control.rtn;

import tzy.tinyPros.JVM09.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM09.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/22 15:23
 **/
public class FRETURN extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Frame lastFrame
                =
                frame
                        .getThread()
                        .popFrame();
        frame
                .getThread()
                .topFrame()
                .getOperandStack()
                .pushFloat(
                        lastFrame
                                .getOperandStack()
                                .popFloat()
                );
    }
}
