package tzy.tinyPros.jvm.rtda.heap.methodarea;

import tzy.tinyPros.jvm.rtda.thread.Slot;

/**
 * @author TPureZY
 * @since 2023/7/19 20:37
 **/
public class Slots {

    private Slot[] slots;

    public Slots(int slotLength) {
        if (slotLength > 0) {
            this.slots = new Slot[slotLength];
            for (int i = 0; i < slotLength; i++) {
                slots[i] = new Slot();
            }
        }
    }

    public void setInt(int idx, int val) {
        this.slots[idx].setNum(val);
    }

    public int getInt(int idx) {
        return this.slots[idx].getNum();
    }

    public void setLong(int idx, long val) {
        this.slots[idx].setNum((int) (val >> 32));
        this.slots[idx + 1].setNum((int) val);
    }

    public long getLong(int idx) {
        int high = this.slots[idx].getNum();
        int low = this.slots[idx + 1].getNum();
        return (((long) high) << 32) | ((long) low);
    }

    public void setFloat(int idx, float val) {
        this.slots[idx].setNum(Float.floatToIntBits(val));
    }

    public float getFloat(int idx) {
        return Float.intBitsToFloat(this.slots[idx].getNum());
    }

    public void setDouble(int idx, double val) {
        this.setLong(idx, Double.doubleToLongBits(val));
    }

    public double getDouble(int idx) {
        return Double.longBitsToDouble(this.getLong(idx));
    }

    public void setRef(int idx, Object val) {
        this.slots[idx].setRef(val);
    }

    public Object getRef(int idx) {
        return this.slots[idx].getRef(false);
    }

    public Slots _clone() {
        Slots newSlots = new Slots(this.slots.length);
        for (int i = 0; i < newSlots.slots.length; i++) {
            newSlots.slots[i] = this.slots[i]._clone();
        }
        return newSlots;
    }
}
