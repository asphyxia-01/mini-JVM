package tzy.tinyPros.JVM06.instructions.base;

/**
 * @author TPureZY
 * @since 2023/7/15 15:57
 **/
public class ByteReader {
    private byte[] code;
    /**
     * 程序计数器：其实就是指令所在的地址（单位：byte），这里表现为索引（或第几个字节）
     */
    private int pc;

    public void reset(byte[] code, int pc) {
        this.code = code;
        this.pc = pc;
    }

    public int getPC() {
        return this.pc;
    }

    public byte readByte() {
        return this.code[pc++];
    }

    public short readShort() {
        short var0 = this.code[this.pc++];
        short var1 = this.code[this.pc++];
        return (short) ((var0 << 8) | var1);
    }

    public int readInt() {
        int var0 = this.code[this.pc++];
        int var1 = this.code[this.pc++];
        int var2 = this.code[this.pc++];
        int var3 = this.code[this.pc++];
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
        while (this.pc % 4 != 0) {
            this.pc++;
        }
    }

}
