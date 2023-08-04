package tzy.tinyPros.jvm.instructions.stores.fstore;

import tzy.tinyPros.jvm.instructions.base.Index8Instruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;

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
