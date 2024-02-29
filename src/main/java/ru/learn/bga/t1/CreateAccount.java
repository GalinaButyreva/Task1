package ru.learn.bga.t1;

public class CreateAccount {
    public AccountUndoRedo createAcc(String username) {
        AccountUndoRedo account = new AccountUndoRedo(username);
        AccountCommandValuta accountCommandValuta = new AccountCommandValuta(account);
        AccountCommandUser accountCommandUser = new AccountCommandUser(account);
        AccountCommandAccountSch accountCommandAccountSch = new AccountCommandAccountSch(account);
        AccountCommandSave accountCommandSave = new AccountCommandSave(account);
        CommandInter[] commandInters = {accountCommandValuta
                , accountCommandUser
                , accountCommandAccountSch
                , accountCommandSave};
        RemoteControl remoteControl = new RemoteControl(commandInters);
        account.setControl(remoteControl);
        return account;
    }
}
