package tzy.tinyPros.jvm.instructions.stores.fstore;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 16:29
 **/
public class FSTORE_2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        this._fstore(frame,2);
    }
}
