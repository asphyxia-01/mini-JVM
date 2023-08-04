package tzy.tinyPros.jvm.instructions.stores.astore;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 16:24
 **/
public class ASTORE_2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        this._astore(frame,2);
    }
}
