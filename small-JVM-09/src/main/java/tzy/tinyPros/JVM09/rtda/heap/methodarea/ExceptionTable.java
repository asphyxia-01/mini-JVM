package tzy.tinyPros.JVM09.rtda.heap.methodarea;

import tzy.tinyPros.JVM09.classfile.attributes.impl.CodeAttribute;
import tzy.tinyPros.JVM09.rtda.heap.constantpool.ClassRef;
import tzy.tinyPros.JVM09.rtda.heap.constantpool.RunTimeConstantPool;

/**
 * @author TPureZY
 * @since 2023/8/4 19:52
 **/
public class ExceptionTable {

    private ExceptionHandler[] handlers;

    public ExceptionTable(CodeAttribute.ExceptionEntry[] handlerEntries, RunTimeConstantPool cp) {
        int len = handlerEntries.length;
        this.handlers = new ExceptionHandler[len];
        for (int i = 0; i < len; i++) {
            this.handlers[i] = new ExceptionHandler(
                    handlerEntries[i].startPC,
                    handlerEntries[i].endPC,
                    handlerEntries[i].handlePC,
                    this.analyseClassRef(handlerEntries[i].catchType, cp)
            );
        }
    }

    private ClassRef analyseClassRef(int cpIdx, RunTimeConstantPool cp) {
        if (cpIdx == 0) {
            return null;
        }
        return ((ClassRef) cp.constants[cpIdx]);
    }

    public ExceptionHandler analyseExceptionAndGetHandler(Klass clazz, int happenedPC) {
        for (ExceptionHandler handler : this.handlers) {
            if (happenedPC >= handler.startPC
                    && happenedPC < handler.endPC) {
                // 如果是null则表示是面向所有的异常类，都能处理
                if (null == handler.catchType) {
                    return handler;
                }
                if (handler.getExceptionClass() == clazz || clazz.isExtendFrom(handler.getExceptionClass())) {
                    return handler;
                }
            }
        }
        return null;
    }

    class ExceptionHandler {
        /**
         * 对应try-catch-finally结构中try的第一条指令
         */
        final int startPC;
        /**
         * 对应try-catch-finally结构中try的最后一条指令
         */
        final int endPC;
        final int handlerPC;
        final ClassRef catchType;
        private Klass exceptionClass;

        private ExceptionHandler(int startPC, int endPC, int handlerPC, ClassRef catchType) {
            this.startPC = startPC;
            this.endPC = endPC;
            this.handlerPC = handlerPC;
            this.catchType = catchType;
        }

        Klass getExceptionClass() {
            if (this.exceptionClass == null) {
                this.exceptionClass = catchType.getClazz();
            }
            return this.exceptionClass;
        }
    }
}
