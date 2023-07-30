package tzy.tinyPros.JVM09.rtda.thread;

import tzy.tinyPros.JVM09.rtda.heap.methodarea.Method;

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

    /**
     * 标识哪个线程再运行此方法
     */
    private Thread thread;

    /**
     * 该栈帧对应的方法
     */
    private Method method;

    private int nextPC;

    Frame next;

    public Frame(Thread thread, Method method) {
        this.thread = thread;
        this.method = method;
        this.localVarsTable = new LocalVarsTable(this.method.maxLocalVarsLength);
        this.operandStack = new OperandStack(this.method.maxStackDepth);
    }

    public LocalVarsTable getLocalVarsTable() {
        return this.localVarsTable;
    }

    public OperandStack getOperandStack() {
        return this.operandStack;
    }

    public Thread getThread() {
        return this.thread;
    }

    public int getNextPC() {
        return this.nextPC;
    }

    public Method getMethod() {
        return method;
    }

    public void setNextPC(int var0) {
        this.nextPC = var0;
    }

    /**
     * 恢复到读取这一条指令之前，thread中会记录当前正在执行的指令的pc
     */
    public void revertNextPC() {
        this.nextPC = this.thread.getPc();
    }
}
