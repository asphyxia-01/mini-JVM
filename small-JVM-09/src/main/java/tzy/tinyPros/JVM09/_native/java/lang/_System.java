package tzy.tinyPros.JVM09._native.java.lang;

import tzy.tinyPros.JVM09._native.MethodInfo;
import tzy.tinyPros.JVM09._native.Need2Register;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Klass;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Object;
import tzy.tinyPros.JVM09.rtda.thread.Frame;
import tzy.tinyPros.JVM09.rtda.thread.LocalVarsTable;

/**
 * @author TPureZY
 * @since 2023/7/30 17:17
 **/
public class _System extends Need2Register {
    private static final String TRIGGER_4_CLASSNAME = "java/lang/System";

    {
        this.methodInfo2Register.put(
                new MethodInfo("arraycopy", "(Ljava/lang/Object;ILjava/lang/Object;II)V"),
                this::arraycopy
        );
        this.methodInfo2Register.put(
                new MethodInfo("registerNatives", "()V"),
                this::registerNatives
        );
    }

    public static void registerNow() {
        _System holder = new _System();
        holder.register(holder, TRIGGER_4_CLASSNAME);
    }

    public void registerNatives(Frame frame) {
        // pass
    }

    public void arraycopy(Frame frame) {
        LocalVarsTable table = frame.getLocalVarsTable();
        Object srcRef = table.getRef(0);
        int srcPos = table.getInt(1);
        Object destRef = table.getRef(2);
        int destPos = table.getInt(3);
        int len = table.getInt(4);

        if (srcRef == null || destRef == null) {
            throw new NullPointerException();
        }

        if (!checkArrIfCompatible(srcRef, destRef)) {
            throw new ArrayStoreException();
        }

        if (srcPos < 0
                || destPos < 0
                || len < 0
                || srcPos + len > srcRef.arrayLength()
                || destPos + len > destRef.arrayLength()) {
            throw new IndexOutOfBoundsException();
        }

        // 因为是Java写的JVM，直接用所支持的方法，这里可能会有所疑惑和混淆，如果使用Cpp编写JVM则这里则会是Cpp所拥有的数组复制方法
        System.arraycopy(srcRef.data, srcPos, destRef.data, destPos, len);
    }

    private boolean checkArrIfCompatible(Object src, Object dest) {
        Klass srcKlass = src.clazz;
        Klass destKlass = dest.clazz;
        if (!srcKlass.isArray() || !destKlass.isArray()) {
            return false;
        }
        // 都是基本类型
        if (srcKlass.getComponentClassFromArrayClass().isPrimitive() || destKlass.getComponentClassFromArrayClass().isPrimitive()) {
            return srcKlass == destKlass;
        }
        return true;
    }
}
