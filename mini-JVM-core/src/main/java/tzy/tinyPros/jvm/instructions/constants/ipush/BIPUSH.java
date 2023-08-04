package tzy.tinyPros.jvm.instructions.constants.ipush;

import tzy.tinyPros.jvm.instructions.base.ByteReader;
import tzy.tinyPros.jvm.instructions.base.Instruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/15 23:59
 **/
public class BIPUSH implements Instruction {

    private byte data;

    @Override
    public void fetchOperands(ByteReader br) {
        this.data = br.read1Byte();
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(this.data);
    }
}
