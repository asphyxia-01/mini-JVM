package tzy.tinyPros.JVM07.instructions.control.rtn;

import tzy.tinyPros.JVM07.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.JVM07.rtda.thread.Frame;
import tzy.tinyPros.JVM07.rtda.thread.Thread;

/**
 * @author TPureZY
 * @since 2023/7/22 15:14
 **/
public class ARETURN extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        // 要退出的方法
        Frame lastFrame
                =
                frame.
                        getThread()
                        .popFrame();
        // 要返回的结果会存放在操作数栈
        frame
                .getThread()
                .topFrame()
                .getOperandStack()
                .pushRef(
                        lastFrame
                                .getOperandStack()
                                .popRef()
                );
    }
}
