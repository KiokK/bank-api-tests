package by.vadzimmatsiushonak.bank.api.util;

import by.vadzimmatsiushonak.bank.api.model.entity.Bank;
import by.vadzimmatsiushonak.bank.api.model.entity.BankAccount;
import by.vadzimmatsiushonak.bank.api.model.entity.Customer;
import by.vadzimmatsiushonak.bank.api.model.entity.base.Currency;
import by.vadzimmatsiushonak.bank.api.model.entity.base.OperationType;

import java.math.BigDecimal;
import java.util.ArrayList;

public class BankAccountTestBuilder {

    public static int COUNT = 2;

    public static BankAccount FIRST_IN_DB() {
        BankAccount FIRST_IN_DB = new BankAccount();
        FIRST_IN_DB.setId(1L);
        FIRST_IN_DB.setAmount(new BigDecimal("12000000.00"));
        FIRST_IN_DB.setCurrency(Currency.USD);
        FIRST_IN_DB.setIban("US23AA1234567");
        FIRST_IN_DB.setTitle("ADMIN ADMIN TEST");
        FIRST_IN_DB.setType(OperationType.DEBIT);
//        FIRST_IN_DB.setBank(BankTestBuilder.ID1_ADMIN_B());
        FIRST_IN_DB.setBankCard(BankCardTestBuilder.ID1_ADMINS());
//        FIRST_IN_DB.setCustomer(CustomerTestBuilder.ID1_ADMIN());
//        FIRST_IN_DB.setBankPayments(List.of(BankPaymentTestBuilder.IN_DB_ID_1(),
//                BankPaymentTestBuilder.IN_DB_ID_2(),
//                BankPaymentTestBuilder.IN_DB_ID_3()));
        return FIRST_IN_DB;
    }

    public static BankAccount SECOND_IN_DB() {
        BankAccount SECOND_IN_DB = new BankAccount();
        SECOND_IN_DB.setId(2L);
        SECOND_IN_DB.setAmount(new BigDecimal("150000.00"));
        SECOND_IN_DB.setCurrency(Currency.BYN);
        SECOND_IN_DB.setIban("BY44VM1452003");
        SECOND_IN_DB.setTitle("VADZIM MATSIUSHONAK");
        SECOND_IN_DB.setType(OperationType.DEBIT);
//        SECOND_IN_DB.setBank(BankTestBuilder.ID2_CJSC());
        SECOND_IN_DB.setBankCard(BankCardTestBuilder.ID2_VADZIMS());
//        SECOND_IN_DB.setCustomer(CustomerTestBuilder.ID2_VADZIM());
        SECOND_IN_DB.setBankPayments(new ArrayList<>());
        return SECOND_IN_DB;
    }

    public static BankAccount newBankAccountNoCard(BigDecimal amount, Currency currencyType, OperationType operationType, Bank bank,
                                     Customer customer) {
        BankAccount SECOND_IN_DB = new BankAccount();
        SECOND_IN_DB.setAmount(amount);
        SECOND_IN_DB.setCurrency(currencyType);
        SECOND_IN_DB.setIban("BY44VM14512342");
        SECOND_IN_DB.setTitle("NO REAL USER");
        SECOND_IN_DB.setType(operationType);
        SECOND_IN_DB.setBank(bank);
        SECOND_IN_DB.setCustomer(customer);
        SECOND_IN_DB.setBankPayments(new ArrayList<>());
        return SECOND_IN_DB;
    }

}
