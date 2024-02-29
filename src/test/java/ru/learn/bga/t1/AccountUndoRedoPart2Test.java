package ru.learn.bga.t1;

import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountUndoRedoPart2Test {
    @Test
    @Description("Проверка. Часть 2. undo ")
    void UndoTest() {
        String firstName = "User";

        CreateAccount createAccount = new CreateAccount();
        AccountUndoRedo account = createAccount.createAcc(firstName);



        // Изменяем состояния
        account.setValuta(EnumValuta.RUB, 100);
        account.setUser("Василий Иванов");
        account.setValuta(EnumValuta.RUB, 300);

        account.undo();
        Assertions.assertEquals(100, account.getValutaCnt(EnumValuta.RUB));

        account.undo();
        Assertions.assertEquals(firstName, account.getUser());
        account.undo();
        Assertions.assertEquals(0, account.getValutaCnt(EnumValuta.RUB));

        Assertions.assertThrows(IllegalStateException.class, ()-> account.undo());

        account.setValuta(EnumValuta.RUB, 200);
        Assertions.assertEquals(200, account.getValutaCnt(EnumValuta.RUB));


        // Проверяем для второго объекта Account

        String firstName2 = "User2";

        CreateAccount createAccount2 = new CreateAccount();
        AccountUndoRedo account2 = createAccount2.createAcc(firstName2);


        account2.setValuta(EnumValuta.EUR, 100);
        Assertions.assertEquals(100, account2.getValutaCnt(EnumValuta.EUR));
        Assertions.assertEquals(0, account2.getValutaCnt(EnumValuta.RUB));

    }
}
