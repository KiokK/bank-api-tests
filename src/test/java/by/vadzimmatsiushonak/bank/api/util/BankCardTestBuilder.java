package by.vadzimmatsiushonak.bank.api.util;

import by.vadzimmatsiushonak.bank.api.model.entity.BankAccount;
import by.vadzimmatsiushonak.bank.api.model.entity.BankCard;

import java.time.LocalDate;

import static by.vadzimmatsiushonak.bank.api.util.TestConstants.*;

public class BankCardTestBuilder {

    public static final int COUNT = 2;

    /**
     * @return recipient bank card without {@link BankCard#bankAccount} field.
     * The most suitable option {@link BankAccountTestBuilder#recipientBankAccount()}
     */
    public static final BankCard newRecipientBankCard() {
        BankCard ID1_ADMINS = new BankCard();
        ID1_ADMINS.setId(TEST_RECIPIENT_CARD_ID);
        ID1_ADMINS.setCvs(TEST_RECIPIENT_CARD_CVS);
        ID1_ADMINS.setExpirationDate(TEST_RECIPIENT_CARD_EXPIRATION_DATE);
        ID1_ADMINS.setNumber(TEST_RECIPIENT_CARD_NUMBER);
        return ID1_ADMINS;
    }

    /**
     * @return sender bank card without {@link BankCard#bankAccount} field.
     * The most suitable option {@link BankAccountTestBuilder#senderBankAccount()}
     */
    public static final BankCard newSenderBankCard() {
        BankCard ID2_VADIMS = new BankCard();
        ID2_VADIMS.setId(TEST_SENDER_CARD_ID);
        ID2_VADIMS.setCvs(TEST_SENDER_CARD_CVS);
        ID2_VADIMS.setExpirationDate(TEST_SENDER_CARD_EXPIRATION_DATE);
        ID2_VADIMS.setNumber(TEST_SENDER_CARD_NUMBER);
        return ID2_VADIMS;
    }

    /**
     * @return new bank card {@link BankCard#id} and {@link BankCard#bankAccount} fields are equals null
     */
    public static final BankCard newBankCard(BankAccount bankAccount, LocalDate expirationDate) {
        BankCard newCard = new BankCard();
        newCard.setCvs(TEST_NEW_CARD_CVS);
        newCard.setExpirationDate(expirationDate);
        newCard.setNumber(TEST_NEW_CARD_NUMBER);
        newCard.setBankAccount(bankAccount);
        return newCard;
    }

}
