package by.vadzimmatsiushonak.bank.api.service.impl;

import by.vadzimmatsiushonak.bank.api.model.entity.User;
import by.vadzimmatsiushonak.bank.api.repository.UserRepository;
import by.vadzimmatsiushonak.bank.api.util.TestConstants;
import by.vadzimmatsiushonak.bank.api.util.UserTestBuilder;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static by.vadzimmatsiushonak.bank.api.util.CustomerTestBuilder.newCustomerDefault;
import static by.vadzimmatsiushonak.bank.api.util.UserTestBuilder.newBlockedUser;
import static by.vadzimmatsiushonak.bank.api.util.UserTestBuilder.newTechnicalUser;
import static by.vadzimmatsiushonak.bank.api.util.UserTestBuilder.newUserAdmin;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl userService;

    @Nested
    public class Create {

        @Test
        void checkCreateShouldSaveNewUser() {
            long expectedId = UserTestBuilder.COUNT + 1;
            User expectedUser = new User(newCustomerDefault());
            expectedUser.setId(expectedId);
            User newUser = new User(newCustomerDefault());

            when(userRepository.save(newUser)).thenReturn(expectedUser);
            User actualUser = userService.create(newUser);

            assertAll(
                    () -> assertThat(actualUser.getId()).isEqualTo(expectedId),
                    () -> verify(passwordEncoder).encode(expectedUser.getPassword())
            );
        }
    }

    @Nested
    public class FindById {
        @Test
        void checkFindByIdShouldReturnEmptyOptional() {
            final long NO_EXITING_ID = 112312L;
            when(userRepository.findById(NO_EXITING_ID)).thenReturn(Optional.empty());
            Optional<User> actualUser = userService.findById(NO_EXITING_ID);

            assertThat(actualUser).isEmpty();
        }

        @Test
        void checkFindByIdShouldReturnFirstInDB() {
            final long ID_IN_DB = TestConstants.TEST_ID_USER_ADMIN;
            User expected = newUserAdmin();

            when(userRepository.findById(ID_IN_DB)).thenReturn(Optional.of(expected));
            User actualUser = userService.findById(ID_IN_DB).orElse(new User());

            assertThat(actualUser.getId()).isEqualTo(ID_IN_DB);
        }
    }

    @Nested
    public class FindAll {
        @Test
        void checkFindAllVerify() {
            userService.findAll();
            verify(userRepository).findAll();
        }

        @Test
        void checkFindAllShouldReturnCount() {
            final int EXPECTED_SIZE = UserTestBuilder.COUNT;

            when(userRepository.findAll()).thenReturn(
                    List.of(newUserAdmin(),
                            newTechnicalUser(),
                            newBlockedUser())
            );

            assertThat(userService.findAll().size()).isEqualTo(EXPECTED_SIZE);
        }
    }


    @Nested
    public class Update {

        @Test
        void checkUpdateShouldReturnUpdated() {
            String newLogin = "NEW NAME";
            User realUser = newTechnicalUser();
            User expectedUser = newTechnicalUser();
            realUser.setLogin(newLogin);
            expectedUser.setLogin(newLogin);

            when(userRepository.save(realUser)).thenReturn(expectedUser);
            userService.update(realUser);

            assertAll(
                    () -> assertThat(realUser.getId()).isEqualTo(expectedUser.getId()),
                    () -> assertThat(realUser.getLogin()).isEqualTo(expectedUser.getLogin())
            );
        }

        @Test
        void checkUpdateShouldThrowNullPointer() {
            User userWithNullId = new User();

            assertThrows(
                    NullPointerException.class, () -> userService.update(userWithNullId)
            );
        }
    }

    @Nested
    public class Delete {
        @Test
        void checkDeleteWithRealId() {
            User realUser = newTechnicalUser();

            doNothing().when(userRepository).delete(realUser);
            userService.delete(realUser);

            verify(userRepository).delete(realUser);
        }

        @Test
        void checkDeleteVerify() {
            User noRealUser = new User();

            doNothing().when(userRepository).delete(noRealUser);
            userService.delete(noRealUser);

            verify(userRepository).delete(noRealUser);
        }
    }

    @Nested
    public class DeleteById {
        @Test
        void checkDeleteByIdVerify() {
            final long ID_IN_DB = 1L;

            doNothing().when(userRepository).deleteById(ID_IN_DB);
            userService.deleteById(ID_IN_DB);

            verify(userRepository).deleteById(ID_IN_DB);
        }

        @Test
        void checkDeleteByIdNoRealId() {
            final long ID_IN_DB = 11241L;

            doNothing().when(userRepository).deleteById(ID_IN_DB);
            userService.deleteById(ID_IN_DB);

            verify(userRepository).deleteById(ID_IN_DB);
        }
    }
}
