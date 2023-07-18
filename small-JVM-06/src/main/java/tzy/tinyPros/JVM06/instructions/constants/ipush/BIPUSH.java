package tzy.tinyPros.JVM06.instructions.constants.ipush;

import tzy.tinyPros.JVM06.instructions.base.ByteReader;
import tzy.tinyPros.JVM06.instructions.base.Instruction;
import tzy.tinyPros.JVM06.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/15 23:59
 **/
public class BIPUSH implements Instruction {

    private byte data;

    @Override
    public void fetchOperands(ByteReader br) {
        this.data = br.readByte();
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(this.data);
    }
}
