package tzy.tinyPros.JVM03.classfile.attributes;

/**
 * @author TPureZY
 * @since 2023/7/8 20:45
 **/
public abstract class BaseAttributeInfoElements {

    /**
     * 属性的名称
     * JVM中此处应是attribute_name_index，是常量池中的索引，指向一个Constant_utf8_info，此处简略为实际值
     */
    protected String attrName;
    /**
     * 属性的长度
     * JVM中此处是attribute_length，表示的是不包含attribute_name_index和attribute_length的长度在内的实际长度，即attribute主体部分的字节数
     */
    protected int attrLen;

    public BaseAttributeInfoElements(String attrName, int attrLen) {
        this.attrName = attrName;
        this.attrLen = attrLen;
    }

    public String getAttrName() {
        return this.attrName;
    }

    public int getAttrLen() {
        return this.attrLen;
    }

}
