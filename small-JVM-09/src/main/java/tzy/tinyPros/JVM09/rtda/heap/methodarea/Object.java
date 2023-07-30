package tzy.tinyPros.JVM09.rtda.heap.methodarea;

/**
 * @author TPureZY
 * @since 2023/7/18 16:20
 * <p>
 * 这里的Object仅仅是表示对象的类，不是Java的Object，没有取代Java的Object的意思；作为底层JVM，这个Object是用来具现化对象的，fields表示对象实例的字段(/属性)，Class表示这个哪个类的对象实例
 **/
public class Object {

    public final Class clazz;

    /**
     * 普通类就是 fields Slots
     * <p>
     * 数组类就是 data T[]
     */
    public final java.lang.Object data;

    /**
     * 普通类
     */
    public Object(Class clazz) {
        this.clazz = clazz;
        this.data = new Slots(clazz.instanceSlotCount);
    }

    /**
     * 数组类
     */
    public Object(Class clazz, java.lang.Object data) {
        this.clazz = clazz;
        this.data = data;
    }

    public Slots fields() {
        return (Slots) this.data;
    }

    /**
     * 子类或类本身
     */
    public boolean isInstanceOf(Class clazz) {
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
}
