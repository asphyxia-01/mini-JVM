package tzy.tinyPros.JVM09._native;

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

    protected final void register(Need2Register obj, String trigger4ClassName) {
        for (Map.Entry<MethodInfo, MethodNode> entry : methodInfo2Register.entrySet()) {
            Registry.register(trigger4ClassName, entry.getKey().name, entry.getKey().desc, new NativeMethod(obj, entry.getValue()));
        }
    }

}
