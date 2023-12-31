package tzy.tinyPros.jvm.instructions.extended;

import tzy.tinyPros.jvm.instructions.base.ByteReader;
import tzy.tinyPros.jvm.instructions.base.Instruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/17 17:35
 * <p>
 * Branch always (wide index)
 * <p>
 * 操作数（非索引）一般占用2字节，但是goto_w会拓展，所以此时占用4字节
 **/
public class GOTO_W implements Instruction {

    private int offset;

    @Override
    public void fetchOperands(ByteReader br) {
        this.offset = br.read4Byte();
    }

    @Override
    public void execute(Frame frame) {
        // 无条件转移
        Instruction.branch(frame,this.offset);
    }
}
