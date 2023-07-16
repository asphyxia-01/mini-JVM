package tzy.tinyPros.JVM05.instructions.stores.fstore;

import tzy.tinyPros.JVM05.instructions.base.Index8Instruction;
import tzy.tinyPros.JVM05.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 16:28
 **/
public class FSTORE extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        this._fstore(frame,this.getIdx());
    }
}
