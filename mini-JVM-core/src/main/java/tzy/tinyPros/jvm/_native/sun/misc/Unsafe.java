package tzy.tinyPros.jvm._native.sun.misc;

import tzy.tinyPros.jvm._native.MethodInfo;
import tzy.tinyPros.jvm._native.Need2Register;
import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/8/1 15:21
 **/
public class Unsafe extends Need2Register {
    private static final String TRIGGER_4_CLASSNAME = "sun/misc/Unsafe";

    {
        this.methodInfo2Register.put(
                new MethodInfo("registerNatives", "()V"),
                this::registerNatives
        );
    }

    public static void registerNow() {
        Unsafe holder = new Unsafe();
        holder.registerNow(holder, TRIGGER_4_CLASSNAME);
    }

    public void registerNatives(Frame frame) {
        // pass
    }
}
