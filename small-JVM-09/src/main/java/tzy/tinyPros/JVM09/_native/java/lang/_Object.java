package tzy.tinyPros.JVM09._native.java.lang;

import tzy.tinyPros.JVM09._native.MethodInfo;
import tzy.tinyPros.JVM09._native.Need2Register;
import tzy.tinyPros.JVM09.rtda.heap.ClassLoader;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Klass;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Object;
import tzy.tinyPros.JVM09.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/30 17:17
 **/
public class _Object extends Need2Register {
    private static final String TRIGGER_4_CLASSNAME = "java/lang/Object";

    {
        this.methodInfo2Register.put(
                new MethodInfo("getClass", "()Ljava/lang/Class;"),
                this::_getClass
        );
        this.methodInfo2Register.put(
                new MethodInfo("hashCode", "()I"),
                this::_hashCode
        );
        this.methodInfo2Register.put(
                new MethodInfo("clone", "()Ljava/lang/Object;"),
                this::_clone
        );
        this.methodInfo2Register.put(
                new MethodInfo("registerNatives", "()V"),
                this::registerNatives
        );
    }

    public static void registerNow() {
        _Object holder = new _Object();
        holder.register(holder, TRIGGER_4_CLASSNAME);
    }

    public void registerNatives(Frame frame) {
        // pass
    }

    public void _getClass(Frame frame) {
        Object thisRef = frame.getLocalVarsTable().getRef(0);
        frame.getOperandStack().pushRef(thisRef.clazz.getJavaClassObj());
    }

    public void _hashCode(Frame frame) {
        Object thisRef = frame.getLocalVarsTable().getRef(0);
        frame.getOperandStack().pushInt(thisRef.hashCode());
    }

    public void _clone(Frame frame) throws CloneNotSupportedException {
        Object thisRef = frame.getLocalVarsTable().getRef(0);
        Klass cloneableKlass = thisRef.clazz.loader.loadClass(ClassLoader.UsedClassNameInfo.JAVA_LANG_CLONEABLE);
        if (!thisRef.clazz.isImplementFrom(cloneableKlass)) {
            throw new CloneNotSupportedException();
        }
        frame.getOperandStack().pushRef(thisRef._clone());
    }
}
