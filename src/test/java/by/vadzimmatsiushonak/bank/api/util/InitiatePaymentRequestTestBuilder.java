package by.vadzimmatsiushonak.bank.api.util;

import by.vadzimmatsiushonak.bank.api.model.dto.request.InitiatePaymentRequest;
import by.vadzimmatsiushonak.bank.api.model.entity.base.Currency;

import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_PAYMENT_MORE_THAN_POSSIBLE_VALUE;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_PAYMENT_VALUE;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_RECIPIENT_ID;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_SENDER_ID;

public class InitiatePaymentRequestTestBuilder {

    public static final int COUNT = 3;

    public static InitiatePaymentRequest payToYourself120() {
        InitiatePaymentRequest request = new InitiatePaymentRequest();
        request.recipientBankAccountId = TEST_RECIPIENT_ID;
        request.senderBankAccountId = TEST_RECIPIENT_ID;
        request.amount = TEST_PAYMENT_VALUE;
        request.currency = Currency.USD;
        return request;
    }

    public static InitiatePaymentRequest payUserToAdmin120USD() {
        InitiatePaymentRequest request = new InitiatePaymentRequest();
        request.recipientBankAccountId = TEST_RECIPIENT_ID;
        request.senderBankAccountId = TEST_SENDER_ID;
        request.amount = TEST_PAYMENT_VALUE;
        request.currency = Currency.USD;
        return request;
    }

    public static InitiatePaymentRequest payMoreThanPossibleUserToAdmin() {
        InitiatePaymentRequest request = new InitiatePaymentRequest();
        request.recipientBankAccountId = TEST_RECIPIENT_ID;
        request.senderBankAccountId = TEST_SENDER_ID;
        request.amount = TEST_PAYMENT_MORE_THAN_POSSIBLE_VALUE;
        request.currency = Currency.USD;
        return request;
    }
}
