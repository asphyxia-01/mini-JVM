package tzy.tinyPros.JVM09.instructions.reference;

import tzy.tinyPros.JVM09._native.java.lang._Throwable;
import tzy.tinyPros.JVM09.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Object;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.StringPool;
import tzy.tinyPros.JVM09.rtda.thread.Frame;
import tzy.tinyPros.JVM09.rtda.thread.Thread;

/**
 * athrow指令的操作数是一个异常对象引用，来自操作数栈
 *
 * @author TPureZY
 * @since 2023/8/5 0:36
 **/
public class ATHROW extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Object obj = frame.getOperandStack().popRef();
        if (obj == null) {
            throw new NullPointerException();
        }
        Thread thread = frame.getThread();
        if (!this.findAndGotoExceptionHandler(thread, obj)) {
            this.handleUncaughtException(thread, obj);
        }
    }

    private boolean findAndGotoExceptionHandler(Thread thread, Object obj) {
        while (!thread.isStackEmpty()) {
            Frame frame = thread.topFrame();
            int happenedPC = frame.getNextPC() - 1;
            int handlerPC = frame.getMethod().analyseExceptionAndGetHandlerPC(obj.clazz, happenedPC);
            if (handlerPC > 0) {
                frame.getOperandStack().clear().pushRef(obj);
                frame.setNextPC(handlerPC);
                return true;
            }
            thread.popFrame();
        }
        return false;
    }

    /**
     * 如果执行这个方法也就意味着遇到了没有捕获的异常或者错误，JVM会打印异常信息并停止运行
     */
    private void handleUncaughtException(Thread thread, Object obj) {
        thread.clearJVMStack();
        // 这个是Java层面用户代码new时候传入的自定义的异常信息，也获取并将其打印
        Object detailMessage = obj.getRefVar("detailMessage", "Ljava/lang/String;");
        String msg = "";
        if (detailMessage != null) {
            msg = StringPool.convertAndGetOriginalUtf8(detailMessage);
        }
        System.out.printf("Exception in thread '%s' %s " + ("".equals(msg) ? "\n" : ": %s\n"), thread.name, obj.clazz.name, msg);
        _Throwable[] table = (_Throwable[]) obj.getExtra();
        for (_Throwable throwable : table) {
            System.out.println(throwable);
        }
    }

}
