package by.vadzimmatsiushonak.bank.api.util;

import by.vadzimmatsiushonak.bank.api.model.dto.request.InitiatePaymentRequest;
import by.vadzimmatsiushonak.bank.api.model.entity.base.Currency;

import java.math.BigDecimal;

public class InitiatePaymentRequestTestBuilder {

    public static final InitiatePaymentRequest PAY_TO_YOURSELF_120 = InitiatePaymentRequest.builder()
            .recipientBankAccountId(1L)
            .senderBankAccountId(1L)
            .amount(new BigDecimal("120.00"))
            .currency(Currency.USD)
            .build();

    public static final InitiatePaymentRequest PAY_2_TO_1_120 = InitiatePaymentRequest.builder()
            .recipientBankAccountId(1L)
            .senderBankAccountId(2L)
            .amount(new BigDecimal("120.00"))
            .currency(Currency.USD)
            .build();

    public static final InitiatePaymentRequest PAY_2_TO_1_MORE_THAN_POSSIBLE = InitiatePaymentRequest.builder()
            .recipientBankAccountId(1L)
            .senderBankAccountId(2L)
            .amount(new BigDecimal("1999999999.00"))
            .currency(Currency.USD)
            .build();

}
