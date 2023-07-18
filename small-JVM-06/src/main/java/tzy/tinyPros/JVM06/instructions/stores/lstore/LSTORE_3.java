package tzy.tinyPros.JVM06.instructions.stores.lstore;

import tzy.tinyPros.JVM06.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM06.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 16:34
 **/
public class LSTORE_3 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        this._istore(frame,3);
    }
}
