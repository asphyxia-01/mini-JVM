package tzy.tinyPros.JVM09;

/**
 * @author TPureZY
 * @since 2023/8/5 1:25
 **/
public class TestException {
    public static void main(String[] args) {
        createException(args);
    }

    public static void createException(String[] args) {
        try {
            testParseInt(args);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void testParseInt(String[] args) {
        if (args.length == 0) {
            throw new IndexOutOfBoundsException("越界了");
        }
        System.out.println(Integer.parseInt(args[0]));
    }
}
