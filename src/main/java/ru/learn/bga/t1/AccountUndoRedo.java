package ru.learn.bga.t1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Deque;
import java.util.EnumMap;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedDeque;

public class AccountUndoRedo  extends  Account {
    public RemoteControl getRemoteControl() {
        return remoteControl;
    }

    private  RemoteControl remoteControl;
    private String accountSch;

    public String getAccountSch() {
        return accountSch;
    }

    public void setAccountSch(String accountSch) {
        this.accountSch = accountSch;
        remoteControl.onAccountSchPushed();
    }

    public AccountUndoRedo(String user) {
        super(user);
  }
    public void setControl(RemoteControl remoteControl) {
        this.remoteControl = remoteControl;
        remoteControl.onUserPushed();
    }


    public  AccountSave getAccountSaveOnDate(LocalDateTime date) {
        AccountSave accountOut = null;

        var iterator = remoteControl.accountSaves.iterator();

        while (iterator.hasNext()){
            AccountSave accountSave = iterator.next();
            LocalDateTime dateCmp =  accountSave.getCreated();
            if (date.isAfter(dateCmp))
                accountOut = accountSave;
        }
        return accountOut;
    }
    public AccountUndoRedo getSaveStateOndate(LocalDateTime date) {
        AccountSave accountSave = getAccountSaveOnDate(date);
        AccountUndoRedo accountOut = null;
        if (accountSave != null) {
            accountOut = new AccountUndoRedo(accountSave.getUser());
            EnumMap<EnumValuta, Integer>  EnumMapValuta =  accountSave.getEnumMapValCnt();
            for(EnumValuta valuta : EnumMapValuta.keySet()){
                accountOut.setValuta(valuta, EnumMapValuta.get(valuta));
            }
        }
        return accountOut;
    }
    public void setCommandSave() {
        remoteControl.onCommandSave();
    }
    public void undo() {
        remoteControl.offPushed();
    }
    public void saveUser(String user) {
        super.setUser(user);
    }
    @Override
    public void setUser(String user) {
        super.setUser(user);
        remoteControl.onUserPushed();
    }

    public void saveValuta(EnumValuta valuta, Integer cnt) {
        super.setValuta(valuta, cnt);
    }

    @Override
    public void setValuta(EnumValuta valuta, Integer cnt) {
        super.setValuta(valuta, cnt);
        if (remoteControl != null)
            remoteControl.onValutaPushed();
    }

    @Override
    public String toString() {
        return "Account : \n" +
                "user = " + getUser() + "\n" +
                "valuta = " + getEnumMapValuta() +
                 "\n" + "accountSch = " + accountSch + "\n";

    }

}
