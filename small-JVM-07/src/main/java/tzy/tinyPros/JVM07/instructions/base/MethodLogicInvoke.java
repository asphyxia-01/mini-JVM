package tzy.tinyPros.JVM07.instructions.base;

import tzy.tinyPros.JVM07.rtda.heap.methodarea.Method;
import tzy.tinyPros.JVM07.rtda.thread.Frame;
import tzy.tinyPros.JVM07.rtda.thread.Thread;

/**
 * @author TPureZY
 * @since 2023/7/21 21:33
 * <p>
 * 定位到需要调用的方法后，JVM需要给这个方法创建一个新的帧把它推入JVM的栈顶并传递参数，这个逻辑对于invoke_special、invoke_virtual、invoke_static和invoke_interface基本相同，所以独立出来单独实现
 **/
public class MethodLogicInvoke {

    /**
     * @param frame  触发invoke系列指令的栈帧
     * @param method 要转入的目标方法
     */
    public static void invokeMethod(Frame frame, Method method) {
        Thread thread = frame.getThread();
        Frame newFrame = thread.newFrame(method);
        thread.pushFrame(newFrame);

        // 如果是本地方法则不需要入栈
        if (method.isNative()) {
            if ("registerNatives".equals(method.name)) {
                thread.popFrame();
            } else {
                throw new RuntimeException("native method " + method.name);
            }
        }

        int argSlotCnt = method.argSlotCnt;
        // 除了静态方法，其他方法至少一个参数，如 this 指针
        if (argSlotCnt > 0) {
            // Java中参数是从左到右入栈的，不过没什么特别的原因，约定罢了
            for (int i = argSlotCnt - 1; i >= 0; i--) {
                newFrame.getLocalVarsTable().setSlot(i, frame.getOperandStack().popSlot());
            }
        }
    }
}
