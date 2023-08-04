package tzy.tinyPros.jvm.instructions.reference;

import tzy.tinyPros.jvm.instructions.base.Index16Instruction;
import tzy.tinyPros.jvm.rtda.heap.constantpool.FieldRef;
import tzy.tinyPros.jvm.rtda.heap.constantpool.RunTimeConstantPool;
import tzy.tinyPros.jvm.rtda.heap.methodarea.Klass;
import tzy.tinyPros.jvm.rtda.heap.methodarea.Field;
import tzy.tinyPros.jvm.rtda.heap.methodarea.Object;
import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/20 16:41
 * <p>
 * 给变量进行赋值操作，需要三个操作数，常量池索引（指令给出）、要赋值的变量值（操作数栈弹出）、对象引用（操作数栈弹出）
 **/
public class PUT_FIELD extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Klass curClass = frame.getMethod().clazz;
        RunTimeConstantPool rp = curClass.runTimeConstantPool;
        FieldRef fr = (FieldRef) rp.constants[this.getIdx()];
        Field field = fr.resolvedField();
        if (field.isFinal()) {
            // 如果是final字段，只有直属的类并且是构造器方法才能对其做第一次赋值(即初始化)
            if (curClass != field.clazz || !frame.getMethod().name.equals("<init>")) {
                throw new IllegalAccessError();
            }
        }
        // 不能对静态变量使用此opcode
        if (field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        int slotId = field.slotId;
        switch (field.descriptor.substring(0, 1)) {
            case "B":
            case "Z":
            case "C":
            case "S":
            case "I":
                int val0 = stack.popInt();
                Object intObj = stack.popRef();
                assert intObj != null : "操作对象不能是Null";
                intObj.fields().setInt(slotId, val0);
                break;
            case "F":
                float val1 = stack.popFloat();
                Object floatObj = stack.popRef();
                assert floatObj != null : "操作对象不能是Null";
                floatObj.fields().setFloat(slotId, val1);
                break;
            case "J":
                long val2 = stack.popLong();
                Object longObj = stack.popRef();
                assert longObj != null : "操作对象不能是Null";
                longObj.fields().setLong(slotId, val2);
                break;
            case "D":
                double val3 = stack.popDouble();
                Object doubleObj = stack.popRef();
                assert doubleObj != null : "操作对象不能是Null";
                doubleObj.fields().setDouble(slotId, val3);
                break;
            case "L":
            case "[":
                Object val4 = stack.popRef();
                Object refObj = stack.popRef();
                assert refObj != null : "操作对象不能是Null";
                refObj.fields().setRef(slotId, val4);
                break;
            default:
                break;
        }
    }

}
