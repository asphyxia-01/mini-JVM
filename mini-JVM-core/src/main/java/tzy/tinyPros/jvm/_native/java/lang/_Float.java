package tzy.tinyPros.jvm._native.java.lang;

import tzy.tinyPros.jvm._native.MethodInfo;
import tzy.tinyPros.jvm._native.Need2Register;
import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/30 17:17
 **/
public class _Float extends Need2Register {
    private static final String TRIGGER_4_CLASSNAME = "java/lang/Float";

    {
        this.methodInfo2Register.put(
                new MethodInfo("floatToRawIntBits", "(F)I"),
                this::floatToRawIntBits
        );
        this.methodInfo2Register.put(
                new MethodInfo("intBitsToFloat", "(I)F"),
                this::intBitsToFloat
        );
        this.methodInfo2Register.put(
                new MethodInfo("registerNatives", "()V"),
                this::registerNatives
        );
    }

    public static void registerNow() {
        _Float holder = new _Float();
        holder.registerNow(holder, TRIGGER_4_CLASSNAME);
    }

    public void registerNatives(Frame frame) {
        // pass
    }

    public void floatToRawIntBits(Frame frame) {
        float val = frame.getLocalVarsTable().getFloat(0);
        frame.getOperandStack().pushInt(Float.floatToIntBits(val));
    }

    public void intBitsToFloat(Frame frame) {
        int val = frame.getLocalVarsTable().getInt(0);
        frame.getOperandStack().pushFloat(Float.intBitsToFloat(val));
    }
}
