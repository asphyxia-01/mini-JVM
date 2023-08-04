package tzy.tinyPros.jvm;

/**
 * 测试字符串拼接和入池
 *
 * @author TPureZY
 * @since 2023/7/31 23:40
 **/
public class TestString {
    public static void main(String[] args) {
        String var0 = "ivory tower";
        String var1 = "ivory tower";
        System.out.println(var0 == var1);
        String var2 = "pure22";
        String var3 = "pure";
        int var4 = 22;
        String var5 = var3 + var4;
        System.out.println(var2 == var5);
        var5 = var5.intern();
        System.out.println(var2 == var5);
    }
}
