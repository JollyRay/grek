package ru.mironova.grek;

public class Console {

    public static String fileNotExist(String name){
        return "File not exitst: ".concat(name).concat("\n");
    }

    public static String fileIsDir(String name){
        return "File is directory: ".concat(name).concat("\n");
    }

    public static String notFileOrDir(String name){
        return "grek: ".concat(name).concat(": No such file or directory\n");
    }

    public static String fileWithoutAccess(String name){
        if (name == null){
            return "grek: : Permission denied";
        }
        return String.format("grek: %s: Permission denied", name);
    }

    public static String uncorrectArg(String arg){
        if (arg == null){
            return "Uncorrect arg: ";
        }
        return "Uncorrect arg: ".concat(arg);
    }

    public static String keyWithoutArg(String keyName){
        if (keyName == null){
            return "Uncorrect arg: \nYou can use --help";
        }
        return String.format("Uncorrect arg: %s\nYou can use --help", keyName);
    }

    public static String helpMessage(){
        return "grek [OPTION]... PATTRON [File]...\n" +
        "Pattern selection and interpretation:\n" +
        "\t-r\trecurse\n" +
        "\t-n\tprint line number with output lines\n" +
        "\t-i\tignore case\n"+
        "\t--exclude=[PATTRON]\texclude files by name";
    }
}
