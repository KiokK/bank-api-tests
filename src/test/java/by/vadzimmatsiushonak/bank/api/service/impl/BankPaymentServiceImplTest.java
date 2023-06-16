package by.vadzimmatsiushonak.bank.api.service.impl;

import by.vadzimmatsiushonak.bank.api.exception.InsufficientFundsException;
import by.vadzimmatsiushonak.bank.api.exception.WrongDataException;
import by.vadzimmatsiushonak.bank.api.model.dto.request.InitiatePaymentRequest;
import by.vadzimmatsiushonak.bank.api.model.entity.BankAccount;
import by.vadzimmatsiushonak.bank.api.model.entity.BankPayment;
import by.vadzimmatsiushonak.bank.api.model.entity.base.PaymentStatus;
import by.vadzimmatsiushonak.bank.api.repository.BankAccountRepository;
import by.vadzimmatsiushonak.bank.api.repository.BankPaymentRepository;
import by.vadzimmatsiushonak.bank.api.util.BankPaymentTestBuilder;
import by.vadzimmatsiushonak.bank.api.util.InitiatePaymentRequestTestBuilder;
import by.vadzimmatsiushonak.bank.api.util.TestConstants;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static by.vadzimmatsiushonak.bank.api.util.BankAccountTestBuilder.recipientBankAccount;
import static by.vadzimmatsiushonak.bank.api.util.BankAccountTestBuilder.senderBankAccount;
import static by.vadzimmatsiushonak.bank.api.util.BankPaymentTestBuilder.*;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.NEW_AMOUNT;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_RECIPIENT_ID_BANK_ACCOUNT;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_SENDER_ID_BANK_ACCOUNT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankPaymentServiceImplTest {

    @Mock
    private BankPaymentRepository bankPaymentRepository;

    @InjectMocks
    private BankPaymentServiceImpl bankPaymentService;

    @Mock
    private BankAccountRepository bankAccountRepository;


    @Nested
    public class Create {
        private InitiatePaymentRequest req2to1 = InitiatePaymentRequestTestBuilder.payUserToAdmin120USD();

        @Test
        void checkCreateShouldSaveNewBankPayment() {
            long expectedId = (long) (BankPaymentTestBuilder.COUNT + 1);
            BankAccount initialSenderBankAccount = senderBankAccount();

            BankPayment newBankPayment = BankPaymentTestBuilder
                    .newBankPaymentByRequest(req2to1, PaymentStatus.ACCEPTED, initialSenderBankAccount);

            BankPayment expactedBankPayment = BankPaymentTestBuilder
                    .newBankPaymentByRequest(req2to1, PaymentStatus.ACCEPTED, initialSenderBankAccount);
            expactedBankPayment.setId(expectedId);

            when(bankPaymentRepository.save(newBankPayment)).thenReturn(expactedBankPayment);
            newBankPayment = bankPaymentService.create(newBankPayment);

            assertThat(newBankPayment.getId()).isEqualTo(expectedId);
        }
    }

    @Nested
    public class FindById {
        @Test
        void checkFindByIdShouldReturnEmptyOptional() {
            final long NO_EXITING_ID = 112312L;
            when(bankPaymentRepository.findById(NO_EXITING_ID)).thenReturn(Optional.empty());
            Optional<BankPayment> actualBankPayment = bankPaymentService.findById(NO_EXITING_ID);

            assertThat(actualBankPayment).isEmpty();
        }

        @Test
        void checkFindByIdShouldReturnFirstInDB() {
            final long ID_IN_DB = 1L;
            BankPayment expected = newBankPayment2To1();

            when(bankPaymentRepository.findById(ID_IN_DB)).thenReturn(Optional.of(expected));
            BankPayment actualBankPayment = bankPaymentService.findById(ID_IN_DB).orElse(new BankPayment());

            assertThat(actualBankPayment.getId()).isEqualTo(ID_IN_DB);
        }
    }

    @Nested
    public class FindAll {
        @Test
        void checkFindAllVerify() {
            bankPaymentService.findAll();
            verify(bankPaymentRepository).findAll();
        }

        @Test
        void checkFindAllShouldReturnCount3() {
            final int EXPECTED_SIZE = BankPaymentTestBuilder.COUNT;

            when(bankPaymentRepository.findAll()).thenReturn(
                    List.of(newBankPayment2To1(),
                            newBankPaymentToTheSameAccount(),
                            newBankPayment3UserToAdmin())
            );

            assertThat(bankPaymentService.findAll().size()).isEqualTo(EXPECTED_SIZE);
        }
    }


    @Nested
    public class Update {

        @Test
        void checkUpdateShouldReturnUpdated() {
            BankPayment realBankPayment = newBankPayment2To1();
            BankPayment expectedPayment = newBankPayment2To1();
            realBankPayment.setAmount(NEW_AMOUNT);
            expectedPayment.setAmount(NEW_AMOUNT);

            when(bankPaymentRepository.save(realBankPayment)).thenReturn(expectedPayment);
            bankPaymentService.update(realBankPayment);

            assertAll(
                    () -> assertThat(realBankPayment.getId()).isEqualTo(expectedPayment.getId()),
                    () -> assertThat(realBankPayment.getAmount()).isEqualTo(expectedPayment.getAmount())
            );
        }

        @Test
        void checkUpdateShouldThrowNullPointer() {
            BankPayment bankPaymentWithNullId = new BankPayment();

            assertThrows(
                    NullPointerException.class,
                    () -> bankPaymentService.update(bankPaymentWithNullId)
            );
        }
    }

    @Nested
    public class Delete {
        @Test
        void checkDeleteWithRealId() {
            BankPayment testBP = newBankPayment2To1();

            doNothing().when(bankPaymentRepository).delete(testBP);
            bankPaymentService.delete(testBP);

            verify(bankPaymentRepository).delete(testBP);
        }

        @Test
        void checkDeleteVerify() {
            BankPayment neRealBankPayment = new BankPayment();

            doNothing().when(bankPaymentRepository).delete(neRealBankPayment);
            bankPaymentService.delete(neRealBankPayment);

            verify(bankPaymentRepository).delete(neRealBankPayment);
        }
    }


    @Nested
    public class DeleteById {
        @Test
        void checkDeleteByIdVerify() {
            final long ID_IN_DB = 1L;

            doNothing().when(bankPaymentRepository).deleteById(ID_IN_DB);
            bankPaymentService.deleteById(ID_IN_DB);

            verify(bankPaymentRepository).deleteById(ID_IN_DB);
        }

        @Test
        void checkDeleteByIdNoRealId() {
            final long ID_IN_DB = 11241L;

            doNothing().when(bankPaymentRepository).deleteById(ID_IN_DB);
            bankPaymentService.deleteById(ID_IN_DB);

            verify(bankPaymentRepository).deleteById(ID_IN_DB);
        }
    }

    @Nested
    public class InitiatePayment {
        private InitiatePaymentRequest payYourself = InitiatePaymentRequestTestBuilder.payToYourself120();
        private InitiatePaymentRequest req2to1 = InitiatePaymentRequestTestBuilder.payUserToAdmin120USD();
        private InitiatePaymentRequest tdInfUSD = InitiatePaymentRequestTestBuilder.payMoreThanPossibleUserToAdmin();

        @Test
        void checkInitiatePaymentShouldThrowWrongDataException() {
            assertThrows(
                    WrongDataException.class, () -> bankPaymentService.initiatePayment(payYourself)
            );
        }

        @Test
        void checkInitiatePaymentShouldThrowInsufficientFundsException() {
            BankAccount expectedSender = senderBankAccount();
            BankAccount expectedRecipient = recipientBankAccount();

            when(bankAccountRepository.findById(TestConstants.TEST_SENDER_ID_BANK_ACCOUNT))
                    .thenReturn(Optional.of(expectedSender));
            when(bankAccountRepository.findById(TEST_RECIPIENT_ID_BANK_ACCOUNT))
                    .thenReturn(Optional.of(expectedRecipient));
            assertThrows(
                    InsufficientFundsException.class,
                    () -> bankPaymentService.initiatePayment(tdInfUSD)
            );
        }

        @Test
        void checkInitiatePaymentShouldReturnBankPayment() {
            BankAccount expectedSenderBankAccount = senderBankAccount();
            expectedSenderBankAccount.setAmount(expectedSenderBankAccount.getAmount().subtract(req2to1.amount));
            BankAccount expectedRecipientBankAccount = recipientBankAccount();
            expectedRecipientBankAccount.setAmount(expectedRecipientBankAccount.getAmount().add(req2to1.amount));

            BankPayment expactedBankPayment = BankPaymentTestBuilder
                    .newBankPaymentByRequest(req2to1, PaymentStatus.ACCEPTED, senderBankAccount());
            expactedBankPayment.setId(3L);

            when(bankAccountRepository.findById(TEST_SENDER_ID_BANK_ACCOUNT)).thenReturn(Optional.of(senderBankAccount()));
            when(bankAccountRepository.findById(TEST_RECIPIENT_ID_BANK_ACCOUNT)).thenReturn(Optional.of(recipientBankAccount()));
            doReturn(expactedBankPayment).when(bankPaymentRepository).save(Mockito.any(BankPayment.class));

            BankPayment actualBankPayment = bankPaymentService.initiatePayment(req2to1);

            assertAll(
                    () -> assertThat(actualBankPayment.getStatus()).isEqualTo(expactedBankPayment.getStatus()),
                    () -> verify(bankAccountRepository).save(expectedSenderBankAccount),
                    () -> verify(bankAccountRepository).save(expectedRecipientBankAccount),
                    () -> verify(bankAccountRepository, times(2)).save(any())
            );
        }
    }
}
