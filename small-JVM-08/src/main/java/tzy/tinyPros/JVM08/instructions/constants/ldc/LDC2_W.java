package tzy.tinyPros.JVM08.instructions.constants.ldc;

import tzy.tinyPros.JVM08.instructions.base.Index16Instruction;
import tzy.tinyPros.JVM08.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/20 19:59
 **/
public class LDC2_W extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        Object val
                =
                frame
                        .getMethod()
                        .clazz.runTimeConstantPool
                        .constants[this.getIdx()];
        if (val instanceof Long) {
            frame
                    .getOperandStack()
                    .pushLong((Long) val);
        }
        if (val instanceof Double) {
            frame
                    .getOperandStack()
                    .pushDouble((Double) val);
        }
    }
}
