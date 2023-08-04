package tzy.tinyPros.parser.constantpool.impl;

/**
 * @author TPureZY
 * @since 2023/7/7 0:57
 *
 * Field || Method 的Constant_NameAndType_info的索引查找结果包装类
 **/
public class NameAndTypeInstance {
    /**
     * 字段名||方法名
     */
    private String name;
    /**
     * 描述符，也就是Type
     */
    private String descriptor;

    public NameAndTypeInstance(String name,String desc){
        this.name = name;
        this.descriptor = desc;
    }

    public String getName() {
        return name;
    }

    public String getDescriptor() {
        return descriptor;
    }
}
