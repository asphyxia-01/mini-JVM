package tzy.tinyPros.JVM07.instructions.stores.dstore;

import tzy.tinyPros.JVM07.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM07.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 16:26
 **/
public class DSTORE_3 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        this._dstore(frame,3);
    }
}
