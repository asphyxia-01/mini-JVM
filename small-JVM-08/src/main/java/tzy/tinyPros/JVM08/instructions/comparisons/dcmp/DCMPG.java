package tzy.tinyPros.JVM08.instructions.comparisons.dcmp;

import tzy.tinyPros.JVM08.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM08.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/17 14:40
 **/
public class DCMPG extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame){
        this._dcmp(frame,true);
    }
}
