package by.vadzimmatsiushonak.bank.api.util;

import by.vadzimmatsiushonak.bank.api.model.entity.Bank;

import java.math.BigDecimal;
import java.util.List;

import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_AMOUNT_ADMIN_BANK;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_AMOUNT_ALFA_BANK;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_BANK_TITLE_ADMIN_BANK;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_ID_ADMIN_BANK;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_ID_ALFA_BANK;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_TITLE_ALFA_BANK;

public class BankTestBuilder {


    public static final int COUNT = 2;


    public static final Bank newBankAdminBank() {
        Bank ID1_ADMIN_B = new Bank();
        ID1_ADMIN_B.setId(TEST_ID_ADMIN_BANK);
        ID1_ADMIN_B.setAmount(TEST_AMOUNT_ADMIN_BANK);
        ID1_ADMIN_B.setTitle(TEST_BANK_TITLE_ADMIN_BANK);
        ID1_ADMIN_B.setBankAccounts(List.of(BankAccountTestBuilder.recipientBankAccount()));
        return ID1_ADMIN_B;
    }

    public static final Bank newBankAlfaBank() {
        Bank ID2_CJSC = new Bank();
        ID2_CJSC.setId(TEST_ID_ALFA_BANK);
        ID2_CJSC.setAmount(TEST_AMOUNT_ALFA_BANK);
        ID2_CJSC.setTitle(TEST_TITLE_ALFA_BANK);
        ID2_CJSC.setBankAccounts(List.of(BankAccountTestBuilder.senderBankAccount()));
        return ID2_CJSC;
    }

    public static final Bank newBank(String title, BigDecimal amount) {
        Bank newBank = new Bank();
        newBank.setAmount(amount);
        newBank.setTitle(title);
        return newBank;
    }
}
