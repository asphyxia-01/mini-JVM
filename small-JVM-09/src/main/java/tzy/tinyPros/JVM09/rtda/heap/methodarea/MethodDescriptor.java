package tzy.tinyPros.JVM09.rtda.heap.methodarea;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TPureZY
 * @since 2023/7/21 21:45
 * <p>
 * 分解方法的描述符，从中提取方法的参数类型和返回值类型并以MethodDescriptor类实例存储
 **/
public class MethodDescriptor {
    private List<String> parameterTypes = new ArrayList<>();
    private String returnType;

    /**
     * @param desc     分解后的方法描述符
     * @param isStatic 方法是否是静态方法，如果不是则slot要增加1，因为非静态方法有一个隐藏的引用参数 this 指向所属对象，而静态方法不用
     * @return 需要的slot的个数
     */
    public static int calcArgSlotCount(MethodDescriptor desc, boolean isStatic) {
        int cnt = isStatic ? 0 : 1;
        for (String parameterType : desc.getParameterTypes()) {
            // 如果是long或者double类型则占用两个slot
            if (parameterType.equals("J") || parameterType.equals("D")) {
                cnt++;
            }
            cnt++;
        }
        return cnt;
    }

    public void addParameterType(String var0) {
        this.parameterTypes.add(var0);
    }

    public List<String> getParameterTypes() {
        return parameterTypes;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
}
