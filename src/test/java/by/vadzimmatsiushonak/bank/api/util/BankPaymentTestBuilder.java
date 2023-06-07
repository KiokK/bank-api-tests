package by.vadzimmatsiushonak.bank.api.util;

import by.vadzimmatsiushonak.bank.api.model.dto.request.InitiatePaymentRequest;
import by.vadzimmatsiushonak.bank.api.model.entity.BankAccount;
import by.vadzimmatsiushonak.bank.api.model.entity.BankPayment;
import by.vadzimmatsiushonak.bank.api.model.entity.base.Currency;
import by.vadzimmatsiushonak.bank.api.model.entity.base.PaymentStatus;

import java.math.BigDecimal;

public class BankPaymentTestBuilder {

    public static final int COUNT = 3;

    public static BankPayment IN_DB_ID_1() {
        BankPayment IN_DB_ID_1 = new BankPayment();
        IN_DB_ID_1.setId(1L);
        IN_DB_ID_1.setAmount(new BigDecimal("-150000.00"));
        IN_DB_ID_1.setCurrency(Currency.BYN);
        IN_DB_ID_1.setRecipientBankAccountId(2L);
        IN_DB_ID_1.setBankAccount(BankAccountTestBuilder.FIRST_IN_DB());
        IN_DB_ID_1.setStatus(PaymentStatus.ACCEPTED);
        return IN_DB_ID_1;
    }

    public static BankPayment IN_DB_ID_2() {
        BankPayment IN_DB_ID_2 = new BankPayment();
        IN_DB_ID_2.setId(2L);
        IN_DB_ID_2.setAmount(new BigDecimal("-4136.60"));
        IN_DB_ID_2.setCurrency(Currency.EUR);
        IN_DB_ID_2.setRecipientBankAccountId(1L);
        IN_DB_ID_2.setBankAccount(BankAccountTestBuilder.FIRST_IN_DB());
        IN_DB_ID_2.setStatus(PaymentStatus.ACCEPTED);
        return IN_DB_ID_2;
    }

    public static BankPayment IN_DB_ID_3() {
        BankPayment IN_DB_ID_3 = new BankPayment();
        IN_DB_ID_3.setId(3L);
        IN_DB_ID_3.setAmount(new BigDecimal("-137474.91"));
        IN_DB_ID_3.setCurrency(Currency.USD);
        IN_DB_ID_3.setRecipientBankAccountId(2L);
        IN_DB_ID_3.setBankAccount(BankAccountTestBuilder.FIRST_IN_DB());
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
