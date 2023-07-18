package tzy.tinyPros.JVM06.instructions.stores.astore;

import tzy.tinyPros.JVM06.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM06.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 16:24
 **/
public class ASTORE_1 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        this._astore(frame,1);
    }
}
