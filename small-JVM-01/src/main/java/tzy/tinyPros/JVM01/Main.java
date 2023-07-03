package tzy.tinyPros.JVM01;

/**
 * @author TPureZY
 * @since 2023/7/4 0:31
 **/
public class Main {
    public static void main(String[] args) {
        Cmd cmd = Cmd.parse(args);
        if(!cmd.ok || cmd.help){
            System.out.println("Usage: <main class> [-options] class [args...]");
            return;
        }
        if(cmd.version){
            System.out.println("java version \"11\"");
            return;
        }
        startJVM(cmd);
    }
    public static void startJVM(Cmd cmd){
        System.out.printf("classpath:%s class:%s args:%s\n",cmd.getClasspath().orElse(null),cmd.getMainClass().orElse(null),cmd.getAppArgs().orElse(null));
    }
}
