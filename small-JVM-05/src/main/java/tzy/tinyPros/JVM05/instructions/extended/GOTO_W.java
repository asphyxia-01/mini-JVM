package tzy.tinyPros.JVM05.instructions.extended;

import tzy.tinyPros.JVM05.instructions.base.ByteReader;
import tzy.tinyPros.JVM05.instructions.base.Instruction;
import tzy.tinyPros.JVM05.rtda.thread.Frame;

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
        this.offset = br.readInt();
    }

    @Override
    public void execute(Frame frame) {
        // 无条件转移
        Instruction.branch(frame,this.offset);
    }
}
