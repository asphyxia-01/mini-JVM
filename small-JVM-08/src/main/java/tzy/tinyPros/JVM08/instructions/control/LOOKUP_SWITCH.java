package tzy.tinyPros.JVM08.instructions.control;

import tzy.tinyPros.JVM08.instructions.base.ByteReader;
import tzy.tinyPros.JVM08.instructions.base.Instruction;
import tzy.tinyPros.JVM08.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/17 16:30
 * <p>
 * Access jump table by key match and jump
 * <p>
 * 功能和tableswitch相差不大，主要用于case是离散的情况。matchOffsets类似于Map，其中两位为一组，第一位是case的key值，第二位是offset，所以使用操作数栈顶与key进行比较，相同则获取后一位中对应的offset进行跳转
 **/
public class LOOKUP_SWITCH implements Instruction {

    private int defaultOffset;
    private int npairs;
    private int[] matchOffsets;

    @Override
    public void fetchOperands(ByteReader br) {
        br.skipPadding();
        this.defaultOffset = br.readInt();
        this.npairs = br.readInt();
        this.matchOffsets = br.readInts(this.npairs * 2);
    }

    @Override
    public void execute(Frame frame) {
        int key = frame.getOperandStack().popInt();
        int offset = this.defaultOffset;
        for (int i = 0; i < npairs * 2; i += 2) {
            if (this.matchOffsets[i] == key) {
                offset = this.matchOffsets[i + 1];
                break;
            }
        }
        Instruction.branch(frame, offset);
    }
}
