package tzy.tinyPros.JVM06.instructions.control;

import tzy.tinyPros.JVM06.instructions.base.ByteReader;
import tzy.tinyPros.JVM06.instructions.base.Instruction;
import tzy.tinyPros.JVM06.rtda.thread.Frame;
import tzy.tinyPros.JVM06.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 16:05
 * <p>
 * access jump table by index and jump
 * <p>
 * The alignment required of the 4-byte operands of the tableswitch instruction guarantees 4-byte alignment of those operands if and only if the method that contains the tableswitch starts on a 4-byte boundary.
 * <p>
 * 这个指令也具备跳转的功能，所以也有offset，操作数栈的栈顶（*）对应的就是offset的索引，使用这个索引去jumpOffsets中获取offset，然后执行跳转，注意这里的offset不是由指令操作数直接给出的，是先去操作数栈顶获取索引然后去jumpOffsets中找offset
 **/
public class TABLE_SWITCH implements Instruction {

    /**
     * 都是占用4byte为1个单位
     */
    private int defaultOffset;
    private int low;
    private int high;
    /**
     * 当case之间存在连续的关系时候，使用tableswitch
     */
    private int[] jumpOffsets;

    @Override
    public void fetchOperands(ByteReader br) {
        // 起始字节位置一定是4字节的倍数
        br.skipPadding();
        this.defaultOffset = br.readInt();
        this.low = br.readInt();
        this.high = br.readInt();
        this.jumpOffsets = br.readInts(this.high - this.low + 1);
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        int offset = defaultOffset;
        if (index >= this.low && index <= this.high) {
            offset = jumpOffsets[index - this.low];
        }
        Instruction.branch(frame, offset);
    }
}
