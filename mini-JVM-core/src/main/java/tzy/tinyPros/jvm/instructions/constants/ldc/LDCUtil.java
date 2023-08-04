package tzy.tinyPros.jvm.instructions.constants.ldc;

import tzy.tinyPros.jvm.rtda.heap.constantpool.ClassRef;
import tzy.tinyPros.jvm.rtda.heap.methodarea.StringPool;
import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/31 0:34
 **/
public class LDCUtil {
    public static void _ldc_or_ldc_w(Frame frame, Object val) {
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
                    .pushRef(StringPool.convertAndGetJavaInternStrObj(frame.getMethod().clazz.loader, (String) val));
            return;
        }
        // ldc指令加载非基本类型的Class对象到操作数栈中
        if (val instanceof ClassRef) {
            frame
                    .getOperandStack()
                    .pushRef(((ClassRef) val).getClazz().getJavaClassObj());
            return;
        }
        throw new RuntimeException("todo ldc");
    }
}
