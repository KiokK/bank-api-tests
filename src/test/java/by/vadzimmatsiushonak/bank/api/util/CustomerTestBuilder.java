package by.vadzimmatsiushonak.bank.api.util;

import by.vadzimmatsiushonak.bank.api.model.entity.Customer;

import java.time.LocalDate;
import java.util.List;

public class CustomerTestBuilder {

    public static Customer ID1_ADMIN() {
        Customer ID1_ADMIN = new Customer();
        ID1_ADMIN.setId(1L);
        ID1_ADMIN.setName("ADMIN");
        ID1_ADMIN.setSurname("ADMIN");
        ID1_ADMIN.setPassword("pass");
        ID1_ADMIN.setPhoneNumber("1 23 1234567");
        ID1_ADMIN.setDateOfBirth(LocalDate.parse("2000-02-20"));
        ID1_ADMIN.setBankAccounts(List.of(BankAccountTestBuilder.FIRST_IN_DB()));
        return ID1_ADMIN;
    }

    public static Customer ID2_VADZIM() {
        Customer ID2_VADZIM = new Customer();
        ID2_VADZIM.setId(1L);
        ID2_VADZIM.setName("VADZIM");
        ID2_VADZIM.setSurname("MATSIUSHONAK");
        ID2_VADZIM.setPassword("pass");
        ID2_VADZIM.setPhoneNumber("375 44 1452003");
        ID2_VADZIM.setDateOfBirth(LocalDate.parse("2000-05-25"));
        ID2_VADZIM.setBankAccounts(List.of(BankAccountTestBuilder.SECOND_IN_DB()));
        return ID2_VADZIM;
    }
}
