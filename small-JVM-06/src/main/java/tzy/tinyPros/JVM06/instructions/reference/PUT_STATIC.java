package tzy.tinyPros.JVM06.instructions.reference;

import tzy.tinyPros.JVM06.instructions.base.Index16Instruction;
import tzy.tinyPros.JVM06.rtda.heap.constantpool.FieldRef;
import tzy.tinyPros.JVM06.rtda.heap.constantpool.RunTimeConstantPool;
import tzy.tinyPros.JVM06.rtda.heap.methodarea.Class;
import tzy.tinyPros.JVM06.rtda.heap.methodarea.Field;
import tzy.tinyPros.JVM06.rtda.thread.Frame;
import tzy.tinyPros.JVM06.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/20 15:43
 * <p>
 * put_static ${con_index} 指令opcode后有一个操作数 内容指向运行时常量池中的一个索引，即某个静态变量的FieldRef的索引
 * <p>
 * 该opcode的作用是给类的某个静态变量进行赋值操作，要赋的值从操作数栈中弹出，对索引指向的FieldRef指向的Field进行赋值
 **/
public class PUT_STATIC extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Class curClass = frame.getMethod().clazz;
        RunTimeConstantPool rp = curClass.runTimeConstantPool;
        FieldRef fr = (FieldRef) rp.constants[this.getIdx()];
        Field field = fr.resolvedField();
        // 如果操作的不是static就报错
        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        // 对于final类型的变量除了 构造器可以初始化 其他地方不能再次赋值
        if (field.isFinal()) {
            throw new IllegalAccessError();
        }
        Class fClass = field.clazz;
        int slotId = field.slotId;

        switch (field.descriptor.substring(0, 1)) {
            case "B":
            case "Z":
            case "C":
            case "S":
            case "I":
                fClass.getStaticVars().setInt(slotId, stack.popInt());
                break;
            case "J":
                fClass.getStaticVars().setLong(slotId, stack.popLong());
                break;
            case "F":
                fClass.getStaticVars().setFloat(slotId, stack.popFloat());
                break;
            case "D":
                fClass.getStaticVars().setDouble(slotId, stack.popDouble());
                break;
            case "L":
            case "[":
                fClass.getStaticVars().setRef(slotId, stack.popRef());
                break;
            default:
                break;
        }

    }
}
