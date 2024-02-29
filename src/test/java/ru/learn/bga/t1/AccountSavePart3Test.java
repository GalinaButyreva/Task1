package ru.learn.bga.t1;

import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AccountSavePart3Test {
    @Test
    @Description("Проверка. Часть 2. undo ")
    void SaveTest() {
        String firstName = "User";

        // Завернули
        CreateAccount createAccount = new CreateAccount();
        AccountUndoRedo account = createAccount.createAcc(firstName);

        account.setValuta(EnumValuta.RUB, 100);
        account.setUser("Василий Иванов");
        account.setValuta(EnumValuta.RUB, 300);

        account.undo();
        account.undo();
        account.undo();


        // Сохраняем
        account.setCommandSave();
        LocalDateTime date1 = LocalDateTime.now(); //firstUser RUB 0

        // Изменяем валюту и пользователя
        account.setValuta(EnumValuta.RUB, 700);
        account.setUser("UserSave");
        // Сохраняем(как бы фиксируем транзакцию)
        account.setCommandSave();
        LocalDateTime date2 = LocalDateTime.now(); // результат должен быть Пользователь = UserSave Валюта = RUB 700

        //Assertions.assertEquals(200, account.getValutaCnt(EnumValuta.RUB));
        // Проверяем сохранение на дату date1
        Account accountOnDate1 = account.getSaveStateOndate(date1);
        Assertions.assertEquals(0, accountOnDate1.getValutaCnt(EnumValuta.RUB));
        Assertions.assertEquals(firstName, accountOnDate1.getUser());
        //Проверяем сохранение на дату date2
        Account accountOnDate2 = account.getSaveStateOndate(date2);
        Assertions.assertEquals(700, accountOnDate2.getValutaCnt(EnumValuta.RUB));
        Assertions.assertEquals("UserSave", accountOnDate2.getUser());

        // Проверяем для второго объекта   Account
        String firstName2 = "User2";
        AccountUndoRedo account2 = createAccount.createAcc(firstName2);

        account2.setValuta(EnumValuta.EUR, 100);
        account2.setValuta(EnumValuta.EUR, 750);
        account2.undo();
        account2.setUser("User22");

        // Сохраняем
        account2.setCommandSave();
        LocalDateTime date21 = LocalDateTime.now(); //Пользователь = User22 Валюта = EUR 100
        // Изменим пользователя и валюту
        account2.setUser("User23");
        account2.setValuta(EnumValuta.USD, 350);
        // Сохраняем
        account2.setCommandSave();
        LocalDateTime date22 = LocalDateTime.now(); //Пользователь = User23 Валюта = EUR 100 USD 350
        Account accountOnDate21 = account2.getSaveStateOndate(date21);
        Assertions.assertEquals(100, accountOnDate21.getValutaCnt(EnumValuta.EUR));
        Assertions.assertEquals("User22", accountOnDate21.getUser());

        Account accountOnDate22 = account2.getSaveStateOndate(date22);
        Assertions.assertEquals(100, accountOnDate22.getValutaCnt(EnumValuta.EUR));
        Assertions.assertEquals("User23", accountOnDate22.getUser());
        Assertions.assertEquals(350, accountOnDate22.getValutaCnt(EnumValuta.USD));


    }


}
