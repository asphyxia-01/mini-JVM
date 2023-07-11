package tzy.tinyPros.JVM04;

import tzy.tinyPros.JVM04.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/11 17:02
 **/
public class Test {
    public static void main(String[] args) {
        Frame frame = new Frame(100, 100);
        frame.getLocalVarsTable().setDouble(0,10.32);
        System.out.println(frame.getLocalVarsTable().getDouble(0));
        frame.getOperandStack().pushDouble(10.32);
        System.out.println(frame.getOperandStack().popDouble());
        frame.getOperandStack().pushLong(101l);
        System.out.println(frame.getOperandStack().popLong());
    }
}
