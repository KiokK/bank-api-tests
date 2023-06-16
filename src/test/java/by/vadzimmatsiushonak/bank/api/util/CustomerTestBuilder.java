package by.vadzimmatsiushonak.bank.api.util;

import by.vadzimmatsiushonak.bank.api.model.entity.Customer;

import java.util.ArrayList;
import java.util.List;

import static by.vadzimmatsiushonak.bank.api.util.TestConstants.*;

public class CustomerTestBuilder {

    public static final int COUNT = 2;

    public static Customer newRecipientId1() {
        Customer ID1_ADMIN = new Customer();
        ID1_ADMIN.setId(TEST_RECIPIENT_ID);
        ID1_ADMIN.setName(TEST_RECIPIENT_NAME);
        ID1_ADMIN.setSurname(TEST_RECIPIENT_SURNAME);
        ID1_ADMIN.setPassword(TEST_PASSWORD);
        ID1_ADMIN.setPhoneNumber(TEST_RECIPIENT_PHONE);
        ID1_ADMIN.setDateOfBirth(TEST_RECIPIENT_DATE_OF_BIRTH);
        ID1_ADMIN.setBankAccounts(List.of(BankAccountTestBuilder.recipientBankAccount()));
        return ID1_ADMIN;
    }

    public static Customer newSenderId2() {
        Customer ID2_VADZIM = new Customer();
        ID2_VADZIM.setId(TEST_SENDER_ID);
        ID2_VADZIM.setName(TEST_SENDER_NAME);
        ID2_VADZIM.setSurname(TEST_SENDER_SURNAME);
        ID2_VADZIM.setPassword(TEST_PASSWORD);
        ID2_VADZIM.setPhoneNumber(TEST_SENDER_PHONE);
        ID2_VADZIM.setDateOfBirth(TEST_SENDER_DATE_OF_BIRTH);
        ID2_VADZIM.setBankAccounts(List.of(BankAccountTestBuilder.senderBankAccount()));
        return ID2_VADZIM;
    }

    public static Customer newCustomerDefault() {
        Customer ID2_VADZIM = new Customer();
        ID2_VADZIM.setName(TEST_LOGIN_BLOCKED_USER);
        ID2_VADZIM.setSurname(TEST_RECIPIENT_SURNAME);
        ID2_VADZIM.setPassword(TEST_PASSWORD);
        ID2_VADZIM.setPhoneNumber(TEST_SENDER_PHONE);
        ID2_VADZIM.setDateOfBirth(TEST_SENDER_DATE_OF_BIRTH);
        ID2_VADZIM.setBankAccounts(new ArrayList<>());
        return ID2_VADZIM;
    }
}
