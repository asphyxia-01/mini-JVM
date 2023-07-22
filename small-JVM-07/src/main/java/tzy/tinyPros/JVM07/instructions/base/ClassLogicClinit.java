package tzy.tinyPros.JVM07.instructions.base;

import tzy.tinyPros.JVM07.rtda.heap.methodarea.Class;
import tzy.tinyPros.JVM07.rtda.heap.methodarea.Method;
import tzy.tinyPros.JVM07.rtda.thread.Thread;

/**
 * @author TPureZY
 * @since 2023/7/22 15:49
 * <p>
 * 这里主要是执行类的初始化 - 指的是静态初始化，也就是执行 clinit 方法
 * <p>
 * init is the (or one of the) constructor(s) for the instance, and non-static field initialization.
 * <p>
 * clinit are the static initialization blocks for the class, and static field initialization.
 **/
public class ClassLogicClinit {
    public static void clinitClass(Thread thread, Class clazz) {
        clazz.startClinit();
        scheduleClinit(thread, clazz);
        clinitSuperClass(thread, clazz);
    }

    private static void scheduleClinit(Thread thread, Class clazz) {
        Method clinitMethod = clazz.getClinitMethod();
        if (null == clinitMethod) {
            return;
        }
        // 加入静态初始化方法
        thread.pushFrame(thread.newFrame(clinitMethod));
    }

    private static void clinitSuperClass(Thread thread, Class clazz) {
        // 如果clazz是接口那么也会执行静态初始化，但是父接口不需要
        if (clazz.isInterface()) {
            return;
        }
        Class superClass = clazz.superClass;
        if (superClass != null && !superClass.isClinitStarted()) {
            // 静态初始化父类
            clinitClass(thread, superClass);
        }

    }
}
