package tzy.tinyPros.JVM08.instructions.stores.lstore;

import tzy.tinyPros.JVM08.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM08.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 16:34
 **/
public class LSTORE_0 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        this._lstore(frame,0);
    }
}
