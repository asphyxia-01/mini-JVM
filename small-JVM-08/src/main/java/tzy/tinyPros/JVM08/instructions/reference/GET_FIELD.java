package tzy.tinyPros.JVM08.instructions.reference;

import tzy.tinyPros.JVM08.instructions.base.Index16Instruction;
import tzy.tinyPros.JVM08.rtda.heap.constantpool.FieldRef;
import tzy.tinyPros.JVM08.rtda.heap.constantpool.RunTimeConstantPool;
import tzy.tinyPros.JVM08.rtda.heap.methodarea.Class;
import tzy.tinyPros.JVM08.rtda.heap.methodarea.Field;
import tzy.tinyPros.JVM08.rtda.heap.methodarea.Object;
import tzy.tinyPros.JVM08.rtda.thread.Frame;
import tzy.tinyPros.JVM08.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/20 17:50
 * <p>
 * 与PUT_FIELD相反，是两个操作数，该opcode是从对象变量中取出值并推入操作数栈中
 * <p>
 * 操作数：FieldRef的索引（指令给出）、操作对象（操作数栈）
 **/
public class GET_FIELD extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Class curClass = frame.getMethod().clazz;
        RunTimeConstantPool rp = curClass.runTimeConstantPool;
        FieldRef fieldRef = (FieldRef) rp.constants[this.getIdx()];
        Field field = fieldRef.resolvedField();
        // 只能操作实例变量，不能操作类变量
        if (field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        Object ref = stack.popRef();
        switch (field.descriptor.substring(0, 1)) {
            case "B":
            case "Z":
            case "C":
            case "S":
            case "I":
                stack.pushInt(ref.fields.getInt(field.slotId));
                break;
            case "F":
                stack.pushFloat(ref.fields.getFloat(field.slotId));
                break;
            case "J":
                stack.pushLong(ref.fields.getLong(field.slotId));
                break;
            case "D":
                stack.pushDouble(ref.fields.getDouble(field.slotId));
                break;
            case "L":
            case "[":
                stack.pushRef(ref.fields.getRef(field.slotId));
                break;
            default:
                break;
        }
    }
}
