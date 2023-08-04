package tzy.tinyPros.jvm.instructions.base;

import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/15 16:15
 * <p>
 * 跳转指令
 **/
public class InstructionBranch implements Instruction {

    private int offset;

    @Override
    public void fetchOperands(ByteReader br) {
        // 操作数占用两字节
        this.offset = br.read2Byte();
    }

    @Override
    public void execute(Frame frame) {

    }

    protected boolean _acmp(Frame frame) {
        Object ref0 = frame.getOperandStack().popRef();
        Object ref1 = frame.getOperandStack().popRef();
        return ref0.equals(ref1);
    }

    public int getOffset() {
        return offset;
    }
}
