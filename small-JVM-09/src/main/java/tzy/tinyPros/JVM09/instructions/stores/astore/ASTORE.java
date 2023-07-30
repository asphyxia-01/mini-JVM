package tzy.tinyPros.JVM09.instructions.stores.astore;

import tzy.tinyPros.JVM09.instructions.base.Index8Instruction;
import tzy.tinyPros.JVM09.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 16:22
 **/
public class ASTORE extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        this._astore(frame,this.getIdx());
    }
}
