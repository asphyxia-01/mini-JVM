package tzy.tinyPros.jvm.utils;

import tzy.tinyPros.jvm.Cmd;
import tzy.tinyPros.parser.ClassFile;
import tzy.tinyPros.parser.MemberInfo;

import java.util.Arrays;

/**
 * @author TPureZY
 * @since 2023/7/20 22:41
 * <p>
 * 打印工具
 **/
class PrintUtils {
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

    public static void printUserArgs(Cmd cmd) {
        System.out.printf("classpath:%s class:%s args:%s\n", cmd.getClasspath().orElse(null), cmd.getMainClass().orElse(null), cmd.getAppArgs().orElse(null));
    }
}