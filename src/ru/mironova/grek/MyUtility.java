package ru.mironova.grek;

public class MyUtility {
    private MyUtility(){}

    private static final char[] serviceSymbols = new char[]{'^', '*', '?'};

    public static boolean checkPattron(String regEx){
        boolean isServiceLast = regEx.charAt(0) != '^';

        MAIN:
        for(char c: regEx.toCharArray()){
            for (char symbol: serviceSymbols){
                if (c == symbol){
                    if (isServiceLast){
                        return false;
                    } else {
                        isServiceLast = true;
                        continue MAIN;
                    }
                }
            }
            isServiceLast = false;
        }
        return true;
    }

    public static String reBuildPattren(String regEx){
        return ".*".concat(regEx).concat(".*");
    }
}
