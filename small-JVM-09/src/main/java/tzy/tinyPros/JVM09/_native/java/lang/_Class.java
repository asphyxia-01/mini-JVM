package tzy.tinyPros.JVM09._native.java.lang;

import tzy.tinyPros.JVM09._native.NativeMethod;
import tzy.tinyPros.JVM09._native.Registry;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Object;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.StringPool;
import tzy.tinyPros.JVM09.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/30 17:12
 **/
public class _Class {
    /**
     * 这里内部定义的本地方法主要为{@code trigger4JClassName}使用
     */
    private static final String trigger4JClassName = "java/lang/Class";

    private class MethodInfo2Register {
        static final String METHOD_1 = "getPrimitiveClass";
        static final String PARAMS_RETURN_1 = "(Ljava/lang/String;)Ljava/lang/Class;";
        static final String METHOD_2 = "getName0";
        static final String PARAMS_RETURN_2 = "()Ljava/lang/String;";
        static final String METHOD_3 = "desiredAssertionStatus0";
        static final String PARAMS_RETURN_3 = "(Ljava/lang/Class;)Z";
        static final String METHOD_4 = "registerNatives";
        static final String PARAMS_RETURN_4 = "()V";
    }

    public void registerNow() {
        Registry.register(trigger4JClassName, MethodInfo2Register.METHOD_1, MethodInfo2Register.PARAMS_RETURN_1, new NativeMethod(this, MethodInfo2Register.METHOD_1));
        Registry.register(trigger4JClassName, MethodInfo2Register.METHOD_2, MethodInfo2Register.PARAMS_RETURN_2, new NativeMethod(this, MethodInfo2Register.METHOD_2));
        Registry.register(trigger4JClassName, MethodInfo2Register.METHOD_3, MethodInfo2Register.PARAMS_RETURN_3, new NativeMethod(this, MethodInfo2Register.METHOD_3));
        Registry.register(trigger4JClassName, MethodInfo2Register.METHOD_4, MethodInfo2Register.PARAMS_RETURN_4, new NativeMethod(this, MethodInfo2Register.METHOD_4));
    }

    public void registerNatives(Frame frame) {
        // pass
    }

    public void getPrimitiveClass(Frame frame) {
        Object ref = frame.getLocalVarsTable().getRef(0);
        String name = StringPool.convertAndGetOriginalUtf8(ref);
        frame.getOperandStack().pushRef(frame.getMethod().clazz.loader.loadClass(name).getJavaClassObj());
    }

}
