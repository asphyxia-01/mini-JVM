package tzy.tinyPros.jvm;

/**
 * @author TPureZY
 * @since 2023/8/1 1:25
 **/
public class TestNativeMethods {

    public static void main(String[] args) {
        System.out.println("-------hashCode()-------");
        Object var0 = new Object();
        Object var1 = new Object();
        Object var2 = new Object();
        System.out.println(var0.hashCode());
        System.out.println(var1.hashCode());
        System.out.println(var2.hashCode());

        System.out.println("-------clone()-------");
        InnerClass original = new InnerClass(0, 1, "tzy", new String[]{"tzy", "hello", "world"});
        try {
            InnerClass cloneObj = original.clone();
            System.out.println(cloneObj.strObj.hashCode() == original.strObj.hashCode());
            System.out.println(cloneObj.arrObj.hashCode() == original.arrObj.hashCode());
            cloneObj.var0 = 10;
            cloneObj.var1 = 11;
            cloneObj.strObj = new String("clone");
            cloneObj.arrObj[0] = "TZY-NEW";
            // 自编的JVM未实现锁机制，println()会被检测然后接管JVM内部逻辑执行，其代码内部的逻辑不会被JVM执行
            // 自编的JVM遇到String.valueOf会执行异常，是因为还有JVM很多逻辑没有实现
            System.out.println(original);
            System.out.println(cloneObj);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
class InnerClass implements Cloneable {
    int var0;
    float var1;
    String strObj;
    String[] arrObj;

    InnerClass(int var0, float var1, String strObj, String[] arrObj) {
        this.var0 = var0;
        this.var1 = var1;
        this.strObj = strObj;
        this.arrObj = arrObj;
    }

    @Override
    public InnerClass clone() throws CloneNotSupportedException {
        return (InnerClass) super.clone();
    }

    @Override
    public String toString() {
        return "InnerClass{" +
                "var0=" + var0 +
                ", var1=" + var1 +
                ", strObj='" + strObj + '\'' +
                '}';
    }
}