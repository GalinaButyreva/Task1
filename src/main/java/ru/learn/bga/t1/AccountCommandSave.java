package ru.learn.bga.t1;

public class AccountCommandSave implements CommandInter{
    AccountUndoRedo account;

    public AccountCommandSave (AccountUndoRedo account) {
        this.account = account;
    }
    @Override
    public void execute() {
        String user = account.getUser();
        AccountSave accountSave = AccountSave
                                .getAccBuider(user)
                                .accountSch(account.getAccountSch())
                                .enumMapValCnt(account.getEnumMapValuta())
                                .build();
        account.getRemoteControl().queryAccQr.pushQrObj((Object)accountSave, this);

    }

    @Override
    public void undo() {

    }
}
