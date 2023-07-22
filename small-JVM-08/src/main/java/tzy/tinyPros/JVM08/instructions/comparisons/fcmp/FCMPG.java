package tzy.tinyPros.JVM08.instructions.comparisons.fcmp;

import tzy.tinyPros.JVM08.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM08.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/17 14:43
 **/
public class FCMPG extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        this._fcmp(frame,true);
    }
}
