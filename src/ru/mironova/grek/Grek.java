package ru.mironova.grek;

import static ru.mironova.grek.Constants.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Grek{
    private Grek(){}

    private static final String separator = "--";

    private boolean withRegister;
    private boolean isRecursion;
    private boolean isNumbered;
    private boolean withSeparate;
    private LinkedList<String> paths = new LinkedList<>();
    private String specialMessage;
    private String regEx;
    private String nextMessage;
    private StringBuilder messages;
    private int after, before;
    private LinkedList<String> uncorrectFile = new LinkedList<String>();

    public static Grek init(String[] args){
        Grek self = new Grek();
        int len = args.length;
        if (len < 2){
            self.specialMessage = len == 1 && args[0].equals(helpKey) ? Console.helpMessage() : "";
            return self;
        }

        for (int iter = 0; iter < len; iter++){
            if (args[iter].trim().length() == 0){
                continue;
            }
            switch (args[iter]) {
                case recursionKey:
                    self.isRecursion = true;
                    break;
                case withNumber:
                    self.isNumbered = true;
                    break;
                case withoutRegisterKey:
                    self.withRegister = true;
                    break;
                case beforeStringKey:
                    if (len - 1 == iter){
                        self.specialMessage = Console.keyWithoutArg(args[iter]);
                        return self;
                    } else {
                        if (!self.setBefore(args[iter+1])){
                            self.specialMessage = Console.uncorrectArg(args[iter+1]);
                            return self;
                        }
                        iter++;
                    }
                    break;
                case afterStringKey:
                    if (len - 1 == iter){
                        self.specialMessage = Console.keyWithoutArg(args[iter]);
                        return self;
                    } else {
                        if (!self.setAfter(args[iter+1])){
                            self.specialMessage = Console.uncorrectArg(args[iter+1]);
                            return self;
                        }
                        iter++;
                    }
                    break;
                case helpKey:
                    self.specialMessage = Console.helpMessage();
                    return self; 
                default:
                    if (Grek.parseNonFixedKey(args[iter], self)){
                        return self;
                    }
                    break;
            }
        }
        return self;
    }

    private static boolean parseNonFixedKey(String key, Grek self){
        if (key.trim().indexOf(excludeKey) == 0){
            if (key.trim().length() < 11){
                self.specialMessage = Console.uncorrectArg(key);
                return true;
            }
            self.appendExclude(key.trim());
        } else if (key.trim().charAt(0) == '-'){
            self.specialMessage = Console.uncorrectArg(key);
            return true;
        } else if (self.regEx == null){
            if (!MyUtility.checkPattron(key.trim())){
                self.specialMessage = "";
                return true;
            }
            self.regEx = MyUtility.reBuildPattren(key.trim());
        } else {
            if (key.trim().length() > 0)
                self.paths.add(key);
        }
        return false;
    } 

    private boolean setBefore(String before){
        try{
            this.before = Integer.parseInt(before);
            if (this.before > -1){
                withSeparate = true;
                return true;
            }
            return false;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private boolean setAfter(String after){
        try{
            this.after = Integer.parseInt(after);
            if (this.after > -1){
                withSeparate = true;
                return true;
            }
            return false;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private boolean appendExclude(String excludeWithPrefix){
        String regEx = excludeWithPrefix.substring(10).trim();
        if (MyUtility.checkPattron(regEx)){
            this.uncorrectFile.add(MyUtility.reBuildPattren(regEx));
            return true;
        }
        return false;
    }

    public String excute(){
        if (this.specialMessage !=null){
            return this.specialMessage;
        }
        messages = new StringBuilder();

        boolean isMulti = this.paths.size() > 1 || isRecursion;
        try {
            for (String path: this.paths){
                File file = new File(path);
                if (FileSpaceWorker.checkFile(file)){
                    if (!FileSpaceWorker.checkOnExclude(file, uncorrectFile))
                        printFile(file, isMulti);
                    continue;
                }
                if (FileSpaceWorker.checkDir(file)){
                    if (isRecursion){
                        LinkedList<File> files = FileSpaceWorker.getAllFile(file, uncorrectFile);
                        files.forEach((iterFile) -> printFile(iterFile, isMulti || files.size() > 1));
                    } else {
                        pullNextMessage(Console.fileIsDir(file.getName()), false);
                    }
                    continue;
                }
                pullNextMessage(Console.notFileOrDir(file.getName()), false);
            }
        } catch (PatternSyntaxException e){
            return "";
        }

        popNextMessage(false);

        return messages.toString();
    }

    public static String initAndExcute(String[] args){
        Grek grek = Grek.init(args);
        if (grek != null){
            return grek.excute().trim();
        } else {
            return "";
        }
    }

    private void pullNextMessage(String newMessage, boolean withSeparator){
        if (newMessage != null){
            popNextMessage(withSeparator);
            nextMessage = newMessage;
        }
    }

    private void popNextMessage(boolean withSeparator){
        if (nextMessage != null){
            messages.append(nextMessage);
            if (withSeparator && this.withSeparate){
                messages.append(separator).append('\n');
            }
        }
    }

    private void printFile(File file, boolean isMulti) throws PatternSyntaxException{
        if (!file.canRead()){
            pullNextMessage(Console.fileWithoutAccess(file.getName()),false);
            return;
        }
        try{
            Scanner scanner = new Scanner(file);
            LimitedLinkedList<String> listBefore = new LimitedLinkedList<>(before);
            int counter = 0, lineNumber = 0, lastFind = 0;
            String sidePrefix = "";
            String mainPrefix = "";
            boolean beFind = false;
            if (isMulti){
                sidePrefix = sidePrefix.concat(file.getPath()).concat("-");
                mainPrefix = mainPrefix.concat(file.getPath()).concat(":");
            }
            if (isNumbered){
                sidePrefix = sidePrefix.concat("%d-");
                mainPrefix = mainPrefix.concat("%d:");
            }
            Pattern pattern = Pattern.compile(regEx, withRegister ? Pattern.CASE_INSENSITIVE : Pattern.CANON_EQ );
            
            while (scanner.hasNextLine()){
                lineNumber++;
                String line = scanner.nextLine();
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()){
                    beFind = true;
                    counter = 0;
                    listBefore.add(String.format(mainPrefix, lineNumber).concat(line));
                    continue;
                }
                ++counter;
                if (after < counter && beFind){
                    pullNextMessage(listBefore.printAndClear(), lastFind == 0 || lineNumber -  after - 1 - before - counter > lastFind);
                    lastFind = lineNumber - after - 1;
                    beFind = false;
                }
                if (beFind){
                    listBefore.add(String.format(sidePrefix, lineNumber).concat(line));
                } else {
                    listBefore.addWithLimit(String.format(sidePrefix, lineNumber).concat(line));
                }
            }
            if (beFind){
                pullNextMessage(listBefore.printAndClear(), lastFind == 0 || lineNumber -  after - 1 - before - counter > lastFind);
            }
            
            scanner.close();
        } catch(FileNotFoundException e){
            pullNextMessage(Console.fileNotExist(file.getPath()), false);
        }
    }
}
