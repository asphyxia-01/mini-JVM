package tzy.tinyPros.JVM08.rtda.heap.methodarea;

/**
 * @author TPureZY
 * @since 2023/7/21 20:31
 **/
public class MethodLookup {
    public static Method loopupMethodInClass(Klass clazz, String name, String descriptor) {
        for (Klass cur = clazz; cur != null; cur = cur.superClass) {
            for (Method method : cur.methods) {
                if (judge(method, name, descriptor)) {
                    return method;
                }
            }
        }
        return null;
    }

    public static Method loopupMethodInInterfaces(Klass[] inters, String name, String descriptor) {
        for (Klass inter : inters) {
            for (Method method : inter.methods) {
                if (judge(method, name, descriptor)) {
                    return method;
                }
            }
            Method ans = loopupMethodInInterfaces(inter.interfaces, name, descriptor);
            if (ans != null) {
                return ans;
            }
        }
        return null;
    }

    private static boolean judge(Method method, String name, String descriptor) {
        return method.name.equals(name) && method.descriptor.equals(descriptor);
    }
}
