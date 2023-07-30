package tzy.tinyPros.JVM08.instructions.constants.ldc;

import tzy.tinyPros.JVM08.instructions.base.Index8Instruction;
import tzy.tinyPros.JVM08.rtda.heap.methodarea.StringPool;
import tzy.tinyPros.JVM08.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/20 19:54
 * <p>
 * 从运行时常量池中加载常量值并推入操作数栈
 **/
public class LDC extends Index8Instruction {
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
        }

        throw new RuntimeException("todo ldc");
    }
}
