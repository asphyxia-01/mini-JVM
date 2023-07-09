package tzy.tinyPros.JVM03.classfile.attributes.impl;

import tzy.tinyPros.JVM03.classfile.ClassReader;
import tzy.tinyPros.JVM03.classfile.attributes.AttributeInfo;
import tzy.tinyPros.JVM03.classfile.attributes.BaseAttributeInfoElements;
import tzy.tinyPros.JVM03.classfile.constantpool.ConstantPool;

/**
 * @author TPureZY
 * @since 2023/7/10 0:12
 * <p>
 * 记录源文件名称
 **/
public class SourceFileAttribute extends BaseAttributeInfoElements implements AttributeInfo {

    private ConstantPool cp;

    /**
     * The string referenced by the sourcefile_index item will be interpreted as indicating the name of the
     * source
     * file from which this class file was compiled. It will not be interpreted as indicating the name of a
     * directory containing the file or an absolute path name for the file; such platform-specific additional
     * information must be supplied by the run-time interpreter or development tool at the time the file name is
     * actually used.
     */
    private int sourceFileIdx;

    public SourceFileAttribute(String attrName, int attrLen, ConstantPool cp) {
        super(attrName, attrLen);
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader cr) {
        this.sourceFileIdx = cr.readU2();
    }

    public String getSourceFileName() {
        return this.cp.getUtf8(this.sourceFileIdx);
    }
}
