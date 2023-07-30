package tzy.tinyPros.JVM09.instructions.reference;

import tzy.tinyPros.JVM09.instructions.base.ByteReader;
import tzy.tinyPros.JVM09.instructions.base.Instruction;
import tzy.tinyPros.JVM09.rtda.heap.constantpool.ClassRef;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Klass;
import tzy.tinyPros.JVM09.rtda.thread.Frame;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Object;
import tzy.tinyPros.JVM09.rtda.thread.OperandStack;

import java.util.Arrays;

/**
 * 创建多维数组
 * <p>
 * 2+n个操作数：运行时常量池索引（2byte，来自指令，获取数组类描述符，注意区别于 anew_array 指令，此处获取的是数组类描述符，而不是数组元素描述符）；数组维度（1byte，来自指令，注意是维度不是长度）；n（维度）个表述各个维度下长度的值cnt（来自操作数栈）
 *
 * @author TPureZY
 * @since 2023/7/26 23:23
 **/
public class MULTI_ANEW_ARRAY implements Instruction {

    private int idx;
    private int dimensions;

    @Override
    public void fetchOperands(ByteReader br) {
        this.idx = br.readShort();
        this.dimensions = br.readByte();
    }

    @Override
    public void execute(Frame frame) {
        Klass arrClass = ((ClassRef) frame.getMethod().clazz.runTimeConstantPool.constants[this.idx]).getClazz();
        int[] lengthPerDimension = this.acquireAndCheckPerLength(frame.getOperandStack(), this.dimensions);
        Object arr = newMultiDimensionalArray(arrClass, lengthPerDimension);
        frame.getOperandStack().pushRef(arr);
    }

    private int[] acquireAndCheckPerLength(OperandStack stack, int dimensions) {
        int[] perLength = new int[dimensions];
        for (int i = dimensions - 1; i >= 0; i--) {
            perLength[i] = stack.popInt();
            if (perLength[i] < 0) {
                throw new NegativeArraySizeException();
            }
        }
        return perLength;
    }

    private Object newMultiDimensionalArray(Klass arrClass, int[] perLength) {
        Object upArr = arrClass.newArray(perLength[0]);
        if (perLength.length > 1) {
            Object[] refs = upArr.refs();
            for (int i = 0; i < refs.length; i++) {
                refs[i] = newMultiDimensionalArray(arrClass.getComponentClassFromArrayClass(), Arrays.copyOfRange(perLength, 1, perLength.length));
            }
        }
        return upArr;
    }

}
