package tzy.tinyPros.jvm.instructions.comparisons.dcmp;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/17 14:42
 **/
public class DCMPL extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        this._dcmp(frame,false);
    }
}
