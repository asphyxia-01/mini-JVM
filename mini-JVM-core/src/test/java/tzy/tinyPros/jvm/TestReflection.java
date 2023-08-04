package tzy.tinyPros.jvm;

import java.util.List;

/**
 * 测试反射
 *
 * @author TPureZY
 * @since 2023/7/31 23:22
 **/
public class TestReflection {
    public static void main(String[] args) {
        System.out.println(byte.class.getName()); // byte
        System.out.println(void.class.getName()); // void
        System.out.println(boolean.class.getName()); // boolean
        System.out.println(char.class.getName()); // char
        System.out.println(short.class.getName()); // short
        System.out.println(int.class.getName()); // int
        System.out.println(long.class.getName()); // long
        System.out.println(float.class.getName()); // float
        System.out.println(double.class.getName()); // double
        System.out.println(Object.class.getName()); // java.lang.Object
        System.out.println(int[].class.getName()); // [I
        System.out.println(int[][].class.getName()); // [[I
        System.out.println(Object[].class.getName()); // [Ljava.lang.Object;
        System.out.println(Object[][].class.getName()); // [[Ljava.lang.Object;
        System.out.println(List.class.getName());
    }
}
