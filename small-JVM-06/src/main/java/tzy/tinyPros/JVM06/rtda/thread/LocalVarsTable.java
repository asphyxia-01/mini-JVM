package tzy.tinyPros.JVM06.rtda.thread;

import tzy.tinyPros.JVM06.rtda.heap.methodarea.Object;


/**
 * @author TPureZY
 * @since 2023/7/11 15:20
 * <p>
 * 局部变量表(直属于栈帧Frame)
 * <p>
 * 在字节码翻译成一些列指令后，会发现指令中会给出操作的局部变量表中的位置，所以不需要在此处维护下标
 **/
public class LocalVarsTable {
    public final int maxSize;
    /**
     * 局部变量表由多个slot组成，slot中可以表示数字和引用类型
     * <p>
     * 对于Long、Double类型则需要占用两个slot，因为由两个u4组成，其他包括引用类型都是一个u4
     */
    public final Slot[] slots;

    public LocalVarsTable(int maxSize) {
        this.maxSize = maxSize;
        this.slots = new Slot[maxSize];
        for (int i = 0; i < maxSize; i++) {
            this.slots[i] = new Slot();
        }
    }

    /**
     * byte、char、short、int 都以int的形式进行的存储
     * @param idx 操作局部变量的下标
     */
    public void setInt(int idx, int val){
        this.slots[idx].setNum(val);
    }

    public int getInt(int idx){
        return this.slots[idx].getNum();
    }

    public void setLong(int idx,long val){
        this.slots[idx].setNum((int)(val>>32));
        // 高位截断
        this.slots[idx+1].setNum((int)val);
    }

    public long getLong(int idx){
        long high = this.slots[idx].getNum();
        long low = this.slots[idx+1].getNum();
        return (high<<32)|low;
    }

    public void setFloat(int idx,float val){
        // 保留比特位不变的情况下转为int
        this.setInt(idx,Float.floatToIntBits(val));
    }

    public float getFloat(int idx){
        // 保留比特位不变的情况下转为float
        return Float.intBitsToFloat(this.getInt(idx));
    }

    public void setDouble(int idx,double val){
        // 位运算不能作用于浮点数，将浮点数转为数字类型再处理，注意不要使用强转等，浮点数和数字的转型不是高位截断等直接的方式，而是保证字面量不发生变化的比特重排列转换，浮点数使用的是IEEE754格式
        this.setLong(idx,Double.doubleToLongBits(val));
    }

    public double getDouble(int idx){
        return Double.longBitsToDouble(this.getLong(idx));
    }

    public void setRef(int idx, Object ref){
        this.slots[idx].setRef(ref);
    }
    public Object getRef(int idx){
        return this.slots[idx].getRef(false);
    }

}
