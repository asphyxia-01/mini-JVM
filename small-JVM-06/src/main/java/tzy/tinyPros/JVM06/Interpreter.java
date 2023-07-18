package tzy.tinyPros.JVM06;

import com.alibaba.fastjson.JSON;
import tzy.tinyPros.JVM06.classfile.MemberInfo;
import tzy.tinyPros.JVM06.classfile.attributes.impl.CodeAttribute;
import tzy.tinyPros.JVM06.instructions.InstructionMapper;
import tzy.tinyPros.JVM06.instructions.base.ByteReader;
import tzy.tinyPros.JVM06.instructions.base.Instruction;
import tzy.tinyPros.JVM06.rtda.thread.Frame;
import tzy.tinyPros.JVM06.rtda.thread.Thread;

import java.util.Objects;

/**
 * @author TPureZY
 * @since 2023/7/17 17:37
 * <p>
 * 指令集解释器
 **/
public class Interpreter {

    Interpreter(MemberInfo info) {
        CodeAttribute codeInfo = info.getCodeAttributeInfo();
        int maxStack = codeInfo.getMaxStack();
        int maxLocals = codeInfo.getMaxLocals();
        byte[] code = codeInfo.getCode();
        // 模拟JVM创建主线程运行 main(String[] args) 方法
        Thread thread = new Thread();
        Frame frame = new Frame(thread, maxLocals, maxStack);
        thread.pushFrame(frame);
        this.loop(thread, code);
    }

    private void loop(Thread thread, byte[] code) {
        Frame frame = thread.popFrame();
        ByteReader br = new ByteReader();

        while (1 == 1) {
            int nextPC = frame.getNextPC();
            thread.setPc(nextPC);
            // 取指
            br.reset(code,nextPC);
            byte opcode = br.readByte();
            Instruction instruction = InstructionMapper.acquireInstruction(opcode);
            if (Objects.isNull(instruction)) {
                System.out.printf("尚未实现该指令：%s\n", byteToHexString(new byte[]{opcode}));
                break;
            }
            // 间指
            instruction.fetchOperands(br);
            // PC++
            frame.setNextPC(br.getPC());
            // 输出结果
            System.out.println("寄存器(指令)：" + byteToHexString(new byte[]{opcode}) + " -> " + instruction.getClass().getSimpleName() + " => 局部变量表：" + JSON.toJSONString(frame.getOperandStack().getSlots()) + " 操作数栈：" + JSON.toJSONString(frame.getOperandStack().getSlots()));
            // 执行
            instruction.execute(frame);
        }
    }

    private static String byteToHexString(byte[] codes) {
        StringBuilder sb = new StringBuilder();
        sb.append("0x");
        for (byte b : codes) {
            int value = b & 0xFF;
            String strHex = Integer.toHexString(value);
            if (strHex.length() < 2) {
                strHex = "0" + strHex;
            }
            sb.append(strHex);
        }
        return sb.toString();
    }

}
