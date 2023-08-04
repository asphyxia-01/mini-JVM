package tzy.tinyPros.JVM09._native.java.lang;

import tzy.tinyPros.JVM09._native.MethodInfo;
import tzy.tinyPros.JVM09._native.Need2Register;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Klass;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Method;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Object;
import tzy.tinyPros.JVM09.rtda.thread.Frame;
import tzy.tinyPros.JVM09.rtda.thread.Thread;

/**
 * @author TPureZY
 * @since 2023/8/4 15:22
 **/
public class _Throwable extends Need2Register {

    private class StackTraceElement {
        private String fileName;
        private String className;
        private String methodName;
        private int lineNumber;

        private StackTraceElement(String fileName, String className, String methodName, int lineNumber) {
            this.fileName = fileName;
            this.className = className;
            this.methodName = methodName;
            this.lineNumber = lineNumber;
        }

        @Override
        public String toString() {
            return String.format(
                    "  at %s.%s(%s:%s)",
                    this.className,
                    this.methodName,
                    this.fileName,
                    this.lineNumber
            );
        }
    }

    private StackTraceElement traceElement;

    private static final String TRIGGER_4_CLASSNAME = "java/lang/Throwable";

    {
        this.methodInfo2Register.put(
                new MethodInfo("fillInStackTrace", "(I)Ljava/lang/Throwable;"),
                this::fillInStackTrace
        );
        this.methodInfo2Register.put(
                new MethodInfo("registerNatives", "()V"),
                this::registerNatives
        );
    }

    public static void registerNow() {
        _Throwable holder = new _Throwable();
        holder.registerNow(holder, TRIGGER_4_CLASSNAME);
    }

    public void registerNatives(Frame frame) {
        // pass
    }

    /**
     * 所有Exception和Error的实现类最上层都是Throwable，而Throwable中有个方法如下：
     * <pre>{@code private native Throwable fillInStackTrace(int dummy);}</pre>
     * 这个本地方法可以根据传入的this对象指针推导继承的层级，排除不需要参与路径回溯的自己的实现类
     * <p>
     * 同时这个本地方法对应的直接调用方法如下（最上层Throwable持有，各子类一般不会去重写也不直接调用）：
     * <pre>{@code public synchronized Throwable fillInStackTrace()}</pre>
     * 这个方法只在构造方法<init>中使用，因此构造方法从子类一步步向上调用super.<init>构造方法时候会依次填入构造方法对应的栈帧，而本地方法根据传入的this对象指针推测继承层级也可以排除自身初始化用的栈帧，然后剩余的栈帧就是出现<pre>{@code throw new ...Exception() / ...Error()}</pre>的地方，由此构建异常抛出时候的调用情况，构造回溯路径
     *
     * @param frame 栈帧
     */
    public void fillInStackTrace(Frame frame) {
        Object thisRef = frame.getLocalVarsTable().getRef(0);
        // 其实就是该本地方法的返回值
        frame.getOperandStack().pushRef(thisRef);
        _Throwable[] traceSeq = this.createTraceElements(thisRef, frame.getThread());
        thisRef.setExtra(traceSeq);
    }

    private _Throwable[] createTraceElements(Object obj, Thread thread) {
        Frame[] frames = thread.getFrames();
        int curStackDepth = frames.length;
        // 异常类的构造方法 + 两个fillInStackTrace方法
        int skipLen = this.computeClassExtendedDepth(obj.clazz) + 2;
        _Throwable[] traceSeq = new _Throwable[curStackDepth - skipLen];
        int idx = 0;
        for (int i = skipLen; i < curStackDepth; i++) {
            traceSeq[idx++] = createTraceElement(frames[i]);
        }
        return traceSeq;
    }

    private _Throwable createTraceElement(Frame frame) {
        Method method = frame.getMethod();
        Klass clazz = method.clazz;
        _Throwable trace = new _Throwable();
        trace.traceElement = new StackTraceElement(
                clazz.sourceFile,
                clazz.name,
                method.name,
                method.getLineNumber(frame.getNextPC() - 1)
        );
        return trace;
    }

    private int computeClassExtendedDepth(Klass clazz) {
        int sum = 0;
        for (Klass cur = clazz; cur != null; cur = cur.superClass) {
            sum++;
        }
        // 减一是因为所有类都继承java/lang/Object，而java/lang/Object不会执行<init>初始化方法，计算要跳过的栈帧不应该算入其中
        return sum - 1;
    }

    @Override
    public String toString() {
        return this.traceElement.toString();
    }
}
