package com.semenov.front.client;

import com.semenov.front.dto.UserAccountDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class AccountClientTest {

    private RestTemplate restTemplate;
    private AccountClient accountClient;

    @BeforeEach
    void setUp() throws Exception {
        restTemplate = mock(RestTemplate.class);
        accountClient = new AccountClient(restTemplate);

        // Устанавливаем private поле accountServiceUrl вручную через Reflection
        Field urlField = AccountClient.class.getDeclaredField("accountServiceUrl");
        urlField.setAccessible(true);
        urlField.set(accountClient, "http://localhost:8080");
    }

    @Test
    void testCreateUserAccount() {
        UserAccountDto dto = new UserAccountDto();
        accountClient.createUserAccount(dto);

        verify(restTemplate, times(1))
                .postForEntity(eq("http://localhost:8080/api/users"), eq(dto), eq(Void.class));
    }

    @Test
    void testUpdatePasswordByLogin() {
        String login = "login123";
        String newPassword = "newPass";

        accountClient.updatePasswordByLogin(login, newPassword);

        String expectedUrl = "http://localhost:8080/user/login123/editPassword?newPass=newPass";
        verify(restTemplate).postForEntity(eq(expectedUrl), eq(null), eq(Void.class));
    }

    @Test
    void testEditUserAccounts() {
        String login = "login123";
        String name = "John Doe";
        LocalDate birthdate = LocalDate.of(1990, 5, 20);

        accountClient.editUserAccounts(login, name, birthdate);

        String expectedUrl = "http://localhost:8080/user/login123/editUserAccounts?name=John Doe&birthdate=1990-05-20";
        verify(restTemplate).postForEntity(eq(expectedUrl), eq(null), eq(Void.class));
    }
}



