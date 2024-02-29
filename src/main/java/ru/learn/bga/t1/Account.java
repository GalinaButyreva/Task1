package ru.learn.bga.t1;

import java.util.EnumMap;
import java.util.HashMap;


public class Account {

    private String user;
    private EnumMap <EnumValuta, Integer> enumMapValCnt;
    private void checkUser(String user) {
        if (user == null){
            throw new IllegalArgumentException("Имя не может быть null");
        }
        else if (user.equals("")) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
    }
    public Account(String user) {
        checkUser(user);
        this.user = user;
        enumMapValCnt = new EnumMap<>(EnumValuta.class);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        checkUser(user);
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
            System.out.println("Количество валюты не может быть отрицательным");
            throw new IllegalArgumentException("Количество валюты не может быть отрицательным");
        }
        else if (cnt == 0) {
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

