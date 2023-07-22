package tzy.tinyPros.JVM08.rtda.heap.constantpool;

import tzy.tinyPros.JVM08.classfile.constantpool.impl.ConstantMemberRefInfo;
import tzy.tinyPros.JVM08.rtda.heap.methodarea.Class;
import tzy.tinyPros.JVM08.rtda.heap.methodarea.Field;

/**
 * @author TPureZY
 * @since 2023/7/19 17:12
 **/
public class FieldRef extends MemberRef {

    private Field field;

    private FieldRef(RunTimeConstantPool runTimeConstantPool, ConstantMemberRefInfo info) {
        super(runTimeConstantPool, info);
    }

    public static FieldRef newFieldRef(RunTimeConstantPool runTimeConstantPool, ConstantMemberRefInfo info) {
        return new FieldRef(runTimeConstantPool, info);
    }

    public Field resolvedField() {
        if (this.field == null) {
            try {
                this.resolveFieldRef();
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (this.field.isAccessibleTo(this.runTimeConstantPool.clazz)) {
            return this.field;
        }
        return this.field;
    }

    private void resolveFieldRef() throws NoSuchFieldException, IllegalAccessException {
        // 持有这个字段的类
        Class holder = this.getClazz();
        // 访问这个字段的类
        Class visitor = this.runTimeConstantPool.clazz;

        this.field = this.lookupField(holder, this.name, this.descriptor);
        if (field == null) {
            throw new NoSuchFieldException();
        }
        if (!this.field.isAccessibleTo(visitor)) {
            throw new IllegalAccessException();
        }
    }

    /**
     * 使用递归查找
     *
     * @param holder     字段理论上的持有者
     * @param name       字段名称
     * @param descriptor 字段描述符
     */
    private Field lookupField(Class holder, String name, String descriptor) {
        // 终止条件
        if (holder == null) {
            return null;
        }
        // 先找当前Class的Fields
        for (Field var0 : holder.fields) {
            if (var0.name.equals(name) && var0.descriptor.equals(descriptor)) {
                return var0;
            }
        }
        // 再找接口是否持有，接口中的默认都是static final修饰的字段
        for (Class var1 : holder.interfaces) {
            Field ans = this.lookupField(var1, name, descriptor);
            if (ans != null) {
                return ans;
            }
        }
        // 再找父类是否持有这个字段
        return lookupField(holder.superClass, name, descriptor);
    }

}
