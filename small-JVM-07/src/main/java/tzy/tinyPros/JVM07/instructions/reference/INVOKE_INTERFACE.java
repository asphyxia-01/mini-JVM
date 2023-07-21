package tzy.tinyPros.JVM07.instructions.reference;

import tzy.tinyPros.JVM07.instructions.base.ByteReader;
import tzy.tinyPros.JVM07.instructions.base.Instruction;
import tzy.tinyPros.JVM07.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/22 0:16
 **/
public class INVOKE_INTERFACE implements Instruction {

    private int idx;

    @Override
    public void fetchOperands(ByteReader br) {

    }

    @Override
    public void execute(Frame frame) {

    }
}
