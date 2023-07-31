package tzy.tinyPros.JVM09.rtda.heap.methodarea;

import com.beust.jcommander.Parameter;

/**
 * @author TPureZY
 * @since 2023/7/18 16:20
 * <p>
 * 这里的Object仅仅是表示对象的类，不是Java的java/lang/Object，没有取代Java的Object的意思；作为底层JVM，这个Object是用来具现化对象的，fields表示对象实例的字段(/属性)，Klass表示这个哪个类的对象实例
 **/
public class Object {

    /**
     * 该对象在JVM中底层在方法区（元数据区）维护的Klass结构
     */
    public final Klass clazz;

    /**
     * 普通类就是 fields Slots
     * <p>
     * 数组类就是 data T[]
     */
    public java.lang.Object data;

    /**
     * 如果当前对象是java/lang/Class对象（即 clazz.name == "java/lang/Class"），则extra存放的是与其绑定的Klass结构，表示当前Object对象是Java层面中哪个类的伴生Class对象，此时也可以写成<pre>{@code private Klass bindKlass}</pre>
     * <p>
     * <p>
     * 每个类除了实例化的对象，都有一个伴生的Class对象（在Java层面就是java/lang/Class的实例对象，但绑定不同的底层Klass结构，相当于映射，以实现一个特殊功能如反射）
     */
    private java.lang.Object extra;

    /**
     * 普通类
     */
    public Object(Klass clazz) {
        this.clazz = clazz;
        this.data = new Slots(clazz.instanceSlotCount);
    }

    /**
     * 数组类
     */
    public Object(Klass clazz, java.lang.Object data) {
        this.clazz = clazz;
        this.data = data;
    }

    public Slots fields() {
        return (Slots) this.data;
    }

    /**
     * 子类或类本身
     */
    public boolean isInstanceOf(Klass clazz) {
        return this.clazz.isAssignableFrom(clazz);
    }

    public Object getRefVar(String name, String desc) {
        Field field = this.clazz.getField(name, desc, false);
        return this.fields().getRef(field.slotId);
    }

    public void setRefVar(String name, String desc, Object ref) {
        Field field = this.clazz.getField(name, desc, false);
        this.fields().setRef(field.slotId, ref);
    }

    public byte[] bytes() {
        return (byte[]) this.data;
    }

    public short[] shorts() {
        return (short[]) this.data;
    }

    public int[] ints() {
        return (int[]) this.data;
    }

    public long[] longs() {
        return (long[]) this.data;
    }

    public char[] chars() {
        return (char[]) this.data;
    }

    public float[] floats() {
        return (float[]) this.data;
    }

    public double[] doubles() {
        return (double[]) this.data;
    }

    public Object[] refs() {
        return (Object[]) this.data;
    }

    public int arrayLength() {

        if (this.data instanceof byte[]) {
            return ((byte[]) this.data).length;
        }

        if (this.data instanceof short[]) {
            return ((short[]) this.data).length;
        }

        if (this.data instanceof int[]) {
            return ((int[]) this.data).length;
        }

        if (this.data instanceof long[]) {
            return ((long[]) this.data).length;
        }

        if (this.data instanceof char[]) {
            return ((char[]) this.data).length;
        }

        if (this.data instanceof float[]) {
            return ((float[]) this.data).length;
        }

        if (this.data instanceof double[]) {
            return ((double[]) this.data).length;
        }

        if (this.data instanceof Object[]) {
            return ((Object[]) this.data).length;
        }

        throw new RuntimeException("Not array");
    }

    public void setExtra(java.lang.Object obj) {
        this.extra = obj;
    }

    public java.lang.Object getExtra() {
        return this.extra;
    }

    public Object _clone() {
        return ObjectCloneHelper.cloneObj(this, this.clazz.isArray());
    }

}
