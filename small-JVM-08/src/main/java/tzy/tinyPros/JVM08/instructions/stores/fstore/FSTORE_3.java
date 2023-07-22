package tzy.tinyPros.JVM08.instructions.stores.fstore;

import tzy.tinyPros.JVM08.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM08.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 16:29
 **/
public class FSTORE_3 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        this._fstore(frame,3);
    }
}
