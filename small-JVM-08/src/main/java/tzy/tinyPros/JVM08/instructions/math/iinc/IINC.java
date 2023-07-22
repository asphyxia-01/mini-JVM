package tzy.tinyPros.JVM08.instructions.math.iinc;

import tzy.tinyPros.JVM08.instructions.base.ByteReader;
import tzy.tinyPros.JVM08.instructions.base.Instruction;
import tzy.tinyPros.JVM08.rtda.thread.Frame;
import tzy.tinyPros.JVM08.rtda.thread.LocalVarsTable;

/**
 * @author TPureZY
 * @since 2023/7/17 0:27
 * <p>
 * <blockquote><pre>
 *     {
 *         u1 index;
 *         u1 const;
 *     }
 * </pre></blockquote>
 * <p>
 * The index is an unsigned byte that must be an index into the local variable array of the current frame
 * (§2.6). The const is an immediate signed byte. The local variable at index must contain an int. The value
 * const is first sign-extended to an int, and then the local variable at index is incremented by that amount.
 **/
public class IINC implements Instruction {

    private int index;
    private int constVal;

    @Override
    public void fetchOperands(ByteReader br) {
        this.index = br.readByte();
        this.constVal = br.readByte();
    }

    @Override
    public void execute(Frame frame) {
        LocalVarsTable table = frame.getLocalVarsTable();
        table.setInt(this.index, table.getInt(this.index) + this.constVal);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
