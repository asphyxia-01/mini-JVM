package tzy.tinyPros.JVM07.classpath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author TPureZY
 * @since 2023/7/5 2:02
 * <p>
 * 启动类路径、拓展类路径、用户类路径处理
 **/
public class Classpath {

    /**
     * 启动类路径
     */
    private Entry bootstrapClasspath;
    /**
     * 拓展类路径
     */
    private Entry extensionClasspath;
    /**
     * 用户类路径
     */
    private Entry userClasspath;

    public Classpath(String bootAndExtPath, String userPath) {
        bootstrapAndExtensionClassPath(bootAndExtPath);
        parseUserClassPath(userPath);
    }

    private void bootstrapAndExtensionClassPath(String bootAndExtPath) {
        // 主要是解析此路径是否正确
        String jreDir = getJreDir(bootAndExtPath);
        // ...jre/lib/*
        System.out.printf("bootstrapClasspath: %s\n",Paths.get(jreDir,"lib"));
        bootstrapClasspath = Entry.create(Paths.get(jreDir, "lib") + File.separator + "*");
        // ...jre/lib/ext/*
        System.out.printf("extensionClasspath: %s\n",Paths.get(jreDir,"lib","ext"));
        extensionClasspath = Entry.create(Paths.get(jreDir, "lib", "ext") + File.separator + "*");
    }

    private void parseUserClassPath(String userPath) {
        if (userPath == null) {
            // 默认是当前目录
            userPath = ".";
        }
        System.out.printf("userClasspath: %s\n",Paths.get(userPath).toAbsolutePath());
        userClasspath = Entry.create(userPath);
    }

    private String getJreDir(String bootAndExtPath) {
        // 如果指定了Jre的路径并且确实存在
        if (bootAndExtPath != null && Files.exists(Paths.get(bootAndExtPath))) {
            return bootAndExtPath;
        }
        // 如果没有指定，则先在当前目录下找
        if (Files.exists(Paths.get("./jre"))) {
            return "./jre";
        }
        // 从系统环境变量中找
        String javaHome = System.getenv("JAVA_HOME");
        if (javaHome != null) {
            return Paths.get(javaHome, "jre").toString();
        }
        throw new RuntimeException("Can not find JRE folder");
    }

    public byte[] readClass(String className) throws IOException {
        className += ".class";
        // 依次查找
        try {
            return bootstrapClasspath.readClass(className);
        } catch (Exception e) {
            // ignored
        }
        try {
            return extensionClasspath.readClass(className);
        } catch (Exception e) {
            // ignored
        }
        return userClasspath.readClass(className);
    }
}
