package tzy.tinyPros.JVM09.instructions.comparisons.ifcond;

import tzy.tinyPros.JVM09.instructions.base.Instruction;
import tzy.tinyPros.JVM09.instructions.base.InstructionBranch;
import tzy.tinyPros.JVM09.rtda.thread.Frame;
import tzy.tinyPros.JVM09.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/17 15:52
 **/
public class IFEQ extends InstructionBranch {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int i = stack.popInt();
        if(i == 0){
            Instruction.branch(frame,this.getOffset());
        }
    }
}