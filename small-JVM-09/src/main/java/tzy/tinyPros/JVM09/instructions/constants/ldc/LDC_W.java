package tzy.tinyPros.JVM09.instructions.constants.ldc;

import tzy.tinyPros.JVM09.instructions.base.ByteReader;
import tzy.tinyPros.JVM09.instructions.base.Index16Instruction;
import tzy.tinyPros.JVM09.instructions.base.Instruction;
import tzy.tinyPros.JVM09.rtda.heap.constantpool.ClassRef;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.StringPool;
import tzy.tinyPros.JVM09.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/20 19:59
 **/
public class LDC_W extends AbstractLDC implements Instruction {
    private int idx;

    @Override
    public void fetchOperands(ByteReader br) {
        // 索引占用2byte
        this.idx = br.readShort();
    }

    @Override
    public void execute(Frame frame) {
        Object val
                =
                frame
                        .getMethod()
                        .clazz.runTimeConstantPool
                        .constants[this.idx];
        this._ldc_or_ldc_w(frame, val);
    }
}
