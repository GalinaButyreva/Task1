package ru.learn.bga.t1;

import java.time.LocalDateTime;
import java.util.*;


public class Account {
    private String user;
    private EnumMap <EnumValuta, Integer> enumMapValCnt;
    private Deque<CommandInterface> commandInterfaceDeque = new ArrayDeque<>();
    private List<SnapshotInterface> accountSaves = new ArrayList<>();

    private void checkUser(String user) {
        if (user == null){
            throw new IllegalArgumentException("Имя не может быть null");
        }
        else if (user.equals("")) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
    }
    // Задание 2
    private void pushPrevCommandUser(String userPrev) {
        /*  Так понятнее пока для меня, но не красиво
        CommandInterface  commandInterface = new CommandInterface() {
            @Override
            public void execute() {
                Account.this.user = userPrev;
            }
        };*/
        // Пытаемся через функциональный стиль
        CommandInterface commandInterface = ()->{this.user=userPrev;};
        commandInterfaceDeque.push(commandInterface);
    }
    // реализовать execute интерфейса для Valuta
    private void  pushPrevCommandValuta(EnumValuta valuta) {
        CommandInterface commandInterface;
        // Предыдущее значение кол-ва валюты
        Integer cntPrev = enumMapValCnt.get(valuta);
        // если валюта есть меняем, если валюты нет(ее добавили),
        // то удаляем (эти команды будем выполнять при восстановлении команд из очереди)

        /*// Так понятнее, но не красиво
        // Boolean isNotRemoveVal = (enumMapValCnt.containsKey(valuta) && cntPrev != null);
        // Определим execute для валюты (Возможно, использование функционального интерфейса с лямбда было бы нагляднее
        // , пока мне так понятнее)
            commandInterface  = new CommandInterface() {
            @Override
            public void execute() {
                if (isNotRemoveVal) {
                    Account.this.enumMapValCnt.put(valuta, cntPrev);
                } else
                {
                    Account.this.enumMapValCnt.remove(valuta);
                }
            }
        };*/
        // Пробуем через функциональный стиль
        if (enumMapValCnt.containsKey(valuta) && cntPrev != null)
            commandInterface = ()->{this.enumMapValCnt.put(valuta, cntPrev);};
        else
            commandInterface = ()->{this.enumMapValCnt.remove(valuta);};
        commandInterfaceDeque.push(commandInterface);

    }
    public void undo() {
        if (commandInterfaceDeque.isEmpty() == false)
            commandInterfaceDeque.pop().execute();
        else
            throw  new RuntimeException("Объект вернулся к состоянию на момент создания");
    }
///////////////////// Задание 3
    public SnapshotInterface save(){
        SnapshotInterface snapshotInterface = new Snapshot();
        accountSaves.add(snapshotInterface);
        commandInterfaceDeque.clear();
        return snapshotInterface;
    };

    public void restoreStateOndate(LocalDateTime date) {
        SnapshotInterface accountOut = null;
        var iterator = accountSaves.iterator();
        while (iterator.hasNext()){
            SnapshotInterface snapshot = iterator.next();
            LocalDateTime dateCmp =  snapshot.getcreatedDate();
            if (date.isAfter(dateCmp))
                snapshot.restoreState();
        }
    }

    private class Snapshot implements SnapshotInterface {
        private final String user;
        private final EnumMap<EnumValuta, Integer> enumMapValCnt;
        private final LocalDateTime createdDate;

        public Snapshot() {
            this.user = Account.this.user;
            this.enumMapValCnt = new EnumMap<>(Account.this.enumMapValCnt);
            this.createdDate = LocalDateTime.now();
        }

        @Override
        public LocalDateTime getcreatedDate() {
            return this.createdDate;
        }

        @Override
        public void restoreState() {
            Account.this.user = this.user;
            Account.this.enumMapValCnt = new EnumMap<>(this.enumMapValCnt);

        }
    }
/////////////////////

    public Account(String user) {
        checkUser(user);
        this.user = user;
        enumMapValCnt = new EnumMap<>(EnumValuta.class);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        // проверим имя владельца
        checkUser(user);
        // отправим в очередь предыдущее значение имени пользователя
        pushPrevCommandUser(this.user);
        this.user = user;
    }

    public EnumMap<EnumValuta, Integer> getEnumMapValuta() {
        EnumMap <EnumValuta, Integer> enumMapValOut  = new EnumMap<>(EnumValuta.class);
        for(EnumValuta valuta : enumMapValCnt.keySet()){
            enumMapValOut.put(valuta, enumMapValCnt.get(valuta));
        }
        return enumMapValOut;
    }
    public Integer getValutaCnt(EnumValuta valuta) {
        if (enumMapValCnt.containsKey(valuta))
            return enumMapValCnt.get(valuta);
        else
            return 0;
    }


    public void setValuta(EnumValuta valuta,  Integer  cnt){
        if (cnt < 0) {
            throw new IllegalArgumentException("Количество валюты не может быть отрицательным");
        };
        // Отправим в очередь предыдущее количество валюты
        pushPrevCommandValuta(valuta);
        // Установим текущее кол-во валюты
        if (cnt == 0) {
            if (enumMapValCnt.containsKey(valuta))
                enumMapValCnt.remove(valuta);

        } else {
            enumMapValCnt.put(valuta, cnt);
        }

    }



    @Override
    public String toString() {

        return "Account : \n" +
                "user = " + getUser() + "\n" +
                "valuta = " + getEnumMapValuta();


    }

}

