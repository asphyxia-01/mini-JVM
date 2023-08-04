package tzy.tinyPros.finder.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * @author TPureZY
 * @since 2023/7/5 0:28
 *
 * Paths with wildcards(通配符) are processed
 * 该类也是CompositeEntry的子集
 **/
public class WildCardEntry extends CompositeEntry {

    public WildCardEntry(String wildCardPath){
        super(getPathList(wildCardPath));
    }

    private static String getPathList(String baseDir){
        baseDir = baseDir.replace("*","");
        try{
            return Files.walk(Paths.get(baseDir))
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .filter(path -> path.contains(".jar") || path.contains(".JAR")||path.contains(".zip")||path.contains(".ZIP"))
                    .collect(Collectors.joining(File.pathSeparator));

        }catch(Exception e){
            // No other folders were found
            return "";
        }
    }
}
