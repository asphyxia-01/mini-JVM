package tzy.tinyPros.parser.attributes.impl;

import tzy.tinyPros.parser.ClassReader;
import tzy.tinyPros.parser.attributes.AttributeInfo;
import tzy.tinyPros.parser.attributes.BaseAttributeInfoElements;

/**
 * @author TPureZY
 * @since 2023/7/9 23:41
 * <p>
 * 使用特征签名代替描述符，是为了引入泛型语法之后能描述泛型参数化类型而添加
 * <p>
 * descriptor：主要是对方法参数和返回值进行描述
 * <p>
 * signature：泛型中才会将该属性编译进字节码文件，JDK 1.5才加入，除了方法参数和返回值，还包含了泛型信息
 * <p>
 * 所以二者在没有泛型的情况下没啥太大区别，比如对于 M find(M var0){} 这个方法，那么会额外标明参数是泛型的
 * <p>
 * https://blog.csdn.net/reliveit/article/details/51163403
 **/
public class LocalVariableTypeTableAttribute extends BaseAttributeInfoElements implements AttributeInfo {

    private LocalVariableTypeEntry[] localVariableTypeTable;

    public LocalVariableTypeTableAttribute(String attrName, int attrLen) {
        super(attrName, attrLen);
    }

    @Override
    public void readInfo(ClassReader cr) {
        int cnt = cr.readU2();
        this.localVariableTypeTable = new LocalVariableTypeEntry[cnt];
        for (int i = 0; i < cnt; i++) {
            this.localVariableTypeTable[i] = new LocalVariableTypeEntry(cr.readU2(), cr.readU2(), cr.readU2(), cr.readU2(), cr.readU2());
            // TODO: 还需要判断是否是Long或者Double类型，如果是，则占用两个索引，需要先i++再进入下一个循环
        }
    }

    static class LocalVariableTypeEntry {
        /**
         * 大体结构和LocalVariableEntry相似，区别只在于这里是signature_index，而后者是descriptor_index，JVM规范中如此解释道:
         * <p>
         * The LocalVariableTypeTable attribute differs from the LocalVariableTable attribute in that it provides
         * signature information rather than descriptor information. This difference is only significant for
         * variables whose type uses a type variable or parameterized type. Such variables will appear in both
         * tables, while variables of other types will appear only in LocalVariableTable.
         * <p>
         * 大体意思就是只有类型变量、参数型变量会在这个表(其实是两个表都有，除此以外这个表不记录其他类型的局部变量)，参考如下文章
         * <p>
         * https://www.cnblogs.com/cscw/p/13780497.html
         */
        private int startPC;
        private int length;
        private int nameIdx;
        private int signatureIdx;
        /**
         * The given local variable must be at index in the local variable array of the current frame.
         * <p>
         * If the local variable at index is of type double or long, it occupies both index and index + 1.
         */
        private int index;

        LocalVariableTypeEntry(int var0, int var1, int var2, int var3, int var4) {
            this.startPC = var0;
            this.length = var1;
            this.nameIdx = var2;
            this.signatureIdx = var3;
            this.index = var4;
        }
    }
}
