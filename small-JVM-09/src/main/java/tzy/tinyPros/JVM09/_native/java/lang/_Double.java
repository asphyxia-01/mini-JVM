package tzy.tinyPros.JVM09._native.java.lang;

import tzy.tinyPros.JVM09._native.MethodInfo;
import tzy.tinyPros.JVM09._native.Need2Register;
import tzy.tinyPros.JVM09.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/30 17:12
 **/
public class _Double extends Need2Register {
    private static final String TRIGGER_4_CLASSNAME = "java/lang/Double";

    {
        this.methodInfo2Register.put(
                new MethodInfo("doubleToRawLongBits", "(D)J"),
                this::doubleToRawLongBits
        );
        this.methodInfo2Register.put(
                new MethodInfo("longBitsToDouble", "(J)D"),
                this::longBitsToDouble
        );
        this.methodInfo2Register.put(
                new MethodInfo("registerNatives", "()V"),
                this::registerNatives
        );
    }

    public static void registerNow() {
        _Double holder = new _Double();
        holder.registerNow(holder, TRIGGER_4_CLASSNAME);
    }

    public void registerNatives(Frame frame) {
        // pass
    }

    public void doubleToRawLongBits(Frame frame) {
        double val = frame.getLocalVarsTable().getDouble(0);
        frame.getOperandStack().pushLong(Double.doubleToLongBits(val));
    }

    public void longBitsToDouble(Frame frame) {
        long val = frame.getLocalVarsTable().getLong(0);
        frame.getOperandStack().pushDouble(Double.longBitsToDouble(val));
    }

}
