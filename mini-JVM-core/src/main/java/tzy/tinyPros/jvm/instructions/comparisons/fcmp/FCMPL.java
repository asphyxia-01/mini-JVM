package tzy.tinyPros.jvm.instructions.comparisons.fcmp;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;

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
