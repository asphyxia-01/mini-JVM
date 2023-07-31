package tzy.tinyPros.JVM09;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TPureZY
 * @since 2023/8/1 1:25
 **/
public class TestNativeMethods {
    private static class InnerClass implements Cloneable {
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
                    ", arrObj=" + arrObj[0] +
                    '}';
        }
    }

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
//            System.out.println("original = " + original);
//            System.out.println("cloneObj = " + cloneObj);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
