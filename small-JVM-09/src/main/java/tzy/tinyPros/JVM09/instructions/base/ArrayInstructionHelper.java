package tzy.tinyPros.JVM09.instructions.base;

/**
 * @author TPureZY
 * @since 2023/7/26 21:51
 **/
public class ArrayInstructionHelper {
    public static void checkNotNull(Object ref) {
        if (ref == null) {
            throw new RuntimeException();
        }
    }

    public static void checkIndex(int idx, int arrLen) {
        if (idx >= arrLen || idx < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
