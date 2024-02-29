package ru.learn.bga.t1;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class QueryAccQr {
    Deque<Object> accountQue = new ConcurrentLinkedDeque<>();
    public  void pushQr(AccountUndoRedo accountUndoRedo
                        , CommandInter commandInter) {
        Object[] saveObj = new Object[2];
        saveObj[0] = commandInter;
        saveObj[1] = accountUndoRedo;
        accountQue.addLast(saveObj);
    }
   public  void pushQrObj(Object objectToQr
            , CommandInter commandInter) {
        Object[] saveObj = new Object[2];
        saveObj[0] = commandInter;
        saveObj[1] = objectToQr;
        accountQue.addLast(saveObj);
    }
    public  Object[] popQr() {
        if (accountQue.size() >1) {
                Object[] saveObj = (Object[]) accountQue.removeLast();
                return saveObj;
        } else if (accountQue.size() == 1) {

            throw  new IllegalStateException("Отстутствуют сохраненные состояния");
        } else {
            throw  new IllegalStateException("Отстутствуют сохраненные состояния");
        }
    }
    public  Object[] getQr() {
        if (accountQue.size() >= 1) {
            Object[] saveObj = (Object[]) accountQue.getLast();
            return saveObj;
        } else {
            throw  new IllegalStateException("Отстутствуют сохраненные состояния");
        }

    }

    public Object[] getQrPrev(CommandInter commandInter) {
        var iterator = accountQue.descendingIterator();//iterator();


        while (iterator.hasNext()) {
            Object[] saveObj = (Object[]) iterator.next(); //accountQue.getLast();
            CommandInter commandInterCmp =  (CommandInter)saveObj[0];
            if (commandInterCmp  == commandInter  ) {
                return  saveObj;
            }

        }
        Object[] saveObj = (Object[]) accountQue.getFirst();
        return saveObj;
    }

    public void  clearQr(){
        Object[] saveObj = (Object[]) accountQue.getLast();
        accountQue.clear();
        accountQue.addLast(saveObj);
    }
    public  AccountUndoRedo getQrPrevState(CommandInter commandInter) {

        Object[] valprev = getQrPrev(commandInter);

        AccountUndoRedo accountState = (AccountUndoRedo) valprev[1];

        return  accountState;
    }


}
