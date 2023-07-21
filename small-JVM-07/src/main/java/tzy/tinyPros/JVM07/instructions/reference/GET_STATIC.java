package tzy.tinyPros.JVM07.instructions.reference;

import tzy.tinyPros.JVM07.instructions.base.Index16Instruction;
import tzy.tinyPros.JVM07.rtda.heap.constantpool.FieldRef;
import tzy.tinyPros.JVM07.rtda.heap.methodarea.Field;
import tzy.tinyPros.JVM07.rtda.heap.methodarea.Slots;
import tzy.tinyPros.JVM07.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/20 16:27
 * <p>
 * 作用与PUT_STATIC指令相反，取出静态变量的值并且推入操作数栈顶
 **/
public class GET_STATIC extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        FieldRef ref = (FieldRef) frame.getMethod().clazz.runTimeConstantPool.constants[this.getIdx()];
        Field field = ref.resolvedField();
        // 如果操作的不是static就报错
        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        Slots staticVars = field.clazz.staticVars;
        switch (field.descriptor.substring(0, 1)) {
            case "B":
            case "Z":
            case "C":
            case "S":
            case "I":
                frame.getOperandStack().pushInt(staticVars.getInt(field.slotId));
                break;
            case "J":
                frame.getOperandStack().pushLong(staticVars.getLong(field.slotId));
                break;
            case "F":
                frame.getOperandStack().pushFloat(staticVars.getFloat(field.slotId));
                break;
            case "D":
                frame.getOperandStack().pushDouble(staticVars.getDouble(field.slotId));
                break;
            case "L":
            case "[":
                frame.getOperandStack().pushRef(staticVars.getRef(field.slotId));
                break;
            default:
                break;
        }
    }
}
