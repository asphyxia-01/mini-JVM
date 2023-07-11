package tzy.tinyPros.JVM04.rtda.thread;

/**
 * @author TPureZY
 * @since 2023/7/11 15:37
 * <p>
 * 局部变量表中的存储单元
 **/
public class Slot {
    private int num;
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
