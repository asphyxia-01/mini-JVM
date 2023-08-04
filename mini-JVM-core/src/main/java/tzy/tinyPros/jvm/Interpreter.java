package tzy.tinyPros.jvm;

import com.alibaba.fastjson.JSON;
import tzy.tinyPros.jvm.instructions.InstructionMapper;
import tzy.tinyPros.jvm.instructions.base.ByteReader;
import tzy.tinyPros.jvm.instructions.base.Instruction;
import tzy.tinyPros.jvm.rtda.heap.ClassLoader;
import tzy.tinyPros.jvm.rtda.heap.methodarea.Klass;
import tzy.tinyPros.jvm.rtda.heap.methodarea.Method;
import tzy.tinyPros.jvm.rtda.heap.methodarea.Object;
import tzy.tinyPros.jvm.rtda.heap.methodarea.StringPool;
import tzy.tinyPros.jvm.rtda.thread.Frame;
import tzy.tinyPros.jvm.rtda.thread.Thread;

import java.util.Objects;

/**
 * @author TPureZY
 * @since 2023/7/17 17:37
 * <p>
 * 指令集解释器
 **/
public class Interpreter {

    /**
     * 只是用来对输出的信息行进行编号用的，对JVM实现没有任何相关
     */
    private static int line;

    public static void interpret(Method method, boolean needLog, String[] args) {
        // 传入main()方法
        new Interpreter(method, needLog, args);
    }

    private Interpreter(Method method, boolean needLog, String[] args) {
        // 模拟JVM创建主线程运行 main(String[] args) 方法
        Thread mainThread = new Thread("main");
        Frame frame = mainThread.newFrame(method);
        mainThread.pushFrame(frame);
        if (args != null) {
            frame.getLocalVarsTable().setRef(0, this.convertOriginalToJavaStrObj(frame.getMethod().clazz.loader, args));
        }
        line = 0;
        loop(mainThread, needLog);
    }

    private Object convertOriginalToJavaStrObj(ClassLoader cl, String[] args) {
        Klass arrClass = cl.loadClass("[java/lang/String");
        Object arrObj = arrClass.newArray(args.length);
        for (int i = 0; i < args.length; i++) {
            arrObj.refs()[i] = StringPool.convertAndGetJavaInternStrObj(cl, args[i]);
        }
        return arrObj;
    }

    private void loop(Thread thread, boolean needLog) {
        Frame frame;
        ByteReader br = new ByteReader();

        while (1 == 1) {
            // 每次获取最新的栈帧
            frame = thread.topFrame();
            int nextPC = frame.getNextPC();
            thread.setPc(nextPC);
            // 取指，每次reset是因为有些指令会改变pc的前进方向，而这个改变最先作用于栈帧的pc寄存器，对于ByteReader则是不改变，需要reset一下，同时栈帧也有可能改变，比如调用其他方法时候会push一个新的栈帧
            br.reset(frame.getMethod().code, nextPC);
            byte opcode = br.read1Byte();
            Instruction instruction = InstructionMapper.acquireInstruction(opcode);
            if (Objects.isNull(instruction)) {
                System.out.printf("尚未实现该指令：%s\n", byteToHexString(new byte[]{opcode}));
                break;
            }
            // 间指
            instruction.fetchOperands(br);
            // PC++，不过有时不是触发++，因为有的指令会改变pc寄存器的值
            frame.setNextPC(br.getPC());
            // 执行前状况
            if (needLog) {
                printInstLog(frame, instruction, opcode);
            }
            // 执行
            instruction.execute(frame);

            // 这个line只是用来对输出的信息行进行编号用的，对JVM实现没有任何相关
            line++;

            if (thread.isStackEmpty()) {
                break;
            }
        }
    }

    private static void printInstLog(Frame frame, Instruction inst, byte opcode) {
        // \33[32;4m表示改变字体, \33[{前景色代号(31~36) || 背景色代号(41~46)};{数字(1加粗、3斜体、4下划线)}m
        System.out.printf(
                "\33[34;1m%d Line :\33[0m %s.%s()\t寄存器(指令): %s -> %s => 局部变量表: %s 操作数栈: %s\n",
                line,
                frame.getMethod().clazz.name,
                frame.getMethod().name,
                byteToHexString(new byte[]{opcode}),
                inst.getClass().getSimpleName(),
                JSON.toJSONString(frame.getLocalVarsTable().slots),
                JSON.toJSONString(frame.getOperandStack().getSlots())
        );
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
