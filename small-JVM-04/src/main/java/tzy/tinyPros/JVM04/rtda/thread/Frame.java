package tzy.tinyPros.JVM04.rtda.thread;

/**
 * @author TPureZY
 * @since 2023/7/11 15:01
 * <p>
 * 栈帧（方法执行状态、局部变量表、操作数栈）
 **/
public class Frame {
    /**
     * 局部变量表
     */
    private LocalVarsTable localVarsTable;
    /**
     * 操作数栈
     */
    private OperandStack operandStack;

    Frame next;

    public Frame(int maxVarsLength, int maxStackDepth) {
        this.localVarsTable = new LocalVarsTable(maxVarsLength);
        this.operandStack = new OperandStack(maxStackDepth);
    }

    public LocalVarsTable getLocalVarsTable(){
        return this.localVarsTable;
    }
    public OperandStack getOperandStack(){
        return this.operandStack;
    }

}
