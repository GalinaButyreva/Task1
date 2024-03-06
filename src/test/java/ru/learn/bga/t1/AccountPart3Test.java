package ru.learn.bga.t1;

import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class AccountPart3Test {
    @Test
    @Description("Проверка. Задание 3. ")
    void SaveTest() {
        String firstName = "User";
        Account account = new Account("user1");
        // Фиксируем сохранения
        account.setValuta(EnumValuta.RUB, 100);
        account.setUser("Василий Иванов");
        account.setValuta(EnumValuta.RUB, 300);
        // Сохраняем на date1 "Василий Иванов" RUB=300
        account.save();
        LocalDateTime date1 = LocalDateTime.now(); // "Василий Иванов" RUB=300


        account.setUser("Петров Иван Петрович");
        account.setUser("Петров Петр Петрович");
        account.setValuta(EnumValuta.EUR, 200);


        // Сохраняем на date2 "Петров Петр Петрович" RUB=300 EUR=200
        account.save();
        LocalDateTime date2 = LocalDateTime.now(); // "Петров Петр Петрович" RUB=300 EUR=200

        account.setValuta(EnumValuta.USD, 200);
        account.setUser("Сидоров");

        // Сохраняем на date3 "Сидоров" RUB=300 EUR=200 USD =200
        account.save();
        LocalDateTime date3 = LocalDateTime.now(); //"Сидоров" RUB=300 EUR=200 USD =200

        account.setUser("Васильев");
        // Состояние на текущий момент : "Васильев"  RUB=300 EUR=200

        // Восстанавливаем зафиксированные состояния на дату
        // Возвращаем состояние на date1 Ожидаем "Василий Иванов" RUB=100
        account.restoreStateOndate(date1);
        Assertions.assertEquals("Василий Иванов", account.getUser());
        Assertions.assertEquals(300, account.getValutaCnt(EnumValuta.RUB));

        // Возвращаем состояние на date3 Ожидаем "Сидоров" RUB=300 EUR=200
        account.restoreStateOndate(date3);
        Assertions.assertEquals("Сидоров", account.getUser());
        Assertions.assertEquals(300, account.getValutaCnt(EnumValuta.RUB));
        Assertions.assertEquals(200, account.getValutaCnt(EnumValuta.EUR));
        Assertions.assertEquals(200, account.getValutaCnt(EnumValuta.USD));

        // Возвращаем состояние на date2 Ожидаем "Петров Петр Петрович" RUB=300 EUR=200
        account.restoreStateOndate(date2);
        Assertions.assertEquals("Петров Петр Петрович", account.getUser());
        Assertions.assertEquals(300, account.getValutaCnt(EnumValuta.RUB));
        Assertions.assertEquals(200, account.getValutaCnt(EnumValuta.EUR));
    }


}
