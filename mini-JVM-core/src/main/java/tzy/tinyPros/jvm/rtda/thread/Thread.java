package tzy.tinyPros.jvm.rtda.thread;

import tzy.tinyPros.jvm.rtda.heap.methodarea.Method;

/**
 * @author TPureZY
 * @since 2023/7/11 14:37
 * <p>
 * 定义线程，运行时数据区可以简单分为线程私有和线程共享的两部分，结构如下
 * <blockquote><pre>
 *     Run-Time Data Area{
 *         Thread{
 *             PC
 *             JVM stack{
 *                 maxStackDepth
 *                 *Frame{
 *                     Local Variables
 *                     Operand Stack
 *                 }
 *             }
 *         }
 *         Heap{
 *             Method Area{
 *                 Class{
 *                     Run-time Constant Pool
 *                     Field And Method
 *                     Method Code
 *                 }
 *                 Object
 *             }
 *         }
 *     }
 * </blockquote></pre>
 * 线程私有：PC、栈（局部变量表、操作数栈、方法运行状态）
 * <p>
 * 线程共享：堆（存放对象实例）、方法区（类字段和方法、方法字节码、运行时常量池）
 **/
public class Thread {

    public final String name;
    /**
     * Program Counter 寄存器
     */
    private int pc;
    /**
     * 栈（由栈帧Frame组成）
     */
    private JVMStack stack;

    public Thread(String name, int maxStackDepth) {
        this.name = name;
        this.stack = new JVMStack(maxStackDepth);
    }

    public Thread(String name) {
        this(name, 1024);
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int getPc() {
        return this.pc;
    }

    public Frame popFrame() {
        return this.stack.pop();
    }

    public void pushFrame(Frame frame) {
        this.stack.push(frame);
    }

    public Frame topFrame() {
        return this.stack.top();
    }

    public Frame newFrame(Method method) {
        return new Frame(this, method);
    }

    public boolean isStackEmpty() {
        return this.stack.isEmpty();
    }

    public Frame[] getFrames() {
        return this.stack.getFrames();
    }

    public void clearJVMStack() {
        this.stack.clear();
    }

}
