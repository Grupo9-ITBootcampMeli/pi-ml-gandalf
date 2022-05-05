package com.piml.gandalf.gandalf.service;

import com.piml.gandalf.gandalf.dto.SignInDTO;
import com.piml.gandalf.gandalf.dto.SignInResponseDTO;
import com.piml.gandalf.gandalf.entity.User;
import com.piml.gandalf.gandalf.exception.handler.SignInNotAuthorizedException;
import com.piml.gandalf.gandalf.exception.handler.UserAlreadyExistsException;
import com.piml.gandalf.gandalf.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class UserServiceTests {
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    public void setupService() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    @DisplayName("Should create an user")
    public void shouldCreateUser() {
        User createUser = User.builder()
                .name("Test user")
                .username("username")
                .role("seller")
                .password("123")
                .cpf("12345647901")
                .email("teste@ml.com")
                .build();

        Mockito.when(userRepository.save(createUser)).thenReturn(createUser);

        Assertions.assertDoesNotThrow(() -> {
            User result = userService.create(createUser);
            Assertions.assertEquals(createUser, result);
        });
    }

    @Test
    @DisplayName("Should not create an existed user")
    public void shouldNotCreateUser() {
        User createUser = User.builder()
                .name("Test user")
                .username("username")
                .role("seller")
                .password("123")
                .cpf("12345647901")
                .email("teste@ml.com")
                .build();

        Mockito.when(userRepository.findTopByCpfOrUsernameOrEmail(createUser.getCpf(), createUser.getUsername(), createUser.getEmail())).thenReturn(java.util.Optional.of(createUser));

        RuntimeException givenException = Assertions.assertThrows(UserAlreadyExistsException.class, () -> {
                    User result = userService.create(createUser);
                }
        );
        Assertions.assertEquals("User already exists", givenException.getMessage());
    }

    @Test
    @DisplayName("Should sign in")
    public void shouldSignIn() {

        User createUser = User.builder()
                .name("Test user")
                .username("username")
                .role("seller")
                .password("123")
                .cpf("12345647901")
                .email("teste@ml.com")
                .build();

        SignInDTO signInfo = new SignInDTO("username", "123");

        Mockito.when(userRepository.findByUsernameAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.of(createUser));

        Assertions.assertDoesNotThrow(() -> {
            SignInResponseDTO dto = userService.signIn(signInfo);
        });
    }

    @Test
    @DisplayName("Should not sign in")
    public void shouldNotSignIn() {

        SignInDTO signInfo = new SignInDTO("username", "123");
        User createUser = User.builder()
                .name("Test user")
                .username("username")
                .role("seller")
                .password("123")
                .cpf("12345647901")
                .email("teste@ml.com")
                .build();

        Mockito.when(userRepository.findByUsernameAndPassword(signInfo.getUsername(), signInfo.getPassword())).thenReturn(Optional.of(createUser));

        RuntimeException givenException = Assertions.assertThrows(SignInNotAuthorizedException.class, () -> {
                    userService.signIn(signInfo);
                }
        );
        Assertions.assertEquals("User not found", givenException.getMessage());
    }
    @Test
    @DisplayName("Should get an user by id")
    public void shouldGetUserById(){
        User createUser = User.builder()
                .name("Test user")
                .username("username")
                .role("seller")
                .password("123")
                .cpf("12345647901")
                .email("teste@ml.com")
                .build();

        Mockito.when(userRepository.getById(Mockito.anyLong())).thenReturn(createUser);

        Assertions.assertDoesNotThrow(()->{
            User result = userService.getById(1L);
            Assertions.assertEquals(createUser, result);
        });
    }

}
