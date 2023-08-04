package tzy.tinyPros.jvm._native;

import tzy.tinyPros.jvm._native.java.lang.*;
import tzy.tinyPros.jvm._native.sun.misc.Unsafe;
import tzy.tinyPros.jvm._native.sun.misc.VM;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TPureZY
 * @since 2023/7/30 15:37
 **/
public class Registry {
    private static final Map<String, NativeMethod> registry = new HashMap<>();

    /**
     * JVM启动时候就加载本地方法并注册到registry上
     */
    public static void initNative() {
        _Class.registerNow();
        _Double.registerNow();
        _Float.registerNow();
        _Object.registerNow();
        _String.registerNow();
        _System.registerNow();
        VM.registerNow();
        Unsafe.registerNow();
        _Throwable.registerNow();
    }

    /**
     * 注册本地方法
     */
    public static void register(String className, String methodName, String methodDesc, NativeMethod method) {
        registry.put(
                String.join("~", new String[]{className, methodName, methodDesc}),
                method
        );
    }

    /**
     * 获取本地方法
     */
    public static NativeMethod acquireNativeMethod(String className, String methodName, String methodDesc) {
        return registry.getOrDefault(String.join("~", new String[]{className, methodName, methodDesc}), null);
    }

}
