package tzy.tinyPros.JVM06;

import tzy.tinyPros.JVM06.classfile.ClassFile;
import tzy.tinyPros.JVM06.classfile.MemberInfo;
import tzy.tinyPros.JVM06.classpath.Classpath;

import java.util.Arrays;

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
        ClassFile cf = loadClass(className, cp);
        assert cf != null;
        MemberInfo mainMethod = getMainMethod(cf.getMethods());
        new Interpreter(mainMethod);
//        printClassStructure(cf);
    }

    public static ClassFile loadClass(String className, Classpath cp) {
        try {
            // 读取字节码
            byte[] classData = cp.readClass(className);
            // 解析字节码
            return new ClassFile(classData);
        } catch (Exception e) {
            System.out.println("Could not find or load main class: " + className);
            e.printStackTrace();
        }
        return null;
    }

    public static void printClassStructure(ClassFile cf) {
        System.out.printf("version: %d.%d\n", cf.getMajorVersion(), cf.getMinorVersion());
        System.out.printf("constant_pool_count: %d\n", cf.getConstantPool().getSize());
        System.out.printf("access_flags: 0x%x\n", cf.getAccessFlags());
        System.out.printf("this_class_name: %s\n", cf.getClassName());
        System.out.printf("super_class_name: %s\n", cf.getSuperClassName());
        System.out.printf("interface_name_array: %s\n", Arrays.toString(cf.getInterfaceNames()));
        System.out.printf("fields_count: %d\n", cf.getFields().length);
        for (MemberInfo field : cf.getFields()) {
            System.out.printf("%s \t\t %s\n", field.getName(), field.getDescriptor());
        }
        System.out.printf("methods_count: %d\n", cf.getMethods().length);
        for (MemberInfo method : cf.getMethods()) {
            System.out.printf("%s \t\t %s\n", method.getName(), method.getDescriptor());
        }
    }

    public static void printBytes(byte[] classData) {
        System.out.println("classData: ");
        for (byte data : classData) {
            // 使用16进制输出
            System.out.print(String.format("%02x", data & 0xff) + " ");
        }
    }

    public static void printUserArgs(Cmd cmd){
        System.out.printf("classpath:%s class:%s args:%s\n", cmd.getClasspath().orElse(null), cmd.getMainClass().orElse(null), cmd.getAppArgs().orElse(null));
    }

    public static MemberInfo getMainMethod(MemberInfo[] infos){
        for (MemberInfo info : infos) {
            // 寻找main方法
            if("main".equals(info.getName())&&"([Ljava/lang/String;)V".equals(info.getDescriptor())){
                return info;
            }
        }
        return null;
    }
}
