package tzy.tinyPros.JVM09._native;

import tzy.tinyPros.JVM09.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/30 15:57
 **/
public class NativeMethod {

    /**
     * 函数式接口
     */
    private MethodNode method;
    /**
     * (deprecated) 该NativeMethod所挂载的JVM中的底层结构，因为使用Java实现JVM所以自然是java.lang.Object,然后通过反射找到这个JVM内部支持的方法，JVM之上的Java线程模拟自己在运行本地方法有Frame栈帧结构，但实际是由JVM接管内部运行后返回值
     * <p>
     * <p>
     * (using now) 使用函数式接口实现<pre>{@code private MethodNode method;}</pre>将原先无关的几个类都继承了Need2Register抽象类，因此这个属性暂时无用
     */
    private Need2Register obj;

    public NativeMethod(Need2Register obj, MethodNode method) {
        this.method = method;
        this.obj = obj;
    }

    /**
     * JVM之上的Java线程模拟自己在运行本地方法有Frame栈帧结构，但实际是由JVM接管内部运行后返回值
     */
    public void invoke(Frame frame) {
        try {
            this.method.invoke(frame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
