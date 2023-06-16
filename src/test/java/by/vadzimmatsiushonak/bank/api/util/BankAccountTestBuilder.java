package by.vadzimmatsiushonak.bank.api.util;

import by.vadzimmatsiushonak.bank.api.model.entity.Bank;
import by.vadzimmatsiushonak.bank.api.model.entity.BankAccount;
import by.vadzimmatsiushonak.bank.api.model.entity.Customer;
import by.vadzimmatsiushonak.bank.api.model.entity.base.Currency;
import by.vadzimmatsiushonak.bank.api.model.entity.base.OperationType;

import java.math.BigDecimal;
import java.util.ArrayList;

import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_NEW_IBAN_NUMBER;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_NEW_TITLE_BANK_ACCOUNT;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_RECIPIENT_AMOUNT_BANK_ACCOUNT;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_RECIPIENT_IBAN_BANK_ACCOUNT;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_RECIPIENT_ID_BANK_ACCOUNT;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_RECIPIENT_TITLE_BANK_ACCOUNT;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_SENDER_AMOUNT_BANK_ACCOUNT;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_SENDER_IBAN_BANK_ACCOUNT;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_SENDER_ID_BANK_ACCOUNT;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_SENDER_TITLE_BANK_ACCOUNT;

public class BankAccountTestBuilder {

    public static int COUNT = 2;

    public static BankAccount recipientBankAccount() {
        BankAccount FIRST_IN_DB = new BankAccount();
        FIRST_IN_DB.setId(TEST_RECIPIENT_ID_BANK_ACCOUNT);
        FIRST_IN_DB.setAmount(TEST_RECIPIENT_AMOUNT_BANK_ACCOUNT);
        FIRST_IN_DB.setCurrency(Currency.USD);
        FIRST_IN_DB.setIban(TEST_RECIPIENT_IBAN_BANK_ACCOUNT);
        FIRST_IN_DB.setTitle(TEST_RECIPIENT_TITLE_BANK_ACCOUNT);
        FIRST_IN_DB.setType(OperationType.DEBIT);
//        FIRST_IN_DB.setBank(BankTestBuilder.newBankAdminBank());
        FIRST_IN_DB.setBankCard(BankCardTestBuilder.newRecipientBankCard());
//        FIRST_IN_DB.setCustomer(CustomerTestBuilder.ID1_ADMIN());
//        FIRST_IN_DB.setBankPayments(List.of(BankPaymentTestBuilder.IN_DB_ID_1(),
//                BankPaymentTestBuilder.IN_DB_ID_2(),
//                BankPaymentTestBuilder.IN_DB_ID_3()));
        return FIRST_IN_DB;
    }

    public static BankAccount senderBankAccount() {
        BankAccount SECOND_IN_DB = new BankAccount();
        SECOND_IN_DB.setId(TEST_SENDER_ID_BANK_ACCOUNT);
        SECOND_IN_DB.setAmount(TEST_SENDER_AMOUNT_BANK_ACCOUNT);
        SECOND_IN_DB.setCurrency(Currency.BYN);
        SECOND_IN_DB.setIban(TEST_SENDER_IBAN_BANK_ACCOUNT);
        SECOND_IN_DB.setTitle(TEST_SENDER_TITLE_BANK_ACCOUNT);
        SECOND_IN_DB.setType(OperationType.DEBIT);
//        SECOND_IN_DB.setBank(BankTestBuilder.newBankAlfaBank());
        SECOND_IN_DB.setBankCard(BankCardTestBuilder.newSenderBankCard());
//        SECOND_IN_DB.setCustomer(CustomerTestBuilder.ID2_VADZIM());
        SECOND_IN_DB.setBankPayments(new ArrayList<>());
        return SECOND_IN_DB;
    }

    public static BankAccount newBankAccountNoCard(BigDecimal amount, Currency currencyType, OperationType operationType, Bank bank,
                                     Customer customer) {
        BankAccount SECOND_IN_DB = new BankAccount();
        SECOND_IN_DB.setAmount(amount);
        SECOND_IN_DB.setCurrency(currencyType);
        SECOND_IN_DB.setIban(TEST_NEW_IBAN_NUMBER);
        SECOND_IN_DB.setTitle(TEST_NEW_TITLE_BANK_ACCOUNT);
        SECOND_IN_DB.setType(operationType);
        SECOND_IN_DB.setBank(bank);
        SECOND_IN_DB.setCustomer(customer);
        SECOND_IN_DB.setBankPayments(new ArrayList<>());
        return SECOND_IN_DB;
    }

}
