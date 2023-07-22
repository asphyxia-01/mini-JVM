package tzy.tinyPros.JVM08.instructions.stores.dstore;

import tzy.tinyPros.JVM08.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM08.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 16:26
 **/
public class DSTORE_2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        this._dstore(frame,2);
    }
}
