package ru.learn.bga.t1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.EnumMap;

public final class AccountSave {
    private final String user;
    private final String accountSch;
    private final EnumMap<EnumValuta, Integer> enumMapValCnt;
    private final LocalDateTime created;
    private AccountSave(AccBuilder builder) {
        this.user = builder.user;
        this.accountSch = builder.accountSch;
        this.enumMapValCnt = builder.enumMapValCnt;
        this.created = builder.created;
    }
    public static AccBuilder getAccBuider(String user) {
        return new AccBuilder(user);
    }
    public static final class AccBuilder {
        private final LocalDateTime created;
        private final String user;
        private  String accountSch;

        private  EnumMap<EnumValuta, Integer> enumMapValCnt;



        public AccBuilder(String user) {
            LocalDateTime date = LocalDateTime.now();
            this.user = user;
            this.created = date;

        }

        public  AccBuilder accountSch(String  accountSch){
            this.accountSch = accountSch;
            return this;
        }
        public  AccBuilder enumMapValCnt(EnumMap<EnumValuta, Integer> enumMapValCnt){
            this.enumMapValCnt =  new EnumMap<>(EnumValuta.class);
            for(EnumValuta valuta : enumMapValCnt.keySet()){
                this.enumMapValCnt.put(valuta, enumMapValCnt.get(valuta));
            }
            return this;
        }
        public AccountSave build() {
            return new AccountSave(this);
        }

    }
    //=======


    public String getUser() {
        return user;
    }

    public String getAccountSch() {
        return accountSch;
    }

    public EnumMap<EnumValuta, Integer> getEnumMapValCnt() {
        return enumMapValCnt;
    }

    public LocalDateTime getCreated() {

        return created;
    }
    @Override
    public String toString() {
        return "AccountSave =" +
                "created=" + created +
                ", user='" + user + " " +
                ", accountSch='" + accountSch + " " +
                ", enumMapValCnt=" + enumMapValCnt;
    }
}
