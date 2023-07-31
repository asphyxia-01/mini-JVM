package tzy.tinyPros.JVM09.rtda.heap.constantpool;

import tzy.tinyPros.JVM09.classfile.constantpool.impl.ConstantMemberRefInfo;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.FieldLookUp;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Klass;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Field;

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
        Klass holder = this.getClazz();
        // 访问这个字段的类
        Klass visitor = this.runTimeConstantPool.clazz;

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
    private Field lookupField(Klass holder, String name, String descriptor) {
        // 先从自身和超类找起，此时holder不管是接口还是类都没关系（接口也是类的一种）
        Field ans = FieldLookUp.lookupFieldInClass(holder, name, descriptor);
        if (ans == null) {
            ans = FieldLookUp.lookupFieldInInterfaces(holder.interfaces, name, descriptor);
        }
        return ans;
    }

}
