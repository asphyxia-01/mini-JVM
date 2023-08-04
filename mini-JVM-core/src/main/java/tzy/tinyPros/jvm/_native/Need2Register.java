package tzy.tinyPros.jvm._native;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TPureZY
 * @since 2023/7/31 16:00
 **/
public abstract class Need2Register {
    /**
     * key 方法名称和描述符
     * <p>
     * value 绑定的JVM内部方法名称
     */
    protected final Map<MethodInfo, MethodNode> methodInfo2Register = new HashMap<>();

    /**
     * 设置是否需要在注册后清空methodInfo2Register中的信息，以节省空间
     */
    protected boolean del;

    protected final void registerNow(Need2Register obj, String trigger4ClassName) {
        for (Map.Entry<MethodInfo, MethodNode> entry : methodInfo2Register.entrySet()) {
            Registry.register(
                    trigger4ClassName,
                    entry.getKey().name,
                    entry.getKey().desc,
                    new NativeMethod(obj, entry.getValue())
            );
        }
        if (del) {
            methodInfo2Register.clear();
        }
    }

}
