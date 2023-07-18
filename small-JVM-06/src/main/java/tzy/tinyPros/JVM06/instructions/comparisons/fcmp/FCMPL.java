package tzy.tinyPros.JVM06.instructions.comparisons.fcmp;

import tzy.tinyPros.JVM06.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM06.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/17 14:43
 **/
public class FCMPL extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        this._fcmp(frame,false);
    }
}
