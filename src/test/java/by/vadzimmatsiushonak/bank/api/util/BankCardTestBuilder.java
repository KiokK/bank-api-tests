package by.vadzimmatsiushonak.bank.api.util;

import by.vadzimmatsiushonak.bank.api.model.entity.BankAccount;
import by.vadzimmatsiushonak.bank.api.model.entity.BankCard;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BankCardTestBuilder {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final BankCard ID1_ADMINS() {
        BankCard ID1_ADMINS = new BankCard();
        ID1_ADMINS.setId(1L);
        ID1_ADMINS.setCvs("111");
        ID1_ADMINS.setExpirationDate(LocalDate.parse("2111-11-11", formatter));
        ID1_ADMINS.setNumber("1234567890987654");
//        ID1_ADMINS.setBankAccount(BankAccountTestBuilder.FIRST_IN_DB());
        return ID1_ADMINS;
    }

    public static final BankCard ID2_VADZIMS() {
        BankCard ID2_VADIMS = new BankCard();
        ID2_VADIMS.setId(2L);
        ID2_VADIMS.setCvs("123");
        ID2_VADIMS.setExpirationDate(LocalDate.parse("2026-10-01", formatter));
        ID2_VADIMS.setNumber("4585227889907279");
//        ID2_VADIMS.setBankAccount(BankAccountTestBuilder.SECOND_IN_DB());
        return ID2_VADIMS;
    }

    public static final BankCard newBankCard(BankAccount bankAccount, LocalDate expirationDate) {
        BankCard ID2_VADIMS = new BankCard();
        ID2_VADIMS.setCvs("234");
        ID2_VADIMS.setExpirationDate(expirationDate);
        ID2_VADIMS.setNumber("4585227889907111");
        ID2_VADIMS.setBankAccount(bankAccount);
        return ID2_VADIMS;
    }

}
