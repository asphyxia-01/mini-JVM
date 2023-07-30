package tzy.tinyPros.JVM08.instructions.constants.ldc;

import tzy.tinyPros.JVM08.instructions.base.Index16Instruction;
import tzy.tinyPros.JVM08.rtda.heap.methodarea.StringPool;
import tzy.tinyPros.JVM08.rtda.thread.Frame;

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
            return;
        }
        if (val instanceof Float) {
            frame
                    .getOperandStack()
                    .pushFloat((Float) val);
            return;
        }
        if (val instanceof String) {
            frame
                    .getOperandStack()
                    .pushRef(
                            StringPool
                                    .convertAndGetJavaInternStrObj(
                                            frame.getMethod().clazz.loader,
                                            (String) val
                                    )
                    );
            return;
        }
        throw new RuntimeException("todo ldc");
    }
}
