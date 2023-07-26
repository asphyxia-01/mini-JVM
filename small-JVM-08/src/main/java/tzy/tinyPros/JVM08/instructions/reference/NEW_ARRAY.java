package tzy.tinyPros.JVM08.instructions.reference;

import tzy.tinyPros.JVM08.instructions.base.ByteReader;
import tzy.tinyPros.JVM08.instructions.base.Index8Instruction;
import tzy.tinyPros.JVM08.instructions.base.Instruction;
import tzy.tinyPros.JVM08.rtda.thread.Frame;
import tzy.tinyPros.JVM08.rtda.heap.ClassLoader;
import tzy.tinyPros.JVM08.rtda.heap.methodarea.Class;

/**
 * newarray指令用来创建基本类型数组，
 * <p>
 * 两个操作数：uint8整数（atype，来自指令）表示要创建的基本数组类型；第二个操作数是数组长度（count，来自操作数栈）
 *
 * @author TPureZY
 * @since 2023/7/26 0:19
 **/
public class NEW_ARRAY extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        int cnt = frame
                .getOperandStack()
                .popInt();
        if (cnt < 0) {
            throw new NegativeArraySizeException();
        }
        Class arrClass = this.getPrimitiveArrayClass(
                frame.getMethod().clazz.loader,
                this.getIdx()
        );
        frame
                .getOperandStack()
                .pushRef(
                        arrClass.newArray(cnt)
                );
    }

    private Class getPrimitiveArrayClass(ClassLoader cr, int atype) {
        switch (atype) {
            case ArrayType.AT_BOOLEAN:
                return cr.loadClass("[Z");
            case ArrayType.AT_BYTE:
                return cr.loadClass("[B");
            case ArrayType.AT_CHAR:
                return cr.loadClass("[C");
            case ArrayType.AT_SHORT:
                return cr.loadClass("[S");
            case ArrayType.AT_INT:
                return cr.loadClass("[I");
            case ArrayType.AT_LONG:
                return cr.loadClass("[J");
            case ArrayType.AT_FLOAT:
                return cr.loadClass("[F");
            case ArrayType.AT_DOUBLE:
                return cr.loadClass("[D");
            default:
                throw new RuntimeException("Invalid atype!");
        }
    }

    static class ArrayType {
        static final byte AT_BOOLEAN = 4;
        static final byte AT_CHAR = 5;
        static final byte AT_FLOAT = 6;
        static final byte AT_DOUBLE = 7;
        static final byte AT_BYTE = 8;
        static final byte AT_SHORT = 9;
        static final byte AT_INT = 10;
        static final byte AT_LONG = 11;
    }

}
