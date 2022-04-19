package ru.mironova.grek;

import java.io.File;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class FileSpaceWorker {
    public static LinkedList<File> getAllFile(File file, LinkedList<String> excludes){
        LinkedList<File> files = new LinkedList<>();
        MAIN:
        for (File iterFile: file.listFiles()){
            if (!iterFile.canRead()){
                files.add(iterFile);
                continue;
            }
            if (iterFile.isDirectory()){
                files.addAll(getAllFile(iterFile, excludes));
                continue;
            }
            if (iterFile.isFile()){
                if (checkOnExclude(iterFile, excludes)){
                    continue MAIN;
                }
                files.add(iterFile);
                continue;
            }
        }
        return files;
    }

    public static boolean checkOnExclude(File file, LinkedList<String> excludes){
        for (String regEx: excludes){
            if (Pattern.matches(regEx, file.getName())){
                return true;
            }
        }
        return false;
    }

    public static boolean checkFile(File file){
        if (!file.exists()){
            return false;
        }
        if (file.isDirectory()){
            return false;
        }
        if (!file.canRead()){
            return false;
        }
        
        return true;
    }

    public static boolean checkDir(File file){
        if (!file.exists()){
            return false;
        }
        if (file.isFile()){
            return false;
        }
        if (!file.canRead()){
            return false;
        }
        
        return true;
    }
}
