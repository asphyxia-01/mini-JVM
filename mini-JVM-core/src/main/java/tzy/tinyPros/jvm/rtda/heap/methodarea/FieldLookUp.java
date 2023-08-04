package tzy.tinyPros.jvm.rtda.heap.methodarea;

/**
 * @author TPureZY
 * @since 2023/8/1 0:59
 **/
public class FieldLookUp {

    public static Field lookupFieldInClass(Klass holder, String name, String descriptor) {
        for (Klass cur = holder; cur != null; cur = cur.superClass) {
            if (cur.fields != null) {
                for (Field field : cur.fields) {
                    if (judge(field, name, descriptor)) {
                        return field;
                    }
                }
            }
        }
        return null;
    }

    public static Field lookupFieldInInterfaces(Klass[] inters, String name, String descriptor) {
        if (inters != null) {
            for (Klass inter : inters) {
                if (inter.fields != null) {
                    for (Field field : inter.fields) {
                        if (judge(field, name, descriptor)) {
                            return field;
                        }
                    }
                }
                Field ans = lookupFieldInInterfaces(inter.interfaces, name, descriptor);
                if (ans != null) {
                    return ans;
                }
            }
        }
        return null;
    }

    private static boolean judge(Field field, String name, String descriptor) {
        return field.name.equals(name) && field.descriptor.equals(descriptor);
    }
}
