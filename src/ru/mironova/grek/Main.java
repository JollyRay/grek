package ru.mironova.grek;

public class Main{
    public static void main(String[] args){

        Grek grek = Grek.init(args);
        if (grek != null){
            System.out.println(grek.excute().trim());
        } else {
            System.out.println("");
        }
    }
}