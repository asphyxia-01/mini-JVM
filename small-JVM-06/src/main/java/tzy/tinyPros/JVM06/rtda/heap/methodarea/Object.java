package tzy.tinyPros.JVM06.rtda.heap.methodarea;

/**
 * @author TPureZY
 * @since 2023/7/18 16:20
 * <p>
 * 这里的Object仅仅是表示对象的类，不是Java的Object，没有取代Java的Object的意思；作为底层JVM，这个Object是用来具现化对象的，fields表示对象实例的字段(/属性)，Class表示这个哪个类的对象实例
 **/
public class Object {

    Class clazz;
    Slots fields;

    public Object(Class clazz) {
        this.clazz = clazz;
        this.fields = new Slots(clazz.instanceSlotCount);
    }

    public Class getClazz() {
        return clazz;
    }

    public Slots getFields() {
        return fields;
    }

    /**
     * 子类或类本身
     */
    public boolean isInstanceOf(Class clazz) {
        return clazz.isExtendFrom(this.clazz);
    }

}
