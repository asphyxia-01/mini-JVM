package tzy.tinyPros.jvm.instructions.base;

import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.heap.methodarea.Object;

/**
 * @author TPureZY
 * @since 2023/7/15 20:56
 * <p>
 * 一字节的操作数，对应的是局部变量表中数据的下标
 **/
public class Index8Instruction implements Instruction {

    private int idx;

    @Override
    public void fetchOperands(ByteReader br) {
        this.idx = br.read1Byte();
    }

    @Override
    public void execute(Frame frame) {

    }

    protected void _astore(Frame frame, int idx) {
        Object ref = frame.getOperandStack().popRef();
        frame.getLocalVarsTable().setRef(idx, ref);
    }

    protected void _dstore(Frame frame, int idx) {
        double v = frame.getOperandStack().popDouble();
        frame.getLocalVarsTable().setDouble(idx, v);
    }

    protected void _fstore(Frame frame, int idx) {
        float v = frame.getOperandStack().popFloat();
        frame.getLocalVarsTable().setFloat(idx, v);
    }

    protected void _istore(Frame frame, int idx) {
        int i = frame.getOperandStack().popInt();
        frame.getLocalVarsTable().setInt(idx, i);
    }

    protected void _lstore(Frame frame, int idx) {
        long l = frame.getOperandStack().popLong();
        frame.getLocalVarsTable().setLong(idx, l);
    }

    public int getIdx() {
        int curIdx = this.idx;
        // 作为索引不能为负
        if (curIdx < 0) {
            curIdx = curIdx & 0x000000ff;
        }
        return curIdx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }
}
