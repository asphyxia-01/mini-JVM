package tzy.tinyPros.JVM09._native;

import java.util.Objects;

/**
 * @author TPureZY
 * @since 2023/7/31 17:16
 **/
public class MethodInfo {
    public final String name;
    public final String desc;

    private int hash;

    public MethodInfo(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    @Override
    public final int hashCode() {
        int h = hash;
        if (h == 0 && this.name != null && this.desc != null) {
            h = Objects.hashCode(this.name) ^ Objects.hashCode(this.desc);
        }
        this.hash = h;
        return h;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MethodInfo) {
            MethodInfo castObj = (MethodInfo) obj;
            if (this.name.equals(castObj.name)
                    && this.desc.equals(castObj.desc)) {
                return true;
            }
        }
        return false;
    }
}
