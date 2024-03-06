package ru.learn.bga.t1;

import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountPart2Test {
    @Test
    @Description("Проверка. Задание 2.")
    void UndoTest() {
        String firstName = "User";
        Account account = new Account(firstName);

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

        Assertions.assertThrows(RuntimeException.class, ()-> account.undo());

        account.setValuta(EnumValuta.RUB, 200);
        Assertions.assertEquals(200, account.getValutaCnt(EnumValuta.RUB));


    }
}
