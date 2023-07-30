package tzy.tinyPros.JVM09.instructions.comparisons.fcmp;

import tzy.tinyPros.JVM09.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM09.rtda.thread.Frame;

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
