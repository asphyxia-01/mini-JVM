package tzy.tinyPros.JVM09.instructions.constants.ldc;

import tzy.tinyPros.JVM09.instructions.base.Index8Instruction;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.StringPool;
import tzy.tinyPros.JVM09.rtda.thread.Frame;

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
