package tzy.tinyPros.JVM05;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TPureZY
 * @since 2023/7/17 21:14
 **/
public class test {

    static int ans = -1;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        int tmp = ans;

        for (int i = 0; i < 10; i++) {
            tmp += i;
        }
        ans = tmp;
//        System.out.println(ans);
    }
}