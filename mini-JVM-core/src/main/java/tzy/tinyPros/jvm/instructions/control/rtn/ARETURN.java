package tzy.tinyPros.jvm.instructions.control.rtn;

import tzy.tinyPros.jvm.instructions.base.NoOperandsInstruction;
import tzy.tinyPros.jvm.rtda.thread.Frame;

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
