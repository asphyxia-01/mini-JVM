package tzy.tinyPros.JVM09.instructions.base;

import tzy.tinyPros.JVM09.rtda.thread.Frame;

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
        this.idx = br.readShort();
    }

    @Override
    public void execute(Frame frame) {

    }

    public int getIdx() {
        return idx;
    }
}
