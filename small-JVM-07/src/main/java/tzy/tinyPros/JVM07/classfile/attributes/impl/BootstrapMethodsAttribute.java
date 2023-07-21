package tzy.tinyPros.JVM07.classfile.attributes.impl;

import tzy.tinyPros.JVM07.classfile.ClassReader;
import tzy.tinyPros.JVM07.classfile.attributes.AttributeInfo;
import tzy.tinyPros.JVM07.classfile.attributes.BaseAttributeInfoElements;

/**
 * @author TPureZY
 * @since 2023/7/8 23:25
 * <p>
 * 用于保存invokeddynamic指令引用的引导方式限定符
 **/
public class BootstrapMethodsAttribute extends BaseAttributeInfoElements implements AttributeInfo {

    private BootstrapMethod[] methods;

    public BootstrapMethodsAttribute(String attrName, int attrLen) {
        super(attrName, attrLen);
    }

    @Override
    public void readInfo(ClassReader cr) {
        int methodCount = cr.readU2();
        this.methods = new BootstrapMethod[methodCount];
        for (int i = 0; i < methodCount; i++) {
            this.methods[i] = new BootstrapMethod(cr.readU2(),cr.readU2sByHeadLen());
        }
    }

    public BootstrapMethod[] getMethods() {
        return methods;
    }

    static class BootstrapMethod {
        private int bootstrapMethodRef;
        private int[] bootstrapArguments;

        public BootstrapMethod(int ref, int[] args){
            this.bootstrapMethodRef = ref;
            this.bootstrapArguments = args;
        }

        public int getBootstrapMethodRef() {
            return bootstrapMethodRef;
        }

        public int[] getBootstrapArguments() {
            return bootstrapArguments;
        }
    }
}
