package by.vadzimmatsiushonak.bank.api.service.impl;

import by.vadzimmatsiushonak.bank.api.model.entity.User;
import by.vadzimmatsiushonak.bank.api.repository.UserRepository;
import by.vadzimmatsiushonak.bank.api.util.UserTestBuilder;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private CustomUserDetailsService userDetailsService;

    @Nested
    public class LoadUserByUsername {
        @Test
        void checkLoadUserByUsernameShouldReturnRealDetails() {
            User expectedUser = UserTestBuilder.ID1_ADMIN();

            doReturn(Optional.of(expectedUser)).when(userRepository).findByLogin(expectedUser.getLogin());
            UserDetails actualDetails = userDetailsService.loadUserByUsername(expectedUser.getLogin());

            assertAll(
                    () -> assertThat(actualDetails.getUsername()).isEqualTo(expectedUser.getLogin()),
                    () -> assertThat(actualDetails.getPassword()).isEqualTo(expectedUser.getPassword()),
                    () -> assertThat(actualDetails.getAuthorities().toArray())
                            .contains(new SimpleGrantedAuthority(expectedUser.getRole().authority))
            );
        }

        @Test
        void checkLoadUserByUsernameShouldThrowUsernameNotFoundException() {
            User noRealUser = new User();
            noRealUser.setLogin("aoskdoa");

            assertThrows(UsernameNotFoundException.class,
                    () -> userDetailsService.loadUserByUsername(noRealUser.getLogin()));

        }


        @Test
        void checkLoadUserByUsernameShouldThrowUserIsNotActive() {
            User noActiveUser = UserTestBuilder.ID3_NO_ACTIVE();

            doReturn(Optional.of(noActiveUser)).when(userRepository).findByLogin(noActiveUser.getLogin());

            assertThrows(UsernameNotFoundException.class,
                    () -> userDetailsService.loadUserByUsername(noActiveUser.getLogin()));
        }
    }
}
