package tzy.tinyPros.JVM07;

import tzy.tinyPros.JVM07.classpath.Classpath;
import tzy.tinyPros.JVM07.rtda.heap.ClassLoader;
import tzy.tinyPros.JVM07.rtda.heap.methodarea.Class;
import tzy.tinyPros.JVM07.rtda.heap.methodarea.Method;

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
        // Java中命名空间中类的全类名和classpath组合就是目标文件的路径，组合时候将全类名的'.'全部更改为'/'
        String className = cmd.getMainClass().get().replace(".", "/");
        ClassLoader classLoader = new ClassLoader(cp);
        Class mainClass = classLoader.loadClass(className);
        Method mainMethod = mainClass.getMainMethod();
        assert mainMethod != null : "Main method not found in class " + cmd.getMainClass();
        new Interpreter(mainMethod);
    }

}
