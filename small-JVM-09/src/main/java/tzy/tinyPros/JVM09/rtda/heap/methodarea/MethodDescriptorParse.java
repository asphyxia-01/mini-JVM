package tzy.tinyPros.JVM09.rtda.heap.methodarea;

/**
 * @author TPureZY
 * @since 2023/7/21 21:48
 * <p>
 * 分解方法描述符的工具类
 * <p>
 * 描述符格式举例：
 * <p>
 * (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
 * <p>
 * ([CIILjava/lang/String;I)I
 * <p>
 * 可以看出基础类型间没有';'，但是引用类型会以';'结尾
 **/
public class MethodDescriptorParse {
    private String row;
    /**
     * Encodes this {@code String} into a sequence of bytes using the platform's default charset, storing the result into a new byte array.
     * <p>
     * 原byte[]，后改为char[]使用
     */
    private char[] rowChars;
    private int offset;
    private MethodDescriptor parsedDesc;

    public static MethodDescriptor parseMethodDescriptorParser(String descriptor) {
        MethodDescriptorParse methodDescriptorParse = new MethodDescriptorParse();
        return methodDescriptorParse.parse(descriptor);
    }

    private MethodDescriptor parse(String descriptor) {
        this.row = descriptor;
        this.rowChars = this.row.toCharArray();
        this.offset = 0;
        parsedDesc = new MethodDescriptor();
        this.startParams();
        this.parseParamTypes();
        this.endParams();
        this.parseReturnParams();
        this.finish();
        return this.parsedDesc;
    }

    /**
     * 解析出错进行报错
     */
    private void panic() {
        throw new RuntimeException("BAD DESCRIPTOR: " + this.row);
    }

    private char ReadC1() {
        return this.rowChars[this.offset++];
    }

    private void unReadC1() {
        this.offset--;
    }

    private void startParams() {
        if (this.ReadC1() != '(') {
            this.panic();
        }
    }

    private void endParams() {
        if (this.ReadC1() != ')') {
            this.panic();
        }
    }

    private void parseParamTypes() {
        while (1 == 1) {
            String type = this.parseFieldType();
            if ("".equals(type)) {
                break;
            }
            this.parsedDesc.addParameterType(type);
        }
    }

    private void parseReturnParams() {
        // 表示返回值是void
        if (this.ReadC1() == 'V') {
            this.parsedDesc.setReturnType("V");
            return;
        }
        this.unReadC1();
        String type = this.parseFieldType();
        if (!"".equals(type)) {
            this.parsedDesc.setReturnType(type);
            return;
        }
        // 说明描述符格式有问题
        panic();
    }

    private void finish() {
        if (this.offset != this.rowChars.length) {
            this.panic();
        }
    }

    private String parseFieldType() {
        switch (this.ReadC1()) {
            case 'B':
                return "B";
            case 'C':
                return "C";
            case 'D':
                return "D";
            case 'F':
                return "F";
            case 'I':
                return "I";
            case 'J':
                return "J";
            case 'S':
                return "S";
            case 'Z':
                return "Z";
            case 'L':
                return this.parseObjectType();
            case '[':
                return this.parseArrayType();
            default:
                // 说明读到描述符中参数表的末尾了
                this.unReadC1();
                return "";
        }
    }

    /**
     * 引用类型比较特殊，使用 ‘L’ 开头，然后后面跟的是具体的类的全类名
     */
    private String parseObjectType() {
        // 保留switch时候读取的标识符，所以前移一格
        this.unReadC1();
        String unread = this.row.substring(this.offset);
        int endIdx = unread.indexOf(";");
        if (endIdx == -1) {
            this.panic();
            return "";
        }
        int begIdx = this.offset;
        // endIdx指向的是";"的位置后一个位置，保留原始格式如：Ljava/lang/Object;
        endIdx = this.offset + endIdx + 1;
        this.offset = endIdx;
        return this.row.substring(begIdx, endIdx);
    }

    private String parseArrayType() {
        // 保留switch时候读取的标识符，所以前移一格
        int begIdx = this.offset - 1;
        // '['后的表达方式没有特别之处
        this.parseFieldType();
        int endIdx = this.offset;
        return this.row.substring(begIdx, endIdx);
    }
}
