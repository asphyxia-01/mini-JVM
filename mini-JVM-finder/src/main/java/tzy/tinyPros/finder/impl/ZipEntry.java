package tzy.tinyPros.finder.impl;

import tzy.tinyPros.finder.Entry;

import java.io.IOException;
import java.nio.file.*;

/**
 * @author TPureZY
 * @since 2023/7/5 0:28
 *
 * Used to handle paths of type zip、jar
 **/
public class ZipEntry implements Entry {

    private Path absolutePath;

    public ZipEntry(String zipPath){
        this.absolutePath = Paths.get(zipPath).toAbsolutePath();
    }
    @Override
    public byte[] readClass(String className) throws IOException {
        // NIO文件系统
        try(FileSystem zipFs = FileSystems.newFileSystem(this.absolutePath,null)){
            return Files.readAllBytes(zipFs.getPath(className));
        }
    }

    @Override
    public String toString() {
        return this.absolutePath.toString();
    }
}
