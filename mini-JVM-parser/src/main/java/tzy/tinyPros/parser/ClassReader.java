package tzy.tinyPros.parser;


import java.math.BigInteger;

/**
 * @author TPureZY
 * @since 2023/7/6 15:50
 * <p>
 * class文件作为字节流处理，用ClassReader来读取分析字节
 * JVM主要有三种类型数据，分别是u1、u2、u4，都是无符号数据
 * 由于都是无符号数据，在这里u1、u2使用int类型，u4使用long类型
 **/
public class ClassReader {
    /**
     * class文件字节流数据
     */
    private byte[] data;

    /**
     * 游标，标记下次读取的起始位置
     */
    private int cursor;

    public ClassReader(byte[] data) {
        this.data = data;
        this.cursor = 0;
    }

    public byte[] readBytes(int length) {
        return readByte(length);
    }

    // u1
    public int readU1() {
        return byte2Int(readByte(1));
    }

    // u2
    public int readU2() {
        return byte2Int(readByte(2));
    }

    // u4
    public long readU4() {
        return byte2Long(readByte(4));
    }

    public int[] readU2sByHeadLen() {
        int len = this.readU2();
        int[] var0 = new int[len];
        for (int i = 0; i < len; i++) {
            var0[i] = this.readU2();
        }
        return var0;
    }

    public int readUInt32ToInt() {
        return byte2Int(readByte(4));
    }

    public long readUInt64ToLong() {
        return byte2Long(readByte(8));
    }

    public float readUInt32ToFloat() {
        return byte2Float(readByte(4));
    }

    public double readUInt64ToDouble() {
        return byte2Double(readByte(8));
    }


    private byte[] readByte(int length) {
        if(cursor>=data.length){
            return null;
        }
        byte[] target = new byte[length];
        System.arraycopy(data, cursor, target, 0, length);
        // 移动游标
        cursor += length;
        return target;
    }

    private int byte2Int(byte[] val) {
        return new BigInteger(1, val).intValue();
    }

    private long byte2Long(byte[] val) {
        return new BigInteger(1, val).longValue();
    }

    private float byte2Float(byte[] val) {
        int var0 = byte2Int(val);
        return Float.intBitsToFloat(var0);
    }

    private double byte2Double(byte[] val) {
        long var0 = byte2Long(val);
        return Double.longBitsToDouble(var0);
    }

    public void setCursor(int newCursor){
        this.cursor = newCursor;
    }
    public int getCursor(){
        return this.cursor;
    }

}
