package ru.learn.bga.t1;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        // Тест
        Account account1 = new Account("user1");
        account1.setValuta(EnumValuta.RUB, 100);
        account1.setUser("Василий Иванов");
        account1.setValuta(EnumValuta.RUB, 300);
        System.out.println("До undo ==== " + account1.getUser() + ": " + account1.getEnumMapValuta());
        System.out.println("Выполняем undo:");
        account1.undo();
        System.out.println("1-й вызов undo " + account1.getUser() + ": " + account1.getEnumMapValuta());
        account1.undo();
        System.out.println("2-й вызов undo " + account1.getUser() + ": " + account1.getEnumMapValuta());
        account1.undo();
        System.out.println("3-й вызов undo " + account1.getUser() + ": " + account1.getEnumMapValuta());


        // Тест
        Account account = new Account("user1");
        account.setValuta(EnumValuta.RUB, 100);
        account.setUser("Василий Иванов");
        account.setValuta(EnumValuta.RUB, 300);
        System.out.println("До undo ==== " + account.getUser() + ": " + account.getEnumMapValuta());
        System.out.println("Выполняем undo:");
        //account.undo();
        System.out.println(account.getUser() + ": " + account.getEnumMapValuta());

        System.out.println("Сохраняем date1 " + account.getUser() + ": " + account.getEnumMapValuta());
        account.save();
        LocalDateTime date1 = LocalDateTime.now();
        account.setUser("Петров Иван Петрович");
        account.setUser("Петров Петр Петрович");
        account.setValuta(EnumValuta.EUR, 200);
        System.out.println("Сохраняем date2 " + account.getUser() + ": " + account.getEnumMapValuta());
        account.save();
        LocalDateTime date2 = LocalDateTime.now();
        account.setValuta(EnumValuta.USD, 200);
        account.setUser("Сидоров");
        account.save();
        LocalDateTime date3 = LocalDateTime.now();
        System.out.println("Сохраняем date3 " + account.getUser() + ": " + account.getEnumMapValuta());
        // Возвращаем состояние на date1
        account.restoreStateOndate(date1);
        System.out.println("Возвращаем на date 1 " + account.getUser() + ": " + account.getEnumMapValuta());
        // Возвращаем состояние на date3
        account.restoreStateOndate(date3);
        System.out.println("Возвращаем на date 3 " + account.getUser() + ": " + account.getEnumMapValuta());

        // Возвращаем состояние на date2
        account.restoreStateOndate(date2);
        System.out.println("Возвращаем на date 3 " + account.getUser() + ": " + account.getEnumMapValuta());

        account.undo();
       System.out.println(account.getUser() + ": " + account.getEnumMapValuta());

    }
}
