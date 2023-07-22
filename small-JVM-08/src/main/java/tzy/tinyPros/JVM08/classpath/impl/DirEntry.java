package tzy.tinyPros.JVM08.classpath.impl;

import tzy.tinyPros.JVM08.classpath.Entry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author TPureZY
 * @since 2023/7/5 0:28
 *
 * Used to handle dir path
 **/
public class DirEntry implements Entry {

    private Path absolutePath;

    public DirEntry(String path){
        this.absolutePath = Paths.get(path).toAbsolutePath();
//        System.out.println("当前目录是："+this.absolutePath.toString());
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        /*
        path.resolve()
        如果其他参数是一个绝对路径，那么这个方法就会简单地返回其他。如果其他参数是一个空的路径，那么这个方法就简单地返回这个路径。否则，这个方法认为这个路径是一个目录，并根据这个路径解析给定的路径
         */
        return Files.readAllBytes(this.absolutePath.resolve(className));
    }

    @Override
    public String toString() {
        return this.absolutePath.toString();
    }
}
