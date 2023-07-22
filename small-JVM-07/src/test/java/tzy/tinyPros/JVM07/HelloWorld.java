package tzy.tinyPros.JVM07;

/**
 * @author TPureZY
 * @since 2023/7/22 21:56
 **/
public class HelloWorld {
    public static void main(String[] args) {
        long x = fibonacci(10);
        System.out.println(x);
    }

    // Fibonacci sequence
    private static long fibonacci(long n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
