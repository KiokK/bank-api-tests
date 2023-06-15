package by.vadzimmatsiushonak.bank.api.service.impl;

import by.vadzimmatsiushonak.bank.api.model.entity.BankAccount;
import by.vadzimmatsiushonak.bank.api.model.entity.base.Currency;
import by.vadzimmatsiushonak.bank.api.model.entity.base.OperationType;
import by.vadzimmatsiushonak.bank.api.repository.BankAccountRepository;
import by.vadzimmatsiushonak.bank.api.util.BankAccountTestBuilder;
import by.vadzimmatsiushonak.bank.api.util.BankTestBuilder;
import by.vadzimmatsiushonak.bank.api.util.CustomerTestBuilder;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static by.vadzimmatsiushonak.bank.api.util.BankAccountTestBuilder.recipientBankAccount;
import static by.vadzimmatsiushonak.bank.api.util.BankAccountTestBuilder.senderBankAccount;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.NEW_AMOUNT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankAccountServiceImplTest {

    @Mock
    private BankAccountRepository bankAccountRepository;
    @InjectMocks
    private BankAccountServiceImpl bankAccountService;


    @Nested
    public class Create {
        @Test
        void checkCreateShouldSaveNewBankAccount() {
            BankAccount actualBankAccount = BankAccountTestBuilder
                    .newBankAccountNoCard(new BigDecimal("100101.00"), Currency.BYN, OperationType.DEBIT,
                            BankTestBuilder.newBankAdminBank(), CustomerTestBuilder.newSenderId2());

            BankAccount expected = BankAccountTestBuilder
                    .newBankAccountNoCard(new BigDecimal("100101.00"), Currency.BYN, OperationType.DEBIT,
                            BankTestBuilder.newBankAdminBank(), CustomerTestBuilder.newSenderId2());

            final long NEXT_ID = 3L;
            expected.setId(NEXT_ID);

            when(bankAccountRepository.save(actualBankAccount)).thenReturn(expected);
            actualBankAccount = bankAccountService.create(actualBankAccount);

            assertThat(actualBankAccount.getId()).isEqualTo(NEXT_ID);
        }
    }
    @Nested
    public class FindById {
        @Test
        void checkFindByIdShouldReturnEmptyOptional() {
            final long NO_EXITING_ID = 112312L;
            when(bankAccountRepository.findById(NO_EXITING_ID)).thenReturn(Optional.empty());
            Optional<BankAccount> actualBankAccount = bankAccountService.findById(NO_EXITING_ID);

            assertThat(actualBankAccount)
                    .isEmpty();
        }

        @Test
        void checkFindByIdShouldReturnFirstInDB() {
            final long ID_IN_DB = 1L;
            BankAccount expected = recipientBankAccount();

            when(bankAccountRepository.findById(ID_IN_DB)).thenReturn(Optional.of(expected));
            BankAccount actualBankAccount = bankAccountService.findById(ID_IN_DB).orElse(new BankAccount());

            assertThat(actualBankAccount.getId())
                    .isEqualTo(ID_IN_DB);
        }
    }

    @Nested
    public class FindAll {
        @Test
        void checkFindAllVerify() {
            bankAccountService.findAll();
            verify(bankAccountRepository).findAll();
        }

        @Test
        void checkFindAllShouldReturnCount() {
            final int EXPECTED_SIZE = BankAccountTestBuilder.COUNT;

            when(bankAccountRepository.findAll()).thenReturn(
                    List.of(recipientBankAccount(), senderBankAccount())
            );

            assertThat(bankAccountService.findAll().size())
                    .isEqualTo(EXPECTED_SIZE);
        }
    }

    @Nested
    public class Update {

        @Test
        void checkUpdateShouldReturnUpdated() {
            BankAccount realBankAccount = recipientBankAccount();
            BankAccount expectedBankAccount = recipientBankAccount();
            realBankAccount.setAmount(NEW_AMOUNT);
            expectedBankAccount.setAmount(NEW_AMOUNT);

            when(bankAccountRepository.save(realBankAccount)).thenReturn(expectedBankAccount);
            bankAccountService.update(realBankAccount);

            assertAll(
                    () -> assertThat(realBankAccount.getId()).isEqualTo(expectedBankAccount.getId()),
                    () -> assertThat(realBankAccount.getAmount()).isEqualTo(expectedBankAccount.getAmount())
            );
        }

        @Test
        void checkUpdateShouldThrowNullPointer() {
            BankAccount bankAccountWithNullId = new BankAccount();

            assertThrows(
                    NullPointerException.class,
                    () -> bankAccountService.update(bankAccountWithNullId)
            );
        }
    }

    @Nested
    public class Delete {
        @Test
        void checkDeleteWithRealId() {
            BankAccount testBP = recipientBankAccount();

            doNothing().when(bankAccountRepository).delete(testBP);
            bankAccountService.delete(testBP);

            verify(bankAccountRepository).delete(testBP);
        }

        @Test
        void checkDeleteVerify() {
            BankAccount testBP = new BankAccount();

            doNothing().when(bankAccountRepository).delete(testBP);
            bankAccountService.delete(testBP);

            verify(bankAccountRepository).delete(testBP);
        }
    }


    @Nested
    public class DeleteById {
        @Test
        void checkDeleteByIdVerify() {
            final long ID_IN_DB = 1L;

            doNothing().when(bankAccountRepository).deleteById(ID_IN_DB);
            bankAccountService.deleteById(ID_IN_DB);

            verify(bankAccountRepository).deleteById(ID_IN_DB);
        }

        @Test
        void checkDeleteByIdNoRealId() {
            final long ID_IN_DB = 11241L;

            doNothing().when(bankAccountRepository).deleteById(ID_IN_DB);
            bankAccountService.deleteById(ID_IN_DB);

            verify(bankAccountRepository).deleteById(ID_IN_DB);
        }
    }
}
