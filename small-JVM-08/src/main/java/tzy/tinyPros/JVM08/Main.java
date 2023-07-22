package tzy.tinyPros.JVM08;

import tzy.tinyPros.JVM08.classpath.Classpath;
import tzy.tinyPros.JVM08.rtda.heap.ClassLoader;
import tzy.tinyPros.JVM08.rtda.heap.methodarea.Method;

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
        // Java中命名空间中类的全类名和classpath组合就是目标文件的路径，组合时候将全类名的'.'全部更改为'/'
        Method mainMethod
                =
                new ClassLoader(
                        new Classpath(
                                cmd
                                        .getJre()
                                        .orElse(null),
                                cmd
                                        .getClasspath()
                                        .orElse(null)
                        )
                )
                        .loadClass(
                                cmd
                                        .getMainClass()
                                        .get()
                                        .replace(".", "/")
                        )
                        .getMainMethod();
        assert mainMethod != null : "Main method not found in class " + cmd.getMainClass();
        Interpreter.interpret(mainMethod, cmd.getverboseInstFlag().get());
    }

}
