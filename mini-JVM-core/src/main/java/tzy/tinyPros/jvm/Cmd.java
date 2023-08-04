package tzy.tinyPros.jvm;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.List;
import java.util.Optional;

/**
 * @author TPureZY
 * @since 2023/7/4 0:06
 **/
public class Cmd {
    @Parameter(names = {"-?", "-h", "-help"}, description = "print help message", order = 3, arity = 0, help = true)
    boolean help = false;

    @Parameter(names = {"-v", "-version", "-V"}, description = "print jvm version", order = 2, arity = 0)
    boolean version = false;

    @Parameter(names = {"-cp", "-classpath"}, description = "import classpath", order = 1, arity = 1)
    String classpath;

    @Parameter(names = "-verbose:inst", description = "enable verbose output", order = 5, arity = 1)
    boolean verboseInstFlag = false;

    @Parameter(names = {"-Xjre"}, description = "jre path(now using jre1.8 which has rj.jar)", order = 4)
    String jre;

    @Parameter(description = "main class and args")
    List<String> mainClassAndArgs;

    boolean ok;

    public Optional<String> getJre() {
        return Optional.ofNullable(jre);
    }

    public Optional<String> getClasspath() {
        return Optional.ofNullable(classpath);
    }

    public Optional<String> getMainClass() {
        return mainClassAndArgs != null && !mainClassAndArgs.isEmpty() ? Optional.of(mainClassAndArgs.get(0)) : Optional.empty();
    }

    public Optional<List<String>> getAppArgs() {
        return isLegal() ? Optional.of(mainClassAndArgs.subList(1, mainClassAndArgs.size())) : Optional.empty();
    }

    public boolean isLegal() {
        return mainClassAndArgs != null && mainClassAndArgs.size() > 1;
    }

    public Optional<Boolean> getverboseInstFlag() {
        return Optional.of(this.verboseInstFlag);
    }

    public static Cmd parse(String[] argv) {
        Cmd args = new Cmd();
        JCommander cmd = JCommander.newBuilder().addObject(args).build();
        // Parse and validate the command line parameters.
        cmd.parse(argv);
        args.ok = true;
        return args;
    }
}
