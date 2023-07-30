package tzy.tinyPros.JVM08.rtda.heap.methodarea;

/**
 * @author TPureZY
 * @since 2023/7/27 0:23
 **/
public class ClassHierarchy {
    private Klass holder;

    private ClassHierarchy(Klass clazz) {
        this.holder = clazz;
    }

    public static ClassHierarchy getClassHierarchy(Klass clazz) {
        return new ClassHierarchy(clazz);
    }

    /**
     * 判断holder是否是other的子类
     * <p>
     * 基本数据类型如：int、long等不能强转（不含String），编译时就会报错
     * <p>
     * 1、数组可以强制转换成java/lang/Object类型（因为数组的超类是java/lang/Object）
     * <p>
     * 2、数组可以强制转换为Cloneable和Serializable类型（因为数组实现了整两个接口）
     * <p>
     * 3、下两个条件成立一个，类型SC[]的数组可以强转为类型TC[]的数组
     * <p>
     * 3.1、TC和SC是同一个基本类型
     * <p>
     * 3.2、TC和SC都是引用类型，且SC可以强转为TC
     */
    public boolean isAssignableFrom(Klass other) {
        if (holder == other) {
            return true;
        }
        if (!holder.isArray()) {
            if (!holder.isInterface()) {
                return holder.isAssignableFrom(other);
            } else {
                if (!other.isInterface()) {
                    // holder是接口，而other不是接口，那other只能是java/lang/Object才行
                    return other.isJLObject();
                } else {
                    return holder.isSubImplementFrom(other);
                }
            }
        } else {
            if (!other.isArray()) {
                if (!other.isInterface()) {
                    // 如果holder是数组，但是other不是数组也不是接口，那么只能是java/lang/Object才可
                    return other.isJLObject();
                } else {
                    return other.isJLCloneable() || other.isJIOSerializable();
                }
            } else {
                Klass holderComponent = holder.getComponentClassFromArrayClass();
                Klass otherComponent = other.getComponentClassFromArrayClass();
                return holderComponent == otherComponent
                        || holderComponent.isAssignableFrom(otherComponent);
            }
        }
    }

    public boolean isImplementsFrom(Klass otherInterface) {
        return this.holder.isImplementFrom(otherInterface);
    }
}
