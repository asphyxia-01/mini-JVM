package tzy.tinyPros.JVM09._native;

import tzy.tinyPros.JVM09.rtda.thread.Frame;

/**
 * @author TPureZY
 * @since 2023/7/31 21:04
 **/
@FunctionalInterface
public interface MethodNode {
    /**
     * 传入不同的方法体，实现不同的功能
     *
     * @param frame 栈帧
     * @throws Exception 有些方法会抛出异常
     */
    void invoke(Frame frame) throws Exception;
}