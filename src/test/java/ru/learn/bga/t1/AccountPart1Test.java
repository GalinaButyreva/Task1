package ru.learn.bga.t1;

import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountPart1Test {
    @Test
    @Description("Тестирование валидности имени для пустого значения Задание 1.")
    void setUserEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new  Account(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new  Account("user1").setUser(null));
    }
    @Test
    @Description("Тестирование валидности имени Null")
    void setUserNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new  Account(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new  Account("user1").setUser(null));

    }
    @Test
    @Description("Тестирование ввода отрицательного значения количества  валюты")
    void SetValuta() {
        Account account = new Account("user1");

        Assertions.assertThrows(IllegalArgumentException.class, ()->account.setValuta(EnumValuta.EUR, -10));
    }

    @Test
    @Description("Проверка. Валюта. Изменение количества")
    void SetValutaVal() {
        Account account = new Account("user1");
        account.setValuta(EnumValuta.EUR, 100);
        account.setValuta(EnumValuta.EUR, 200);
        Integer count = account.getEnumMapValuta().get(EnumValuta.EUR);
        Assertions.assertEquals(200, count);
    }



}
