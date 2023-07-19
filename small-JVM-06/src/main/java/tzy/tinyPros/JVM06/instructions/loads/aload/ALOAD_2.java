package tzy.tinyPros.JVM06.instructions.loads.aload;

import tzy.tinyPros.JVM06.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM06.rtda.thread.Frame;
import tzy.tinyPros.JVM06.rtda.heap.methodarea.Object;

/**
 * @author TPureZY
 * @since 2023/7/16 0:22
 * <p>
 * 要push的局部变量表中的变量的索引隐藏在指令中
 **/
public class ALOAD_2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Object ref = frame.getLocalVarsTable().getRef(2);
        frame.getOperandStack().pushRef(ref);
    }
}
