package tzy.tinyPros.JVM08.rtda.heap.methodarea;

import tzy.tinyPros.JVM08.classfile.MemberInfo;
import tzy.tinyPros.JVM08.rtda.heap.constantpool.AccessFlags;

/**
 * @author TPureZY
 * @since 2023/7/18 21:07
 **/
public class ClassMember {

    public final int accessFlags;
    public final String name;
    public final String descriptor;
    public final Class clazz;

    public ClassMember(MemberInfo info, Class clazz){
        this.accessFlags = info.getAccessFlags();
        this.name = info.getName();
        this.descriptor = info.getDescriptor();
        this.clazz = clazz;
    }

    public boolean isPublic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_PUBLIC);
    }

    public boolean isPrivate() {
        return 0 != (this.accessFlags & AccessFlags.ACC_PRIVATE);
    }

    public boolean isProtected() {
        return 0 != (this.accessFlags & AccessFlags.ACC_PROTECTED);
    }

    public boolean isStatic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_STATIC);
    }

    public boolean isFinal() {
        return 0 != (this.accessFlags & AccessFlags.ACC_FINAL);
    }

    public boolean isSynthetic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_SYNTHETIC);
    }

    /**
     * 判断当前字段能否被 Class var0 访问
     */
    public boolean isAccessibleTo(Class var0){
        if(this.isPublic()){
            return true;
        }
        if(this.isProtected()){
            // 同一个类 || 是this.clazz的子类 || 在同一个包中
            return var0 == this.clazz || var0.isExtendFrom(this.clazz)|| var0.getPackageName().equals(this.clazz.getPackageName());
        }
        // 就是default，需要在同一包中才能访问
        if(!this.isPrivate()){
            return var0.getPackageName().equals(this.clazz.getPackageName());
        }
        // 说明是private，只有自身能访问
        return var0 == this.clazz;
    }

}
