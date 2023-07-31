package tzy.tinyPros.JVM09.rtda.heap.methodarea;


import java.util.HashMap;
import java.util.Map;

import tzy.tinyPros.JVM09.rtda.heap.ClassLoader;

/**
 * @author TPureZY
 * @since 2023/7/27 15:39
 **/
public class StringPool {

    /**
     * K：用什么语言编写JVM则使用该语言的字符串表达方式
     * <p>
     * V：编写JVM的语言构建的Java对象的表达
     * <p>
     * 由常量池中的utf8数据映射到具体对象
     */
    private static final Map<String, Object> internStrPool = new HashMap<>();

    /**
     * 将 字符串 转为Java类型对象并入池，如果用Cpp编写JVM则原始字符串使用Cpp中的字符串表示方式接收
     *
     * @param cl          类加载器
     * @param originalStr 原始字符串
     * @return 入池后的Java中字符串对象
     */
    public static Object convertAndGetJavaInternStrObj(ClassLoader cl, String originalStr) {
        if (internStrPool.containsKey(originalStr)) {
            return internStrPool.get(originalStr);
        }
        char[] cs = originalStr.toCharArray();
        // 创建一个数组类对象
        Object valueInJavaStrObj = new Object(cl.loadClass("[C"), cs);
        Object javaStrObj = cl.loadClass("java/lang/String").newObject();
        javaStrObj.setRefVar("value", "[C", valueInJavaStrObj);
        internStrPool.put(originalStr, javaStrObj);
        return javaStrObj;
    }

    /**
     * 用编写JVM使用的语言来表示从对象Object概念中剥离出的utf8字符串，方便后续处理
     */
    public static String convertAndGetOriginalUtf8(Object strObj) {
        // 注意这个Object不是java/lang/Object，是对象概念的具象化，自己编写的用来表示对象这一概念的类
        Object cs = strObj.getRefVar("value", "[C");
        return new String(cs.chars());
    }

    /**
     * 如果字符串池中有则返回字符串池中的obj，而不是传入的要入池的strObj
     */
    public static Object internStr(Object strObj) {
        String key = convertAndGetOriginalUtf8(strObj);
        if (!internStrPool.containsKey(key)) {
            internStrPool.put(
                    key,
                    strObj
            );
        }
        return internStrPool.get(key);
    }

}
