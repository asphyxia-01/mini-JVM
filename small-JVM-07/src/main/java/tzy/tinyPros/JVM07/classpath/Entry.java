package tzy.tinyPros.JVM07.classpath;

import tzy.tinyPros.JVM07.classpath.impl.CompositeEntry;
import tzy.tinyPros.JVM07.classpath.impl.DirEntry;
import tzy.tinyPros.JVM07.classpath.impl.WildCardEntry;
import tzy.tinyPros.JVM07.classpath.impl.ZipEntry;

import java.io.File;
import java.io.IOException;

/**
 * @author TPureZY
 * @since 2023/7/5 0:20
 *
 * 类路径接口(用来找Class的目录或者zip、jar文件的处理接口)
 **/
public interface Entry {

    /**
     * 读取该Entry实例负责的Class的字节码
     * @param className
     * @return
     * @throws IOException
     */
    byte[] readClass(String className) throws IOException;

    static Entry create(String path){
        /*
         if path contains File.pathSeparator like ";" which used in windows
         */
        if(path.contains(File.pathSeparator)){
            return new CompositeEntry(path);
        }

        if(path.contains("*")){
            return new WildCardEntry(path);
        }

        if(path.contains(".JAR")||path.contains(".jar")||path.contains(".zip")||path.contains(".ZIP")){
            return new ZipEntry(path);
        }

        return new DirEntry(path);
    }
}
