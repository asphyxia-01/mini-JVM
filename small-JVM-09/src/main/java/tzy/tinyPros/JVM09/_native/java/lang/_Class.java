package tzy.tinyPros.JVM09._native.java.lang;

import tzy.tinyPros.JVM09._native.MethodInfo;
import tzy.tinyPros.JVM09._native.Need2Register;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Klass;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Object;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.StringPool;
import tzy.tinyPros.JVM09.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/30 17:12
 **/
public class _Class extends Need2Register {
    /**
     * 这里内部定义的本地方法主要为{@code trigger4JClassName}使用
     */
    private static final String TRIGGER_4_CLASSNAME = "java/lang/Class";

    {
        this.methodInfo2Register.put(
                new MethodInfo("getPrimitiveClass", "(Ljava/lang/String;)Ljava/lang/Class;"),
                this::getPrimitiveClass
        );
        this.methodInfo2Register.put(
                new MethodInfo("getName0", "()Ljava/lang/String;"),
                this::getName0
        );
        this.methodInfo2Register.put(
                new MethodInfo("desiredAssertionStatus0", "(Ljava/lang/Class;)Z"),
                this::desiredAssertionStatus0
        );
        this.methodInfo2Register.put(
                new MethodInfo("registerNatives", "()V"),
                this::registerNatives
        );
    }

    public static void registerNow() {
        _Class holder = new _Class();
        holder.registerNow(holder, TRIGGER_4_CLASSNAME);
    }

    public void registerNatives(Frame frame) {
        // pass
    }

    public void getPrimitiveClass(Frame frame) {
        Object ref = frame.getLocalVarsTable().getRef(0);
        String name = StringPool.convertAndGetOriginalUtf8(ref);
        frame.getOperandStack().pushRef(frame.getMethod().clazz.loader.loadClass(name).getJavaClassObj());
    }

    public void getName0(Frame frame) {
        // java层面的java/lang/Class对像实例
        Object jClassRef = frame.getLocalVarsTable().getRef(0);
        // 获取底层绑定到的Klass
        Klass bindKlass = (Klass) jClassRef.getExtra();
        frame.getOperandStack().pushRef(StringPool.convertAndGetJavaInternStrObj(bindKlass.loader, bindKlass.name));
    }

    public void desiredAssertionStatus0(Frame frame) {
        // pass 暂时不考虑断言机制，有些包装类在初始化时候会调用这个本地方法
        frame.getOperandStack().pushBoolean(false);
    }

    public void isInterface(Frame frame) {
        // 这个ref是Java层面java/lang/Class的对象实例
        Object ref = frame.getLocalVarsTable().getRef(0);
        frame.getOperandStack().pushBoolean(((Klass) ref.getExtra()).isInterface());
    }

    public void isPrimitive(Frame frame) {
        Object ref = frame.getLocalVarsTable().getRef(0);
        frame.getOperandStack().pushBoolean(((Klass) ref.getExtra()).isPrimitive());
    }
}
