package by.vadzimmatsiushonak.bank.api.util;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestConstants {

    public static final Long TEST_RECIPIENT_ID = 1L;
    public static final String TEST_RECIPIENT_NAME = "ADMIN";
    public static final String TEST_RECIPIENT_SURNAME = TEST_RECIPIENT_NAME;
    public static final String TEST_RECIPIENT_PHONE = "1 23 1234567";
    public static final LocalDate TEST_RECIPIENT_DATE_OF_BIRTH = LocalDate.of(2000, 02, 20);

    public static final Long TEST_SENDER_ID = 2L;
    public static final String TEST_SENDER_NAME = "Vadzim";
    public static final String TEST_SENDER_SURNAME = "MATSIUSHONAK";
    public static final String TEST_SENDER_PHONE = "375 44 1452003";
    public static final LocalDate TEST_SENDER_DATE_OF_BIRTH = LocalDate.of(2000, 05, 25);

    public static final Long TEST_ID_ADMIN_BANK = 1L;
    public static final String TEST_BANK_TITLE_ADMIN_BANK = "ADMIN bank";
    public static final BigDecimal TEST_AMOUNT_ADMIN_BANK = new BigDecimal("12000000.00");

    public static final Long TEST_ID_ALFA_BANK = 2L;
    public static final String TEST_TITLE_ALFA_BANK = "CJSC ALFA-bank";
    public static final BigDecimal TEST_AMOUNT_ALFA_BANK = new BigDecimal("1500000.00");

    public static final Long TEST_ID_USER_ADMIN = 1L;
    public static final String TEST_LOGIN_USER_ADMIN = "admin";
    public static final Long TEST_ID_USER = 2L;
    public static final String TEST_LOGIN_USER = TEST_SENDER_NAME;
    public static final Long TEST_ID_BLOCKED_USER = 3L;
    public static final String TEST_LOGIN_BLOCKED_USER = "Andrey";

    public static final Long TEST_ID_BANK_PAYMENT1 = 1L;
    public static final BigDecimal TEST_AMOUNT_BANK_PAYMENT1 = new BigDecimal("-150000.00");
    public static final Long TEST_RECIPIENT_BANK_ACCOUNT_PAYMENT1 = 2L;

    public static final Long TEST_ID_BANK_PAYMENT2 = 2L;
    public static final BigDecimal TEST_AMOUNT_BANK_PAYMENT2 = new BigDecimal("-4136.60");
    public static final Long TEST_RECIPIENT_BANK_ACCOUNT_PAYMENT2 = 1L;

    public static final Long TEST_ID_BANK_PAYMENT3 = 3L;
    public static final BigDecimal TEST_AMOUNT_BANK_PAYMENT3 = new BigDecimal("-137474.91");
    public static final Long TEST_RECIPIENT_BANK_ACCOUNT_PAYMENT3 = 2L;


    public static final Long TEST_RECIPIENT_CARD_ID = 1L;
    public static final String TEST_RECIPIENT_CARD_NUMBER = "1234567890987654";
    public static final String TEST_RECIPIENT_CARD_CVS = "111";
    public static final LocalDate TEST_RECIPIENT_CARD_EXPIRATION_DATE = LocalDate.of(2000, 05, 25);

    public static final Long TEST_SENDER_CARD_ID = 2L;
    public static final String TEST_SENDER_CARD_NUMBER = "4585227889907279";
    public static final String TEST_SENDER_CARD_CVS = "123";
    public static final LocalDate TEST_SENDER_CARD_EXPIRATION_DATE = LocalDate.of(2026, 10, 01);




    public static final Long TEST_SENDER_ID_BANK_ACCOUNT = 2L;
    public static final BigDecimal TEST_SENDER_AMOUNT_BANK_ACCOUNT = new BigDecimal("150000.00");
    public static final String TEST_SENDER_IBAN_BANK_ACCOUNT = "BY44VM1452003";
    public static final String TEST_SENDER_TITLE_BANK_ACCOUNT = "VADZIM MATSIUSHONAK";
    public static final Long TEST_RECIPIENT_ID_BANK_ACCOUNT = 1L;
    public static final BigDecimal TEST_RECIPIENT_AMOUNT_BANK_ACCOUNT = new BigDecimal("12000000.00");
    public static final String TEST_RECIPIENT_IBAN_BANK_ACCOUNT = "US23AA1234567";
    public static final String TEST_RECIPIENT_TITLE_BANK_ACCOUNT = "ADMIN ADMIN TEST";



    public static final String TEST_NEW_CARD_NUMBER = "4585227889907111";
    public static final String TEST_NEW_IBAN_NUMBER = "BY44VM14512342";
    public static final String TEST_NEW_TITLE_BANK_ACCOUNT = "NO REAL USER";
    public static final String TEST_NEW_CARD_CVS = "234";

    public static final BigDecimal NEW_AMOUNT = new BigDecimal("1291482.00");

    public static final String TEST_PASSWORD = "$2a$10$60pFUU0NmgZAGr8GvJwX3eM/3wW0RR7rRr2b519WFzijNjSr/TeNi";
    public static final BigDecimal TEST_PAYMENT_VALUE = new BigDecimal("120.00");
    public static final BigDecimal TEST_PAYMENT_MORE_THAN_POSSIBLE_VALUE = new BigDecimal("1999999999.00");

}
