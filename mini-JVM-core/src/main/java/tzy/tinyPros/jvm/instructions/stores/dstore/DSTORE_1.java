package tzy.tinyPros.jvm.instructions.stores.dstore;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 16:26
 **/
public class DSTORE_1 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        this._dstore(frame,1);
    }
}
