package tzy.tinyPros.JVM07.instructions.stores.istore;

import tzy.tinyPros.JVM07.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM07.rtda.thread.Frame;

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
