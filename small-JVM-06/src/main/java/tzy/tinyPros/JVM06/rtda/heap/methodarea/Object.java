package tzy.tinyPros.JVM06.rtda.heap.methodarea;

/**
 * @author TPureZY
 * @since 2023/7/18 16:20
 **/
public class Object {

    Class clazz;
    Slots fields;

    public Object(Class clazz){
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
    public boolean isInstanceOf(Class clazz){
        return clazz.isAssignableFrom(this.clazz);
    }

}
