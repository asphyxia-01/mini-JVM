package tzy.tinyPros.JVM05.instructions.base;

import tzy.tinyPros.JVM05.rtda.thread.Frame;
import tzy.tinyPros.JVM05.rtda.thread.OperandStack;

/**
 * @author TPureZY
 * @since 2023/7/15 22:04
 * <p>
 * 无操作数指令
 **/
public class NoOperandsInstruction implements Instruction {
    @Override
    public void fetchOperands(ByteReader br) {

    }

    @Override
    public void execute(Frame frame) {

    }

    /**
     * dcmp || dcmpg || dcmpl
     */
    protected void _dcmp(Frame frame, boolean gflag) {
        double var1 = frame.getOperandStack().popDouble();
        double var0 = frame.getOperandStack().popDouble();
        if (var0 > var1) {
            frame.getOperandStack().pushInt(1);
            return;
        }
        if (var0 == var1) {
            frame.getOperandStack().pushInt(0);
            return;
        }
        if (var0 < var1) {
            frame.getOperandStack().pushInt(-1);
            return;
        }
        /*
        Otherwise, at least one of value1' or value2' is NaN. The dcmpg instruction pushes the int value 1 onto the operand stack and the dcmpl instruction pushes the int value -1 onto the operand stack.
         */
        if (gflag) {
            frame.getOperandStack().pushInt(1);
            return;
        }
        frame.getOperandStack().pushInt(-1);
    }

    protected void _fcmp(Frame frame, boolean gflag) {
        OperandStack stack = frame.getOperandStack();
        float var3 = stack.popFloat();
        float var2 = stack.popFloat();
        if (var2 > var3) {
            frame.getOperandStack().pushInt(1);
            return;
        }
        if (var2 == var3) {
            frame.getOperandStack().pushInt(0);
            return;
        }
        if (var2 < var3) {
            frame.getOperandStack().pushInt(-1);
            return;
        }
        /*
        Otherwise, at least one of value1' or value2' is NaN. The dcmpg instruction pushes the int value 1 onto the operand stack and the dcmpl instruction pushes the int value -1 onto the operand stack.
         */
        if (gflag) {
            frame.getOperandStack().pushInt(1);
            return;
        }
        frame.getOperandStack().pushInt(-1);
    }

    protected void _astore(Frame frame, int idx) {
        Object ref = frame.getOperandStack().popRef();
        frame.getLocalVarsTable().setRef(idx, ref);
    }

    protected void _dstore(Frame frame, int idx) {
        double v = frame.getOperandStack().popDouble();
        frame.getLocalVarsTable().setDouble(idx, v);
    }

    protected void _fstore(Frame frame, int idx) {
        float v = frame.getOperandStack().popFloat();
        frame.getLocalVarsTable().setFloat(idx, v);
    }

    protected void _istore(Frame frame, int idx) {
        int i = frame.getOperandStack().popInt();
        frame.getLocalVarsTable().setInt(idx, i);
    }

    protected void _lstore(Frame frame, int idx) {
        long l = frame.getOperandStack().popLong();
        frame.getLocalVarsTable().setLong(idx, l);
    }
}
