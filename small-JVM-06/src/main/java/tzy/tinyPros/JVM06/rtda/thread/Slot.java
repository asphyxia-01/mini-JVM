package tzy.tinyPros.JVM06.rtda.thread;

import tzy.tinyPros.JVM06.rtda.heap.methodarea.Object;

/**
 * @author TPureZY
 * @since 2023/7/11 15:37
 * <p>
 * 局部变量表中的存储单元，也是操作数栈的存储单元，一般而言是1个字，也就是4个字节
 **/
public class Slot {
    private int num;

    /**
     * 此处按照占用4个字节处理，一般而言，32位系统上为4字节、64位系统上为8字节，但64位系统推测实际上也是4字节（因为指针压缩）
     */
    private Object ref;

    public int getNum() {
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Object getRef(boolean isDel) {
        Object ans = this.ref;
        if(isDel){
            // 清除引用
            this.ref = null;
        }
        return ans;
    }

    public void setRef(Object ref) {
        this.ref = ref;
    }
}
