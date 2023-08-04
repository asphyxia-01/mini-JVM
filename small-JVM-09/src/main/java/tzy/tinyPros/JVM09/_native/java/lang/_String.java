package tzy.tinyPros.JVM09._native.java.lang;

import tzy.tinyPros.JVM09._native.MethodInfo;
import tzy.tinyPros.JVM09._native.Need2Register;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Object;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.StringPool;
import tzy.tinyPros.JVM09.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/30 17:17
 **/
public class _String extends Need2Register {
    private static final String TRIGGER_4_CLASSNAME = "java/lang/String";

    {
        this.methodInfo2Register.put(
                new MethodInfo("intern", "()Ljava/lang/String;"),
                this::intern
        );
        this.methodInfo2Register.put(
                new MethodInfo("registerNatives", "()V"),
                this::registerNatives
        );
    }

    public static void registerNow() {
        _String holder = new _String();
        holder.registerNow(holder, TRIGGER_4_CLASSNAME);
    }

    public void registerNatives(Frame frame) {
        // pass
    }

    public void intern(Frame frame) {
        // strObj同时也是Java层面默认传入的this指针，因为Java层面intern是实例方法而不是类方法（静态方法）
        Object strObj = frame.getLocalVarsTable().getRef(0);
        frame.getOperandStack().pushRef(StringPool.internStr(strObj));
    }

}
