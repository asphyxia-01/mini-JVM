package tzy.tinyPros.JVM09.instructions.extended;

import tzy.tinyPros.JVM09.instructions.base.ByteReader;
import tzy.tinyPros.JVM09.instructions.base.Instruction;
import tzy.tinyPros.JVM09.instructions.loads.aload.ALOAD;
import tzy.tinyPros.JVM09.instructions.loads.dload.DLOAD;
import tzy.tinyPros.JVM09.instructions.loads.fload.FLOAD;
import tzy.tinyPros.JVM09.instructions.loads.iload.ILOAD;
import tzy.tinyPros.JVM09.instructions.loads.lload.LLOAD;
import tzy.tinyPros.JVM09.instructions.math.iinc.IINC;
import tzy.tinyPros.JVM09.instructions.stores.astore.ASTORE;
import tzy.tinyPros.JVM09.instructions.stores.dstore.DSTORE;
import tzy.tinyPros.JVM09.instructions.stores.fstore.FSTORE;
import tzy.tinyPros.JVM09.instructions.stores.istore.ISTORE;
import tzy.tinyPros.JVM09.instructions.stores.lstore.LSTORE;
import tzy.tinyPros.JVM09.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/17 17:11
 * <p>
 * 加载类指令、存储类指令、ret指令、iinc指令需要按照索引访问局部变量表，一般而言u1即可（因为局部变量表大小基本不会超过256）但是如果出现了特例，则需要使用wide指令拓展前述指令
 * <p>
 * 例如：wide iinc ${index} ${constVal} 使用wide标识后面的opcode是拓展过的
 **/
public class WIDE implements Instruction {

    private Instruction modifiedInstruction;

    @Override
    public void fetchOperands(ByteReader br) {
        byte opcode = br.read1Byte();
        switch (opcode) {
            case 0x15:
                ILOAD inst_iload = new ILOAD();
                inst_iload.setIdx(br.read2Byte());
                this.modifiedInstruction = inst_iload;
                break;
            case 0x16:
                LLOAD inst_lload = new LLOAD();
                inst_lload.setIdx(br.read2Byte());
                this.modifiedInstruction = inst_lload;
                break;
            case 0x17:
                FLOAD inst_fload = new FLOAD();
                inst_fload.setIdx(br.read2Byte());
                this.modifiedInstruction = inst_fload;
                break;
            case 0x18:
                DLOAD inst_dload = new DLOAD();
                inst_dload.setIdx(br.read2Byte());
                this.modifiedInstruction = inst_dload;
                break;
            case 0x19:
                ALOAD inst_aload = new ALOAD();
                inst_aload.setIdx(br.read2Byte());
                this.modifiedInstruction = inst_aload;
                break;
            case 0x36:
                ISTORE inst_istore = new ISTORE();
                inst_istore.setIdx(br.read2Byte());
                this.modifiedInstruction = inst_istore;
                break;
            case 0x37:
                LSTORE inst_lstore = new LSTORE();
                inst_lstore.setIdx(br.read2Byte());
                this.modifiedInstruction = inst_lstore;
                break;
            case 0x38:
                FSTORE inst_fstore = new FSTORE();
                inst_fstore.setIdx(br.read2Byte());
                this.modifiedInstruction = inst_fstore;
                break;
            case 0x39:
                DSTORE inst_dstore = new DSTORE();
                inst_dstore.setIdx(br.read2Byte());
                this.modifiedInstruction = inst_dstore;
                break;
            case 0x3a:
                ASTORE inst_astore = new ASTORE();
                inst_astore.setIdx(br.read2Byte());
                this.modifiedInstruction = inst_astore;
                break;
            case (byte) 0x84:
                IINC inst_iinc = new IINC();
                inst_iinc.setIndex(br.read2Byte());
                this.modifiedInstruction = inst_iinc;
                break;
            case (byte) 0xa9:
                // ret 0xa9
                throw new RuntimeException("Unsupported opcode: 0xa9!");
        }
    }

    @Override
    public void execute(Frame frame) {
        this.modifiedInstruction.execute(frame);
    }
}
