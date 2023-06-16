package by.vadzimmatsiushonak.bank.api.service.impl;

import by.vadzimmatsiushonak.bank.api.model.entity.Bank;
import by.vadzimmatsiushonak.bank.api.repository.BankRepository;
import by.vadzimmatsiushonak.bank.api.util.BankTestBuilder;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static by.vadzimmatsiushonak.bank.api.util.BankTestBuilder.newBankAdminBank;
import static by.vadzimmatsiushonak.bank.api.util.BankTestBuilder.newBankAlfaBank;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.NEW_AMOUNT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankServiceImplTest {

    @InjectMocks
    private BankServiceImpl bankService;

    @Mock
    private BankRepository bankRepository;

    @Nested
    public class Create {

        @Test
        void checkCreateShouldSaveNewBank() {
            String title = "NEW BANK TITLE";
            long expectedId = BankTestBuilder.COUNT + 1;

            Bank newBank = BankTestBuilder.newBank(title, NEW_AMOUNT);
            Bank expactedBank = BankTestBuilder.newBank(title, NEW_AMOUNT);
            expactedBank.setId(expectedId);

            when(bankRepository.save(newBank)).thenReturn(expactedBank);
            newBank = bankService.create(newBank);

            assertThat(newBank.getId()).isEqualTo(expectedId);
        }
    }

    @Nested
    public class FindById {
        @Test
        void checkFindByIdShouldReturnEmptyOptional() {
            final long NO_EXITING_ID = 112312L;
            when(bankRepository.findById(NO_EXITING_ID)).thenReturn(Optional.empty());
            Optional<Bank> actualBank = bankService.findById(NO_EXITING_ID);

            assertThat(actualBank).isEmpty();
        }

        @Test
        void checkFindByIdShouldReturnFirstInDB() {
            final long ID_IN_DB = 1L;
            Bank expected = newBankAdminBank();

            when(bankRepository.findById(ID_IN_DB)).thenReturn(Optional.of(expected));
            Bank actualBank = bankService.findById(ID_IN_DB).orElse(new Bank());

            assertThat(actualBank.getId()).isEqualTo(ID_IN_DB);
        }
    }

    @Nested
    public class FindAll {
        @Test
        void checkFindAllVerify() {
            bankService.findAll();
            verify(bankRepository).findAll();
        }

        @Test
        void checkFindAllShouldReturnCount() {
            final int EXPECTED_SIZE = BankTestBuilder.COUNT;

            when(bankRepository.findAll()).thenReturn(
                    List.of(newBankAdminBank(), newBankAlfaBank())
            );

            assertThat(bankService.findAll().size()).isEqualTo(EXPECTED_SIZE);
        }
    }

    @Nested
    public class Update {

        @Test
        void checkUpdateShouldReturnUpdated() {
            String title = "NEW BANK TITLE";
            Bank realBank = newBankAdminBank();
            Bank expectedBank = newBankAdminBank();
            realBank.setTitle(title);
            expectedBank.setTitle(title);

            when(bankRepository.save(realBank)).thenReturn(expectedBank);
            bankService.update(realBank);

            assertAll(
                    () -> assertThat(realBank.getId()).isEqualTo(expectedBank.getId()),
                    () -> assertThat(realBank.getTitle()).isEqualTo(expectedBank.getTitle())
            );
        }

        @Test
        void checkUpdateShouldThrowNullPointer() {
            Bank bankWithNullId = new Bank();

            assertThrows(
                    NullPointerException.class,
                    () -> bankService.update(bankWithNullId)
            );
        }
    }

    @Nested
    public class Delete {
        @Test
        void checkDeleteWithRealId() {
            Bank realBank = newBankAdminBank();

            doNothing().when(bankRepository).delete(realBank);
            bankService.delete(realBank);

            verify(bankRepository).delete(realBank);
        }

        @Test
        void checkDeleteVerify() {
            Bank realBank = newBankAdminBank();

            doNothing().when(bankRepository).delete(realBank);
            bankService.delete(realBank);

            verify(bankRepository).delete(realBank);
        }
    }

    @Nested
    public class DeleteById {
        @Test
        void checkDeleteByIdVerify() {
            final long ID_IN_DB = 1L;

            doNothing().when(bankRepository).deleteById(ID_IN_DB);
            bankService.deleteById(ID_IN_DB);

            verify(bankRepository).deleteById(ID_IN_DB);
        }

        @Test
        void checkDeleteByIdNoRealId() {
            final long ID_IN_DB = 11241L;

            doNothing().when(bankRepository).deleteById(ID_IN_DB);
            bankService.deleteById(ID_IN_DB);

            verify(bankRepository).deleteById(ID_IN_DB);
        }
    }
}
