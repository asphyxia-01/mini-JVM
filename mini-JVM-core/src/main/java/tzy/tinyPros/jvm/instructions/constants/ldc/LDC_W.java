package tzy.tinyPros.jvm.instructions.constants.ldc;

import tzy.tinyPros.jvm.instructions.base.Index16Instruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/20 19:59
 **/
public class LDC_W extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        Object obj = frame
                .getMethod()
                .clazz.runTimeConstantPool
                .constants[this.getIdx()];
        LDCUtil._ldc_or_ldc_w(frame, obj);
    }
}
