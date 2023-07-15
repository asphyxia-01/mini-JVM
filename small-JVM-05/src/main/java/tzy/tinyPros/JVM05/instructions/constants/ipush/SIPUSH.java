package tzy.tinyPros.JVM05.instructions.constants.ipush;

import tzy.tinyPros.JVM05.instructions.base.ByteReader;
import tzy.tinyPros.JVM05.instructions.base.Instruction;
import tzy.tinyPros.JVM05.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/16 0:03
 **/
public class SIPUSH implements Instruction {

    private short data;

    @Override
    public void fetchOperands(ByteReader br) {
        this.data = br.readShort();
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(this.data);
    }
}
