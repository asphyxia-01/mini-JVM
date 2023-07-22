package tzy.tinyPros.JVM08.instructions.control.rtn;

import tzy.tinyPros.JVM08.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM08.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/22 15:27
 **/
public class RETURN extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame
                .getThread()
                .popFrame();
    }
}
