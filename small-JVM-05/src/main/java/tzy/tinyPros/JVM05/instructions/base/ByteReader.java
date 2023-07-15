package tzy.tinyPros.JVM05.instructions.base;

/**
 * @author TPureZY
 * @since 2023/7/15 15:57
 **/
public class ByteReader {
    private byte[] code;
    /**
     * 记录当前所在的字节下标
     */
    private int index;

    public void reset(byte[] code, int index) {
        this.code = code;
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public byte readByte() {
        return this.code[index++];
    }

    public short readShort() {
        short var0 = this.code[this.index++];
        short var1 = this.code[this.index++];
        return (short) ((var0 << 8) | var1);
    }

    public int readInt() {
        int var0 = this.code[this.index++];
        int var1 = this.code[this.index++];
        int var2 = this.code[this.index++];
        int var3 = this.code[this.index++];
        return (var0 << 24) | (var1 << 16) | (var2 << 8) | var3;
    }

    /**
     * 主要用在 tableswitch 和 lookupswitch 指令
     */
    public int[] readInts(int len) {
        int[] ints = new int[len];
        for (int i = 0; i < len; i++) {
            ints[i] = this.readInt();
        }
        return ints;
    }

    /**
     * 主要用在 tableswitch 和 lookupswitch 指令
     * <p>
     * A lookupswitch is a variable-length instruction. Immediately after the lookupswitch opcode, between zero and three bytes must act as padding, such that defaultbyte1 begins at an address that is a multiple of four bytes from the start of the current method (the opcode of its first instruction).
     */
    public void skipPadding() {
        // index 必须是4的倍数
        while (this.index % 4 != 0) {
            this.index++;
        }
    }

}
