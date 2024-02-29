package ru.learn.bga.t1;

import java.util.EnumMap;

public class AccountCommandUser implements CommandInter{
    AccountUndoRedo account;

    public AccountCommandUser(AccountUndoRedo account) {
        this.account = account;
    }

    @Override
    public void execute() {

        AccountUndoRedo accountState = new AccountUndoRedo(account.getUser());
        Object[] saveObj = new Object[2];
        saveObj[0] = accountState;
        account.getRemoteControl().queryAccQr.pushQr(accountState, this);
    }


    @Override
    public void undo() {
        AccountUndoRedo accountState = account.getRemoteControl().queryAccQr.getQrPrevState(this);
        account.saveUser(accountState.getUser());
    }

}
