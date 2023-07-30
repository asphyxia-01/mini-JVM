package tzy.tinyPros.JVM09.instructions.stores.istore;

import tzy.tinyPros.JVM09.instructions.base.Index8Instruction;
import tzy.tinyPros.JVM09.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 16:33
 **/
public class ISTORE extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        this._istore(frame,this.getIdx());
    }
}
