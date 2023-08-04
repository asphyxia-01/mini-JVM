package tzy.tinyPros.jvm.rtda.heap.methodarea;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TPureZY
 * @since 2023/7/25 23:39
 **/
public class ClassNameHelper {

    /**
     * 不单单是 key -> value，使用时候借助遍历操作可以反向映射
     * <p>
     * 基本数据类型对应的Class中存储的名称是如：boolean 这样的小写全名
     */
    public static final Map<String, String> primitiveTypes = new HashMap<String, String>() {
        {
            put("void", "V");
            put("boolean", "Z");
            put("byte", "B");
            put("short", "S");
            put("char", "C");
            put("int", "I");
            put("long", "J");
            put("float", "F");
            put("double", "D");
        }
    };

    /**
     * [XXX -> [[XXX
     * <p>
     * int -> [I
     * <p>
     * XXX -> [LXXX
     * <p>
     * 将 className 转为数组的形式
     */
    static String getArrayClassName(String className) {
        return "[" + toDescriptor(className);
    }

    static String getComponentClassName(String className) {
        if (className.charAt(0) == '[') {
            String componentTypeDescriptor = className.substring(1);
            return toClassName(componentTypeDescriptor);
        }
        throw new RuntimeException("Not array " + className);
    }

    private static String toDescriptor(String className) {
        if (className.getBytes()[0] == '[') {
            return className;
        }

        String d = primitiveTypes.get(className);
        if (null != d) {
            return d;
        }

        return "L" + className + ";";
    }

    private static String toClassName(String descriptor) {
        char firstDesc = descriptor.charAt(0);
        if (firstDesc == '[') {
            return descriptor;
        }

        if (firstDesc == 'L') {
            return descriptor.substring(1, descriptor.length() - 1);
        }

        for (Map.Entry<String, String> entry : primitiveTypes.entrySet()) {
            if (entry.getValue().equals(descriptor)) {
                // acquire primitive type
                return entry.getKey();
            }
        }

        throw new RuntimeException("Invalid descriptor " + descriptor);

    }
}
