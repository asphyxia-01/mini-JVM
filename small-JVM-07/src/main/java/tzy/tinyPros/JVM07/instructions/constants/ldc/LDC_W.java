package tzy.tinyPros.JVM07.instructions.constants.ldc;

import tzy.tinyPros.JVM07.instructions.base.Index16Instruction;
import tzy.tinyPros.JVM07.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/20 19:59
 **/
public class LDC_W extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        Object val
                =
                frame
                        .getMethod()
                        .clazz.runTimeConstantPool
                        .constants[this.getIdx()];
        if (val instanceof Integer) {
            frame
                    .getOperandStack()
                    .pushInt((Integer) val);
        }
        if (val instanceof Float) {
            frame
                    .getOperandStack()
                    .pushFloat((Float) val);
        }
    }
}
