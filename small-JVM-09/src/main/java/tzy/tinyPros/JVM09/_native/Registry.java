package tzy.tinyPros.JVM09._native;

import tzy.tinyPros.JVM09._native.java.lang.*;

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
        new _Class();
        new _Double();
        new _Float();
        new _Object();
        new _String();
        new _System();
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
