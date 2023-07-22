package tzy.tinyPros.JVM08.instructions.stores.lstore;

import tzy.tinyPros.JVM08.instructions.base.Index8Instruction;
import tzy.tinyPros.JVM08.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 16:34
 **/
public class LSTORE extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        this._lstore(frame,this.getIdx());
    }
}
