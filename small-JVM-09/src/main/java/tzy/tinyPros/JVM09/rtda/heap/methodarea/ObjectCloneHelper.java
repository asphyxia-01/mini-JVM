package tzy.tinyPros.JVM09.rtda.heap.methodarea;

import java.util.Arrays;

/**
 * @author TPureZY
 * @since 2023/7/31 17:57
 **/
public class ObjectCloneHelper {
    public static Object cloneObj(Object obj, boolean isArray) {
        Object newObj;
        if (isArray) {
            newObj = new Object(obj.clazz, copyData4ArrObj(obj.data, 0, obj.arrayLength()));
        } else {
            newObj = new Object(obj.clazz, copySlots4Obj(((Slots) obj.data)));
        }
        newObj.setExtra(obj.getExtra());
        return newObj;
    }

    public static Slots copySlots4Obj(Slots slots) {
        return slots._clone();
    }

    public static java.lang.Object copyData4ArrObj(java.lang.Object arrData, int pos, int len) {
        if (arrData instanceof byte[]) {
            return Arrays.copyOfRange(((byte[]) arrData), pos, pos + len);
        }

        if (arrData instanceof short[]) {
            return Arrays.copyOfRange(((short[]) arrData), pos, pos + len);
        }

        if (arrData instanceof int[]) {
            return Arrays.copyOfRange(((int[]) arrData), pos, pos + len);
        }

        if (arrData instanceof long[]) {
            return Arrays.copyOfRange(((long[]) arrData), pos, pos + len);
        }

        if (arrData instanceof char[]) {
            return Arrays.copyOfRange(((char[]) arrData), pos, pos + len);
        }

        if (arrData instanceof float[]) {
            return Arrays.copyOfRange(((float[]) arrData), pos, pos + len);
        }

        if (arrData instanceof double[]) {
            return Arrays.copyOfRange(((double[]) arrData), pos, pos + len);
        }

        if (arrData instanceof Object[]) {
            return Arrays.copyOfRange(((Object[]) arrData), pos, pos + len);
        }

        throw new RuntimeException("Not array");
    }
}
