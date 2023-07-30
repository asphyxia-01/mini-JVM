package tzy.tinyPros.JVM09.classpath.impl;

import tzy.tinyPros.JVM09.classpath.Entry;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TPureZY
 * @since 2023/7/5 0:27
 *
 * Used to resolve mixed paths (with ";" delimited) to the corresponding other Entry instances
 **/
public class CompositeEntry implements Entry {

    private final List<Entry> entryList = new ArrayList<>();

    public CompositeEntry(String path){
        String[] paths = path.split(File.pathSeparator);
        for(String var1 : paths){
            entryList.add(Entry.create(var1));
        }
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        for (Entry entry : entryList) {
            try{
                return entry.readClass(className);
            }catch (Exception ignored){
                // No need to write, if you don't find it, go straight to the next loop to see whose path matches
            }
        }
        throw new IOException("Class not find" + className);
    }

    @Override
    public String toString() {
        String[] paths = new String[entryList.size()];
        for (int i = 0; i < entryList.size(); i++) {
            paths[i] = entryList.get(i).toString();
        }
        return String.join(File.pathSeparator,paths);
    }
}
