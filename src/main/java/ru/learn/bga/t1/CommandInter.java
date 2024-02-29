package ru.learn.bga.t1;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public interface CommandInter {
    static Deque<Object> accountQue = new ConcurrentLinkedDeque<>();
    public void execute();
    public void undo();

}
