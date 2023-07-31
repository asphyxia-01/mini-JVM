package tzy.tinyPros.JVM09.instructions.reference;

import tzy.tinyPros.JVM09.instructions.base.ClassLogicClinit;
import tzy.tinyPros.JVM09.instructions.base.Index16Instruction;
import tzy.tinyPros.JVM09.rtda.heap.constantpool.FieldRef;
import tzy.tinyPros.JVM09.rtda.heap.constantpool.RunTimeConstantPool;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Klass;
import tzy.tinyPros.JVM09.rtda.heap.methodarea.Field;
import tzy.tinyPros.JVM09.rtda.thread.Frame;
import tzy.tinyPros.JVM09.rtda.thread.OperandStack;

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
        Klass curClass = frame.getMethod().clazz;
        RunTimeConstantPool rp = curClass.runTimeConstantPool;
        FieldRef fr = (FieldRef) rp.constants[this.getIdx()];
        Field field = fr.resolvedField();
        // 如果没有静态初始化则先初始化
        if (!field.clazz.isClinitStarted()) {
            frame.revertNextPC();
            ClassLogicClinit.clinitClass(frame.getThread(), field.clazz);
            return;
        }
        // 如果操作的不是static就报错
        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        // 对于final类型的变量除了 构造器 可以初始化 其他地方不能再次赋值
        if (field.isFinal()) {
            if (!(("<clinit>".equals(frame.getMethod().name))
                    && curClass == field.clazz)) {
                throw new IllegalAccessError();
            }
        }
        Klass fClass = field.clazz;
        int slotId = field.slotId;

        switch (field.descriptor.substring(0, 1)) {
            case "B":
            case "Z":
            case "C":
            case "S":
            case "I":
                fClass.staticVars.setInt(slotId, stack.popInt());
                break;
            case "J":
                fClass.staticVars.setLong(slotId, stack.popLong());
                break;
            case "F":
                fClass.staticVars.setFloat(slotId, stack.popFloat());
                break;
            case "D":
                fClass.staticVars.setDouble(slotId, stack.popDouble());
                break;
            case "L":
            case "[":
                fClass.staticVars.setRef(slotId, stack.popRef());
                break;
            default:
                break;
        }

    }
}
