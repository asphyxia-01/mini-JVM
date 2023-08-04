package tzy.tinyPros.jvm.instructions.reference;

import tzy.tinyPros.jvm.instructions.base.ClassLogicClinit;
import tzy.tinyPros.jvm.instructions.base.Index16Instruction;
import tzy.tinyPros.jvm.instructions.base.MethodLogicInvoke;
import tzy.tinyPros.jvm.rtda.heap.constantpool.MethodRef;
import tzy.tinyPros.jvm.rtda.heap.constantpool.RunTimeConstantPool;
import tzy.tinyPros.jvm.rtda.heap.methodarea.Klass;
import tzy.tinyPros.jvm.rtda.heap.methodarea.Method;
import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/22 0:18
 * <p>
 * invoke_static 指令用来调用静态方法
 **/
public class INVOKE_STATIC extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        RunTimeConstantPool runTimeConstantPool = frame.getMethod().clazz.runTimeConstantPool;
        MethodRef mr = (MethodRef) runTimeConstantPool.constants[this.getIdx()];
        Method method = mr.resolvedMethod();
        if (!method.isStatic()) {
            throw new IncompatibleClassChangeError();
        }

        Klass clazz = method.clazz;
        if (!clazz.isClinitStarted()) {
            // 回退当前pc的位置，回到这条指令的起点
            frame.revertNextPC();
            ClassLogicClinit.clinitClass(frame.getThread(), clazz);
            return;
        }

        // 将method作为栈帧入栈
        MethodLogicInvoke.invokeMethod(frame, method);
    }
}
