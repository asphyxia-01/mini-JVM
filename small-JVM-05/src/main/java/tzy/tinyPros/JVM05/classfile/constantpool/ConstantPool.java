package tzy.tinyPros.JVM05.classfile.constantpool;

import tzy.tinyPros.JVM05.classfile.ClassReader;
import tzy.tinyPros.JVM05.classfile.constantpool.impl.ConstantClassInfo;
import tzy.tinyPros.JVM05.classfile.constantpool.impl.ConstantNameAndTypeInfo;
import tzy.tinyPros.JVM05.classfile.constantpool.impl.ConstantUtf8Info;
import tzy.tinyPros.JVM05.classfile.constantpool.impl.NameAndTypeInstance;

/**
 * @author TPureZY
 * @since 2023/7/6 20:07
 **/
public class ConstantPool {

    private ConstantInfo[] infos;
    /**
     * infos的长度
     */
    private int size;

    /**
     * 主要是对常量池中的各类数据按照对应的结构体进行解析读取为具体的实例
     *
     * @param cr 字节流读取类
     */
    public ConstantPool(ClassReader cr) {
        this.size = cr.readU2();
        this.infos = new ConstantInfo[this.size];
        // 注意，实际索引范围是 1 ~ size-1 ，不是从0开始，当有些引用常量池数据的索引index = 0时候一般表示没有引用常量池中的数据，意思就是当前是null值
        for (int i = 1; i < this.size; i++) {
            // 这个方法每次调用会读取某一个结构体的连续完整数据
            this.infos[i] = ConstantInfo.readConstantInfo(cr, this);
            // 注意处理特殊情况，对于Constant_Long_info和Constant_Double_info要占用两个slot，如果结构体在32bit以内使用1个slot，而64bit则使用2个slot
            switch (this.infos[i].tag()) {
                case ConstantInfo.CONSTANT_TAG_LONG:
                case ConstantInfo.CONSTANT_TAG_DOUBLE:
                    i++;
                    break;
            }
        }
    }

    public NameAndTypeInstance getNameAndType(int idx) {
        ConstantNameAndTypeInfo nat = ((ConstantNameAndTypeInfo) this.infos[idx]);
        return new NameAndTypeInstance(
                this.getUtf8(nat.getNameIdx()),
                this.getUtf8(nat.getDescriptorIdx())
        );
    }

    public String getUtf8(int idx) {
        return ((ConstantUtf8Info) this.infos[idx]).getStr();
    }

    /**
     * 通过idx找到ConstantClassInfo实例，但是该实例内部还有一个nameIdx的索引，这个nameIdx索引才真正指向Utf8的名称
     *
     * @param idx ConstantClassInfo的实例的下标
     * @return 对应的类名
     */
    public String getClassName(int idx) {
        ConstantClassInfo info = (ConstantClassInfo) this.infos[idx];
        return info == null?"":info.getName();
    }

    public ConstantInfo[] getInfos() {
        return this.infos;
    }

    public void setInfos(ConstantInfo[] infos) {
        this.infos = infos;
    }

    public int getSize() {
        return this.size;
    }
}
