package tzy.tinyPros.JVM03;

import java.math.BigInteger;

/**
 * @author TPureZY
 * @since 2023/7/5 3:19
 **/
public class HelloWorld {
    private int num = 10;

//    class Inner{
//        int inner_num;
//        public Inner(){
//            assert num != 20;
//            inner_num = num;
//        }
//    }

//    public static void main(String[] args) {
//        System.out.println("Hello World");
//    }

    public static void main(String[] args) {
        double num = -10.241;
        long l = Double.doubleToLongBits(num);
        byte[] bytes = new byte[8];
        for (int i = 7; i >=0; i--) {
            bytes[i] = (byte) l;
            l>>=8;
        }
        long var0 = new BigInteger(1, bytes).longValue();
        System.out.println(Double.longBitsToDouble(var0));
    }
}
