package ru.learn.bga.t1;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class RemoteControl {
    QueryAccQr queryAccQr = new QueryAccQr();
    CommandInter[] onCommands;
    List<AccountSave> accountSaves = new ArrayList<>();

    public RemoteControl() {
        CommandInter noCommand = new NoCommand();
        for (int i = 0; i < onCommands.length; i++ ) {
            onCommands[i] = noCommand;
        }

    }

    public RemoteControl(CommandInter[] onCommands) {
        this.onCommands = onCommands;
   }

    public void onValutaPushed() {
       onCommands[0].execute();
    }

    public void onUserPushed() {
        onCommands[1].execute();
    }
    public void onAccountSchPushed() {
        onCommands[2].execute();
    }

    public void onCommandSave() {
        onCommands[3].execute();
        Object[] values = (Object[])queryAccQr.popQr();
        AccountSave accountSave = (AccountSave)values[1];
        accountSaves.add(accountSave);
        queryAccQr.clearQr();
    }
    public void offPushed() {
        Object[] values = (Object[])queryAccQr.popQr();
        CommandInter commandUndo = (CommandInter)values[0];
        commandUndo.undo();
    }

}
