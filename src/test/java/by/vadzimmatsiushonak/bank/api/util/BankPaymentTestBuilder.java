package by.vadzimmatsiushonak.bank.api.util;

import by.vadzimmatsiushonak.bank.api.model.dto.request.InitiatePaymentRequest;
import by.vadzimmatsiushonak.bank.api.model.entity.BankAccount;
import by.vadzimmatsiushonak.bank.api.model.entity.BankPayment;
import by.vadzimmatsiushonak.bank.api.model.entity.base.Currency;
import by.vadzimmatsiushonak.bank.api.model.entity.base.PaymentStatus;

import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_AMOUNT_BANK_PAYMENT1;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_AMOUNT_BANK_PAYMENT2;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_AMOUNT_BANK_PAYMENT3;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_ID_BANK_PAYMENT1;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_ID_BANK_PAYMENT2;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_ID_BANK_PAYMENT3;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_RECIPIENT_BANK_ACCOUNT_PAYMENT1;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_RECIPIENT_BANK_ACCOUNT_PAYMENT2;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_RECIPIENT_BANK_ACCOUNT_PAYMENT3;

public class BankPaymentTestBuilder {

    public static final int COUNT = 3;

    public static BankPayment newBankPayment2To1() {
        BankPayment IN_DB_ID_1 = new BankPayment();
        IN_DB_ID_1.setId(TEST_ID_BANK_PAYMENT1);
        IN_DB_ID_1.setAmount(TEST_AMOUNT_BANK_PAYMENT1);
        IN_DB_ID_1.setCurrency(Currency.BYN);
        IN_DB_ID_1.setRecipientBankAccountId(TEST_RECIPIENT_BANK_ACCOUNT_PAYMENT1);
        IN_DB_ID_1.setBankAccount(BankAccountTestBuilder.recipientBankAccount());
        IN_DB_ID_1.setStatus(PaymentStatus.ACCEPTED);
        return IN_DB_ID_1;
    }

    public static BankPayment newBankPaymentToTheSameAccount() {
        BankPayment IN_DB_ID_2 = new BankPayment();
        IN_DB_ID_2.setId(TEST_ID_BANK_PAYMENT2);
        IN_DB_ID_2.setAmount(TEST_AMOUNT_BANK_PAYMENT2);
        IN_DB_ID_2.setCurrency(Currency.EUR);
        IN_DB_ID_2.setRecipientBankAccountId(TEST_RECIPIENT_BANK_ACCOUNT_PAYMENT2);
        IN_DB_ID_2.setBankAccount(BankAccountTestBuilder.recipientBankAccount());
        IN_DB_ID_2.setStatus(PaymentStatus.ACCEPTED);
        return IN_DB_ID_2;
    }

    public static BankPayment newBankPayment3UserToAdmin() {
        BankPayment IN_DB_ID_3 = new BankPayment();
        IN_DB_ID_3.setId(TEST_ID_BANK_PAYMENT3);
        IN_DB_ID_3.setAmount(TEST_AMOUNT_BANK_PAYMENT3);
        IN_DB_ID_3.setCurrency(Currency.USD);
        IN_DB_ID_3.setRecipientBankAccountId(TEST_RECIPIENT_BANK_ACCOUNT_PAYMENT3);
        IN_DB_ID_3.setBankAccount(BankAccountTestBuilder.recipientBankAccount());
        IN_DB_ID_3.setStatus(PaymentStatus.ACCEPTED);
        return IN_DB_ID_3;
    }

    public static BankPayment newBankPaymentByRequest(InitiatePaymentRequest paymentRequest, PaymentStatus status, BankAccount sender) {
        BankPayment payment = new BankPayment();
        payment.setAmount(paymentRequest.amount);
        payment.setCurrency(paymentRequest.currency);
        payment.setBankAccount(sender);
        payment.setRecipientBankAccountId(paymentRequest.recipientBankAccountId);
        payment.setStatus(status);
        return payment;
    }
}
