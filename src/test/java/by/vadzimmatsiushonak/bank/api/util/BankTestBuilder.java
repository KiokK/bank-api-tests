package by.vadzimmatsiushonak.bank.api.util;

import by.vadzimmatsiushonak.bank.api.model.entity.Bank;

import java.math.BigDecimal;
import java.util.List;

public class BankTestBuilder {


    public static final Bank ID1_ADMIN_B() {
        Bank ID1_ADMIN_B = new Bank();
        ID1_ADMIN_B.setId(1L);
        ID1_ADMIN_B.setAmount(new BigDecimal("12000000.00"));
        ID1_ADMIN_B.setTitle("ADMIN bank");
        ID1_ADMIN_B.setBankAccounts(List.of(BankAccountTestBuilder.FIRST_IN_DB()));
        return ID1_ADMIN_B;
    }

    public static final Bank ID2_CJSC() {
        Bank ID2_CJSC = new Bank();
        ID2_CJSC.setId(2L);
        ID2_CJSC.setAmount(new BigDecimal("1500000.00"));
        ID2_CJSC.setTitle("CJSC ALFA-bank");
        ID2_CJSC.setBankAccounts(List.of(BankAccountTestBuilder.SECOND_IN_DB()));
        return ID2_CJSC;
    }
}
