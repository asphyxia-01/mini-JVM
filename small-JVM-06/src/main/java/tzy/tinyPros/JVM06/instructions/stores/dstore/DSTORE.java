package tzy.tinyPros.JVM06.instructions.stores.dstore;

import tzy.tinyPros.JVM06.instructions.base.Index8Instruction;
import tzy.tinyPros.JVM06.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 16:25
 **/
public class DSTORE extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        this._dstore(frame,this.getIdx());
    }
}
