package ru.mironova.grek;

import java.util.LinkedList;

public class LimitedLinkedList<E> extends LinkedList<E> {
    public LimitedLinkedList(int limit){
        super();
        this.limit = limit;
    }

    private int limit;

    public boolean addWithLimit(E e) {
        if (limit > 0){
            if (limit-1 < size()){
                removeFirst();
            }
            super.add(e);
        }
        return false;
    }

    public String printAndClear(){
        StringBuilder sb = new StringBuilder();
        while(size() > 0){
            sb.append(pop()).append('\n');
        }
        return sb.toString();
    }
}
