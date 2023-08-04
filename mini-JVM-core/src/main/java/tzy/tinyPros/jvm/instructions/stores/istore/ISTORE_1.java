package tzy.tinyPros.jvm.instructions.stores.istore;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 16:33
 **/
public class ISTORE_1 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        this._istore(frame,1);
    }
}
