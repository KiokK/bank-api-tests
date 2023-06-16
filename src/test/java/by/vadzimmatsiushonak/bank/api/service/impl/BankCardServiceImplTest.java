package by.vadzimmatsiushonak.bank.api.service.impl;

import by.vadzimmatsiushonak.bank.api.model.entity.BankCard;
import by.vadzimmatsiushonak.bank.api.repository.BankCardRepository;
import by.vadzimmatsiushonak.bank.api.util.BankCardTestBuilder;
import by.vadzimmatsiushonak.bank.api.util.TestConstants;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static by.vadzimmatsiushonak.bank.api.util.BankAccountTestBuilder.COUNT;
import static by.vadzimmatsiushonak.bank.api.util.BankAccountTestBuilder.recipientBankAccount;
import static by.vadzimmatsiushonak.bank.api.util.BankCardTestBuilder.newRecipientBankCard;
import static by.vadzimmatsiushonak.bank.api.util.TestConstants.TEST_NEW_CARD_CVS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankCardServiceImplTest {

    @InjectMocks
    private BankCardServiceImpl bankCardService;

    @Mock
    private BankCardRepository bankCardRepository;

    @Nested
    public class Create {
        @Test
        void checkCreateShouldSaveNewBankCard() {
            final LocalDate expirationDate = TestConstants.TEST_SENDER_CARD_EXPIRATION_DATE;
            BankCard actualBankCard = BankCardTestBuilder
                    .newBankCard(recipientBankAccount(), expirationDate);

            BankCard expectedBankCard = BankCardTestBuilder
                    .newBankCard(recipientBankAccount(), expirationDate);

            final long NEXT_ID = BankCardTestBuilder.COUNT + 1;
            expectedBankCard.setId(NEXT_ID);

            when(bankCardRepository.save(actualBankCard)).thenReturn(expectedBankCard);
            actualBankCard = bankCardService.create(actualBankCard);

            assertThat(actualBankCard.getId()).isEqualTo(NEXT_ID);
        }
    }

    @Nested
    public class FindById {
        @Test
        void checkFindByIdShouldReturnEmptyOptional() {
            final long NO_EXITING_ID = 112312L;
            when(bankCardRepository.findById(NO_EXITING_ID)).thenReturn(Optional.empty());
            Optional<BankCard> actualBankAccount = bankCardService.findById(NO_EXITING_ID);

            assertThat(actualBankAccount).isEmpty();
        }

        @Test
        void checkFindByIdShouldReturnFirstInDB() {
            final long ID_IN_DB = 1L;
            BankCard expected = newRecipientBankCard();

            when(bankCardRepository.findById(ID_IN_DB)).thenReturn(Optional.of(expected));
            BankCard actualBankCard = bankCardService.findById(ID_IN_DB).orElse(new BankCard());

            assertThat(actualBankCard.getId()).isEqualTo(ID_IN_DB);
        }
    }


    @Nested
    public class FindAll {
        @Test
        void checkFindAllVerify() {
            bankCardService.findAll();
            verify(bankCardRepository).findAll();
        }

        @Test
        void checkFindAllShouldReturnCount() {
            final int EXPECTED_SIZE = COUNT;

            when(bankCardRepository.findAll()).thenReturn(
                    List.of(newRecipientBankCard(), BankCardTestBuilder.newSenderBankCard())
            );

            assertThat(bankCardService.findAll().size()).isEqualTo(EXPECTED_SIZE);
        }
    }

    @Nested
    public class Update {

        @Test
        void checkUpdateShouldReturnUpdated() {
            BankCard realBankCard = newRecipientBankCard();
            BankCard expectedBankCard = newRecipientBankCard();
            realBankCard.setCvs(TEST_NEW_CARD_CVS);
            expectedBankCard.setCvs(TEST_NEW_CARD_CVS);

            when(bankCardRepository.save(realBankCard)).thenReturn(expectedBankCard);
            bankCardService.update(realBankCard);

            assertAll(
                    () -> assertThat(realBankCard.getId()).isEqualTo(expectedBankCard.getId()),
                    () -> assertThat(realBankCard.getCvs()).isEqualTo(expectedBankCard.getCvs())
            );
        }

        @Test
        void checkUpdateShouldThrowNullPointer() {
            BankCard bankCardWithNullId = new BankCard();

            assertThrows(
                    NullPointerException.class,
                    () -> bankCardService.update(bankCardWithNullId)
            );
        }
    }

    @Nested
    public class Delete {
        @Test
        void checkDeleteWithRealId() {
            BankCard testBC = newRecipientBankCard();

            doNothing().when(bankCardRepository).delete(testBC);
            bankCardService.delete(testBC);

            verify(bankCardRepository).delete(testBC);
        }

        @Test
        void checkDeleteVerify() {
            BankCard testBC = new BankCard();

            doNothing().when(bankCardRepository).delete(testBC);
            bankCardService.delete(testBC);

            verify(bankCardRepository).delete(testBC);
        }
    }

    @Nested
    public class DeleteById {
        @Test
        void checkDeleteByIdVerify() {
            final long ID_IN_DB = 1L;

            doNothing().when(bankCardRepository).deleteById(ID_IN_DB);
            bankCardService.deleteById(ID_IN_DB);

            verify(bankCardRepository).deleteById(ID_IN_DB);
        }

        @Test
        void checkDeleteByIdNoRealId() {
            final long ID_IN_DB = 11241L;

            doNothing().when(bankCardRepository).deleteById(ID_IN_DB);
            bankCardService.deleteById(ID_IN_DB);

            verify(bankCardRepository).deleteById(ID_IN_DB);
        }
    }
}
