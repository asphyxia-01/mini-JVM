package tzy.tinyPros.jvm._native.sun.misc;

import tzy.tinyPros.jvm._native.MethodInfo;
import tzy.tinyPros.jvm._native.Need2Register;
import tzy.tinyPros.jvm.instructions.base.MethodLogicInvoke;
import tzy.tinyPros.jvm.rtda.heap.methodarea.Klass;
import tzy.tinyPros.jvm.rtda.heap.methodarea.Method;
import tzy.tinyPros.jvm.rtda.heap.methodarea.StringPool;
import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.heap.methodarea.Object;

/**
 * VM相关内容还没看，代码先用着
 *
 * @author TPureZY
 * @since 2023/8/1 2:15
 **/
public class VM extends Need2Register {
    private static final String TRIGGER_4_CLASSNAME = "sun/misc/VM";

    {
        this.methodInfo2Register.put(
                new MethodInfo("getClass", "()Ljava/lang/Class;"),
                this::initialize
        );
        this.methodInfo2Register.put(
                new MethodInfo("registerNatives", "()V"),
                this::registerNatives
        );
    }

    public static void registerNow() {
        VM holder = new VM();
        holder.registerNow(holder, TRIGGER_4_CLASSNAME);
    }

    public void registerNatives(Frame frame) {
        // pass
    }

    public void initialize(Frame frame) {
        Klass vmClass = frame.getMethod().clazz;
        Object savedProps = vmClass.staticVars.getRef(vmClass.getField("savedProps", "Ljava/util/Properties;", true).slotId);
        Object key = StringPool.convertAndGetJavaInternStrObj(vmClass.loader, "foo");
        Object val = StringPool.convertAndGetJavaInternStrObj(vmClass.loader, "bar");

        frame.getOperandStack().pushRef(savedProps);
        frame.getOperandStack().pushRef(key);
        frame.getOperandStack().pushRef(val);

        Klass propsClass = vmClass.loader.loadClass("java/util/Properties");
        Method setPropMethod = propsClass.getInstanceMethod("setProperty",
                "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;");
        MethodLogicInvoke.invokeMethod(frame, setPropMethod);
    }
}
