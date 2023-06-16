package by.vadzimmatsiushonak.bank.api.service.impl;

import by.vadzimmatsiushonak.bank.api.model.entity.Customer;
import by.vadzimmatsiushonak.bank.api.repository.CustomerRepository;
import by.vadzimmatsiushonak.bank.api.util.CustomerTestBuilder;
import by.vadzimmatsiushonak.bank.api.util.TestConstants;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Nested
    public class Create {

        @Test
        void checkCreateShouldSaveNewCustomer() {
            long expectedId = CustomerTestBuilder.COUNT + 1;

            Customer newCustomer = CustomerTestBuilder.newCustomerDefault();
            Customer expectedCustomer = CustomerTestBuilder.newCustomerDefault();

            expectedCustomer.setId(expectedId);

            when(customerRepository.save(newCustomer)).thenReturn(expectedCustomer);
            newCustomer = customerService.create(newCustomer);

            assertThat(newCustomer.getId()).isEqualTo(expectedId);
        }
    }

    @Nested
    public class FindById {
        @Test
        void checkFindByIdShouldReturnEmptyOptional() {
            final long NO_EXITING_ID = 112312L;
            when(customerRepository.findById(NO_EXITING_ID)).thenReturn(Optional.empty());
            Optional<Customer> actualCustomer = customerService.findById(NO_EXITING_ID);

            assertThat(actualCustomer).isEmpty();
        }

        @Test
        void checkFindByIdShouldReturnFirstInDB() {
            final long ID_IN_DB = TestConstants.TEST_RECIPIENT_ID;
            Customer expected = CustomerTestBuilder.newRecipientId1();

            when(customerRepository.findById(ID_IN_DB)).thenReturn(Optional.of(expected));
            Customer actualCustomer = customerService.findById(ID_IN_DB).orElse(new Customer());

            assertThat(actualCustomer.getId()).isEqualTo(ID_IN_DB);
        }
    }

    @Nested
    public class FindAll {
        @Test
        void checkFindAllVerify() {
            customerService.findAll();
            verify(customerRepository).findAll();
        }

        @Test
        void checkFindAllShouldReturnCount() {
            final int EXPECTED_SIZE = CustomerTestBuilder.COUNT;

            when(customerRepository.findAll()).thenReturn(
                    List.of(CustomerTestBuilder.newRecipientId1(), CustomerTestBuilder.newSenderId2())
            );

            assertThat(customerService.findAll().size()).isEqualTo(EXPECTED_SIZE);
        }
    }


    @Nested
    public class Update {

        @Test
        void checkUpdateShouldReturnUpdated() {
            String newName = "NEW NAME";
            Customer realCustomer = CustomerTestBuilder.newRecipientId1();
            Customer expectedCustomer = CustomerTestBuilder.newRecipientId1();
            realCustomer.setName(newName);
            expectedCustomer.setName(newName);

            when(customerRepository.save(realCustomer)).thenReturn(expectedCustomer);
            customerService.update(realCustomer);

            assertAll(
                    () -> assertThat(realCustomer.getId()).isEqualTo(expectedCustomer.getId()),
                    () -> assertThat(realCustomer.getName()).isEqualTo(expectedCustomer.getName())
            );
        }

        @Test
        void checkUpdateShouldThrowNullPointer() {
            Customer customerWithNullId = new Customer();

            assertThrows(
                    NullPointerException.class, () -> customerService.update(customerWithNullId)
            );
        }
    }

    @Nested
    public class Delete {
        @Test
        void checkDeleteWithRealId() {
            Customer realCustomer = CustomerTestBuilder.newRecipientId1();

            doNothing().when(customerRepository).delete(realCustomer);
            customerService.delete(realCustomer);

            verify(customerRepository).delete(realCustomer);
        }

        @Test
        void checkDeleteVerify() {
            Customer emptyCustomer = new Customer();

            doNothing().when(customerRepository).delete(emptyCustomer);
            customerService.delete(emptyCustomer);

            verify(customerRepository).delete(emptyCustomer);
        }
    }

    @Nested
    public class DeleteById {
        @Test
        void checkDeleteByIdVerify() {
            final long ID_IN_DB = 1L;

            doNothing().when(customerRepository).deleteById(ID_IN_DB);
            customerService.deleteById(ID_IN_DB);

            verify(customerRepository).deleteById(ID_IN_DB);
        }

        @Test
        void checkDeleteByIdNoRealId() {
            final long ID_IN_DB = 11241L;

            doNothing().when(customerRepository).deleteById(ID_IN_DB);
            customerService.deleteById(ID_IN_DB);

            verify(customerRepository).deleteById(ID_IN_DB);
        }
    }
}
