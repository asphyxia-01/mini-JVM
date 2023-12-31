package tzy.tinyPros.jvm.instructions.base;

import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/15 15:49
 * <p>
 * 指令通用接口
 **/
public interface Instruction {
    void fetchOperands(ByteReader br);

    void execute(Frame frame);

    static void branch(Frame frame, int offset) {
        frame.setNextPC(frame.getThread().getPc() + offset);
    }
}
