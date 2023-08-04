package tzy.tinyPros.jvm.instructions.loads.aload;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.heap.methodarea.Object;


/**
 * @author TPureZY
 * @since 2023/7/16 0:22
 * <p>
 * 要push的局部变量表中的变量的索引隐藏在指令中
 **/
public class ALOAD_3 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Object ref = frame.getLocalVarsTable().getRef(3);
        frame.getOperandStack().pushRef(ref);
    }
}
