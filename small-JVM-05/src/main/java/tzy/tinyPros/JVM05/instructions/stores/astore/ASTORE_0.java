package tzy.tinyPros.JVM05.instructions.stores.astore;

import tzy.tinyPros.JVM05.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM05.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 16:24
 **/
public class ASTORE_0 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        this._astore(frame,0);
    }
}
