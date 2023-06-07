package by.vadzimmatsiushonak.bank.api.service.impl;

import by.vadzimmatsiushonak.bank.api.exception.InsufficientFundsException;
import by.vadzimmatsiushonak.bank.api.exception.WrongDataException;
import by.vadzimmatsiushonak.bank.api.model.dto.request.InitiatePaymentRequest;
import by.vadzimmatsiushonak.bank.api.model.entity.BankAccount;
import by.vadzimmatsiushonak.bank.api.model.entity.BankPayment;
import by.vadzimmatsiushonak.bank.api.model.entity.base.PaymentStatus;
import by.vadzimmatsiushonak.bank.api.repository.BankAccountRepository;
import by.vadzimmatsiushonak.bank.api.repository.BankPaymentRepository;
import by.vadzimmatsiushonak.bank.api.service.BankAccountService;
import by.vadzimmatsiushonak.bank.api.service.BankPaymentService;
import by.vadzimmatsiushonak.bank.api.util.BankAccountTestBuilder;
import by.vadzimmatsiushonak.bank.api.util.BankPaymentTestBuilder;
import by.vadzimmatsiushonak.bank.api.util.InitiatePaymentRequestTestBuilder;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static by.vadzimmatsiushonak.bank.api.util.ExceptionUtils.new_EntityNotFoundException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class BankPaymentServiceImplTest {

    @SpyBean
    private BankPaymentRepository bankPaymentRepository;

    @Autowired
    private BankPaymentService bankPaymentServiceImpl;

    @SpyBean
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankAccountService bankAccountService;

    @Nested
    public class Create {
        private InitiatePaymentRequest req2to1 = InitiatePaymentRequestTestBuilder.PAY_2_TO_1_120;

        @Test
        void checkCreateShouldSaveNewBankPayment() {
            BankAccount initialSenderBankAccount = BankAccountTestBuilder.SECOND_IN_DB();

            BankPayment newBankPayment = BankPaymentTestBuilder
                    .newBankPaymentByRequest(req2to1, PaymentStatus.ACCEPTED, initialSenderBankAccount);

            BankPayment expactedBankPayment = BankPaymentTestBuilder
                    .newBankPaymentByRequest(req2to1, PaymentStatus.ACCEPTED, initialSenderBankAccount);
            expactedBankPayment.setId(4L);// bankPaymentRepository.count() + 1

            Mockito.doReturn(expactedBankPayment)
                    .when(bankPaymentRepository)
                    .save(newBankPayment);

            System.out.println(newBankPayment.getBankAccount());
            newBankPayment = bankPaymentServiceImpl.create(newBankPayment);

            assertThat(newBankPayment.getId())
                    .isEqualTo(expactedBankPayment.getId());
        }
    }

    @Nested
    public class FindById {
        @Test
        void checkFindByIdShouldReturnEmptyIptional() {
            final long NO_EXITING_ID = 112312L;
            Optional<BankPayment> actualBankPayment = bankPaymentServiceImpl.findById(NO_EXITING_ID);

            assertThat(actualBankPayment)
                    .isEmpty();
        }

        @Test
        void checkFindByIdShouldReturnFirstInDB() {
            final long ID_IN_DB = 1L;
            BankPayment actualBankPayment = bankPaymentServiceImpl.findById(ID_IN_DB).orElse(new BankPayment());

            assertThat(actualBankPayment.getId())
                    .isEqualTo(ID_IN_DB);
        }
    }

    @Nested
    public class FindAll {
        @Test
        void checkFindAllVerify() {
            bankPaymentServiceImpl.findAll();
            verify(bankPaymentRepository, times(2)).findAll();
        }

        @Test
        void checkFindAllShouldReturnCount4() {
            final int EXPECTED_SIZE = BankPaymentTestBuilder.COUNT;

            assertThat(bankPaymentServiceImpl.findAll().size())
                    .isEqualTo(EXPECTED_SIZE);
        }
    }


    @Nested
    public class Update {

        @Test
        void checkUpdateShouldReturnUpdated() {
            BankPayment realBankPayment = BankPaymentTestBuilder.IN_DB_ID_1();
            BankPayment expectedPayment = BankPaymentTestBuilder.IN_DB_ID_1();
            realBankPayment.setAmount(new BigDecimal("1291482.00"));
            expectedPayment.setAmount(new BigDecimal("1291482.00"));
            Mockito.doReturn(expectedPayment)
                    .when(bankPaymentRepository)
                    .save(realBankPayment);
            bankPaymentServiceImpl.update(realBankPayment);
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
                    () -> bankPaymentServiceImpl.update(bankPaymentWithNullId)
            );
        }
    }

    @Nested
    public class Delete {
        @Test
        void checkDeleteWithRealId() {
            BankPayment testBP = BankPaymentTestBuilder.IN_DB_ID_1();
            testBP.setId(12321L);
            Mockito.doNothing()
                    .when(bankPaymentRepository).delete(testBP);
            bankPaymentServiceImpl.delete(testBP);
            verify(bankPaymentRepository).delete(testBP);
        }

        @Test
        void checkDeleteVerify() {
            BankPayment testBP = BankPaymentTestBuilder.IN_DB_ID_1();
            Mockito.doNothing()
                    .when(bankPaymentRepository).delete(testBP);
            bankPaymentServiceImpl.delete(testBP);
            verify(bankPaymentRepository, times(2)).delete(testBP);
        }
    }


    @Nested
    public class DeleteById {
        @Test
        void checkDeleteByIdVerify() {
            final long ID_IN_DB = 1L;
            Mockito.doNothing()
                    .when(bankPaymentRepository).deleteById(ID_IN_DB);
            bankPaymentServiceImpl.deleteById(ID_IN_DB);
            verify(bankPaymentRepository).deleteById(ID_IN_DB);
        }

        @Test
        void checkDeleteByIdNoRealId() {
            final long ID_IN_DB = 11241L;
            Mockito.doNothing()
                    .when(bankPaymentRepository).deleteById(ID_IN_DB);
            bankPaymentServiceImpl.deleteById(ID_IN_DB);
            verify(bankPaymentRepository).deleteById(ID_IN_DB);
        }
    }

    @Nested
    public class InitiatePayment {
        private InitiatePaymentRequest payYourself = InitiatePaymentRequestTestBuilder.PAY_TO_YOURSELF_120;
        private InitiatePaymentRequest req2to1 = InitiatePaymentRequestTestBuilder.PAY_2_TO_1_120;
        private InitiatePaymentRequest tdInfUSD = InitiatePaymentRequestTestBuilder.PAY_2_TO_1_MORE_THAN_POSSIBLE;

        @Test
        void checkInitiatePaymentShouldThrowWrongDataException() {
            assertThrows(
                    WrongDataException.class,
                    () -> bankPaymentServiceImpl.initiatePayment(payYourself)
            );
        }

        @Test
        void checkInitiatePaymentShouldThrowInsufficientFundsException() {
            assertThrows(
                    InsufficientFundsException.class,
                    () -> bankPaymentServiceImpl.initiatePayment(tdInfUSD)
            );
        }

        @Test
        void checkInitiatePaymentShouldReturnBankPayment() {
            BankAccount initialSenderBankAccount = BankAccountTestBuilder.SECOND_IN_DB();
            BankAccount initialRecipientBankAccount = BankAccountTestBuilder.FIRST_IN_DB();

            System.out.println(initialSenderBankAccount.getAmount());
            BigDecimal expectedSenderAmountSub = initialSenderBankAccount.getAmount().subtract(req2to1.amount);
            BigDecimal expectedRecipientAmountSub = initialRecipientBankAccount.getAmount().add(req2to1.amount);
            BankPayment expactedBankPayment = BankPaymentTestBuilder
                    .newBankPaymentByRequest(req2to1, PaymentStatus.ACCEPTED, initialSenderBankAccount);

            Mockito.doReturn(expactedBankPayment)
                    .when(bankPaymentRepository)
                    .save(Mockito.any(BankPayment.class));
            BankPayment actualBankPayment = bankPaymentServiceImpl.initiatePayment(req2to1);

            BankAccount actualSenderBankAccount = bankAccountService.findById(req2to1.senderBankAccountId)
                    .orElseThrow(() -> new_EntityNotFoundException("Test BankAccount", req2to1.senderBankAccountId));
            BankAccount actualRecipientBankAccount = bankAccountService.findById(req2to1.recipientBankAccountId)
                    .orElseThrow(() -> new_EntityNotFoundException("Test BankAccount", req2to1.recipientBankAccountId));

            assertAll(
                    () -> assertThat(actualBankPayment.getStatus()).isEqualTo(expactedBankPayment.getStatus()),
                    () -> assertThat(actualSenderBankAccount.getAmount()).isEqualTo(expectedSenderAmountSub),
                    () -> assertThat(actualRecipientBankAccount.getAmount()).isEqualTo(expectedRecipientAmountSub)
            );
        }
    }
}