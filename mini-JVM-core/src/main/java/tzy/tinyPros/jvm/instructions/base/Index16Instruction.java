package tzy.tinyPros.jvm.instructions.base;

import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/15 22:02
 * <p>
 * 有些指令需要访问运行时常量池，常量池索引由两字节给出
 **/
public class Index16Instruction implements Instruction {

    private int idx;

    @Override
    public void fetchOperands(ByteReader br) {
        this.idx = br.read2Byte();
    }

    @Override
    public void execute(Frame frame) {

    }

    public int getIdx() {
        int curIdx = this.idx;
        // 作为索引不能为负
        if (curIdx < 0) {
            curIdx = curIdx & 0x000000ff;
        }
        return curIdx;
    }
}
