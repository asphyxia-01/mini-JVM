package tzy.tinyPros.JVM02;

import tzy.tinyPros.JVM02.classpath.Classpath;

/**
 * @author TPureZY
 * @since 2023/7/4 0:31
 **/
public class Main {
    public static void main(String[] args) {
        Cmd cmd = Cmd.parse(args);
        if (!cmd.ok || cmd.help) {
            System.out.println("Usage: <main class> [-options] class [args...]");
            return;
        }
        if (cmd.version) {
            System.out.println("java version \"1.8\"");
            return;
        }
        startJVM(cmd);
    }

    public static void startJVM(Cmd cmd) {
        Classpath cp = new Classpath(cmd.getJre().orElse(null), cmd.getClasspath().orElse(null));
        System.out.printf("classpath:%s class:%s args:%s\n", cmd.getClasspath().orElse(null), cmd.getMainClass().orElse(null), cmd.getAppArgs().orElse(null));
        // Java中命名空间中类的全类名和classpath组合就是目标文件的路径，组合时候将全类名的'.'全部更改为'/'
        String className = cmd.getMainClass().get().replace(".", "/");
        try {
            byte[] classData = cp.readClass(className);
            System.out.println("classData: ");
            for (byte data : classData) {
                // 使用16进制输出
                System.out.print(String.format("%02x", data & 0xff) + " ");
            }
        } catch (Exception e) {
            System.out.println("Could not find or load main class" + cmd.getMainClass());
            e.printStackTrace();
        }
    }
}
