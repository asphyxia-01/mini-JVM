package tzy.tinyPros.JVM05.rtda.thread;

/**
 * @author TPureZY
 * @since 2023/7/11 16:22
 * <p>
 * 操作数栈，不同于局部变量表，这个是栈需要显式维护栈顶指针，此处不考虑任何异常
 **/
public class OperandStack {

    public final int maxSize;
    private final Slot[] slots;
    private int top;

    public OperandStack(int maxSize) {
        this.maxSize = maxSize;
        this.slots = new Slot[maxSize];
        for (int i = 0; i < maxSize; i++) {
            this.slots[i] = new Slot();
        }
        // 始终指向栈顶元素
        this.top = -1;
    }

    /**
     * byte、char、short、int都可以按照int进行处理
     *
     * @param val
     */
    public void pushInt(int val) {
        this.slots[++this.top].setNum(val);
    }

    public int popInt() {
        return this.slots[this.top--].getNum();
    }

    public void pushFloat(float val) {
        this.slots[++this.top].setNum(Float.floatToIntBits(val));
    }

    public float popFloat() {
        return Float.intBitsToFloat(this.slots[this.top--].getNum());
    }

    public void pushLong(long val) {
        // 强转高位截断
        this.slots[++this.top].setNum((int) val);
        // 大端存储
        this.slots[++this.top].setNum((int) (val >> 32));
    }

    public long popLong() {
        return (((long) this.slots[this.top--].getNum()) << 32) | ((long) this.slots[this.top--].getNum());
    }

    public void pushDouble(double val) {
        // 转为long进行处理
        this.pushLong(Double.doubleToLongBits(val));
    }

    public double popDouble() {
        long var0 = this.popLong();
        return Double.longBitsToDouble(var0);
    }

    public void pushRef(Object ref) {
        this.slots[++this.top].setRef(ref);
    }

    public Object popRef() {
        return this.slots[this.top--].getRef(true);
    }
}
