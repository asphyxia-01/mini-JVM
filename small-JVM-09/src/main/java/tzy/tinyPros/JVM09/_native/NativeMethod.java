package tzy.tinyPros.JVM09._native;

import tzy.tinyPros.JVM09.rtda.thread.Frame;

import java.lang.reflect.Method;

/**
 * @author TPureZY
 * @since 2023/7/30 15:57
 **/
public class NativeMethod {
    private String methodName;
    /**
     * 该NativeMethod所挂载的JVM中的底层结构，因为使用Java实现JVM所以自然是java.lang.Object,然后通过反射找到这个JVM内部支持的方法，JVM之上的Java线程模拟自己在运行本地方法有Frame栈帧结构，但实际是由JVM接管内部运行后返回值
     */
    private java.lang.Object obj;

    public NativeMethod(java.lang.Object obj, String methodName) {
        this.methodName = methodName;
        this.obj = obj;
    }

    /**
     * JVM之上的Java线程模拟自己在运行本地方法有Frame栈帧结构，但实际是由JVM接管内部运行后返回值
     */
    public void invoke(Frame frame) {
        try {
            // public Method getMethod(String name, Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException
            Method method = this.obj.getClass().getMethod(this.methodName, frame.getClass());
            method.invoke(this.obj, frame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
