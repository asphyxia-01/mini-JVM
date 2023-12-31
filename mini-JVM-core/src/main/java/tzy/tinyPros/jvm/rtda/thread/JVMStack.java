package tzy.tinyPros.jvm.rtda.thread;

/**
 * @author TPureZY
 * @since 2023/7/11 15:07
 * <p>
 * 线程所持有的栈(由栈帧组成)
 **/
public class JVMStack {

    /**
     * 可以由用户通过JVM指令设置
     */
    public final int maxStackDepth;
    private int curDepth;
    private Frame top;

    public JVMStack(int maxStackDepth) {
        this.maxStackDepth = maxStackDepth;
    }

    public void push(Frame frame) {
        if (this.curDepth >= this.maxStackDepth) {
            throw new StackOverflowError();
        }
        if (this.top != null) {
            frame.next = this.top;
        }
        this.top = frame;
        this.curDepth++;
    }

    public Frame pop() {
        if (this.top == null) {
            throw new RuntimeException("JVM stack is empty!");
        }
        Frame var1 = this.top;
        this.top = this.top.next;
        this.curDepth--;
        return var1;
    }

    public Frame top() {
        if (this.top == null) {
            throw new RuntimeException("JVM stack is empty!");
        }
        return this.top;
    }

    public boolean isEmpty() {
        return this.curDepth == 0;
    }

    public Frame[] getFrames() {
        Frame[] frames = new Frame[this.curDepth];
        int idx = 0;
        for (Frame cur = this.top; cur != null; cur = cur.next) {
            frames[idx++] = cur;
        }
        return frames;
    }

    public void clear() {
        this.top = null;
        this.curDepth = 0;
    }
}
