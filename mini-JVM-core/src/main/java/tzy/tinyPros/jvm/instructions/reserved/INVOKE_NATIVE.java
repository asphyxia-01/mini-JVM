package tzy.tinyPros.jvm.instructions.reserved;

import tzy.tinyPros.jvm._native.NativeMethod;
import tzy.tinyPros.jvm._native.Registry;
import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.heap.methodarea.Method;
import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/30 21:00
 * <p>
 * MY_INVOKE_NATIVE不在JVM规范之中，不同于JVM规范中的其他INVOKE指令，这个自定义的指令主要用来标识此时执行的是本地方法，需要JVM转去自己内部定义的方法执行（在该指令前已经更新JVM的栈帧），使用保留指令（0xfe）实现；而JVM规范中的其他INVOKE指令都伴随着新的栈帧创建并推入栈中执行，自定义的INVOKE是标识作用在推入新栈帧后执行时候遇到表明要JVM代为执行
 * <p>
 * <p>
 * 在调用方法时候会忽略native标识，也就是依旧会按照规则使用invoke_static或者invoke_virtual等，由JVM检测是否是本地方法然后进入后续操作
 **/
public class INVOKE_NATIVE extends NoOperandsInstruction {
    /**
     * 这个指令不是要推入新的方法栈帧，而是已经由上一条JVM规范中invoke_static或者其他invoke指令推入了新的栈帧，在执行code时候遇到了INVOKE_NATIVE（0xfe）指令，表明是本地方法由JVM内部逻辑实现执行
     */
    @Override
    public void execute(Frame frame) {
        Method method = frame.getMethod();
        NativeMethod nativeMethod = Registry.acquireNativeMethod(method.clazz.name, method.name, method.descriptor);
        if (null == nativeMethod) {
            throw new UnsatisfiedLinkError(String.join("~", new String[]{method.clazz.name, method.name, method.descriptor}));
        }
        nativeMethod.invoke(frame);
    }
}
