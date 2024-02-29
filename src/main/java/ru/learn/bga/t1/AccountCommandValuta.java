package ru.learn.bga.t1;

import java.util.EnumMap;
import java.util.Stack;

public class AccountCommandValuta implements CommandInter{
    AccountUndoRedo account;
    public AccountCommandValuta(AccountUndoRedo account) {
        this.account = account;
    }
@Override
    public  void execute() {
        AccountUndoRedo accountState = new AccountUndoRedo("   ");
        EnumMap<EnumValuta, Integer>  EnumMapValuta =  account.getEnumMapValuta();

        for(EnumValuta valuta : EnumMapValuta.keySet()){
           accountState.setValuta(valuta, EnumMapValuta.get(valuta));
        }
        account.getRemoteControl().queryAccQr.pushQr(accountState, this);
    }
    @Override
    public void undo() {
        Boolean isNull = false;
        AccountUndoRedo accountState =  account.getRemoteControl().queryAccQr.getQrPrevState(this);
        EnumMap<EnumValuta, Integer>  EnumMapValuta =  accountState.getEnumMapValuta();

        if (EnumMapValuta.size() == 0) {
            EnumMapValuta = account.getEnumMapValuta();
            isNull = true;
        }
        for (EnumValuta valuta : EnumMapValuta.keySet()) {
                account.saveValuta(valuta, isNull == true ? 0 : EnumMapValuta.get(valuta));
            }
    }

}
