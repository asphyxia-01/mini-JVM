package tzy.tinyPros.jvm.instructions.extended;

import tzy.tinyPros.jvm.instructions.base.Instruction;
import tzy.tinyPros.jvm.instructions.base.InstructionBranch;
import tzy.tinyPros.jvm.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/17 17:25
 *
 * 根据操作数栈顶引用是否是null进行跳转
 **/
public class IFNULL extends InstructionBranch {
    @Override
    public void execute(Frame frame) {
        Object ref = frame.getOperandStack().popRef();
        if(ref == null){
            Instruction.branch(frame,this.getOffset());
        }
    }
}
