package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.DataSql;
import ru.netology.page.LoginPage;


import static com.codeborne.selenide.Selenide.open;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorizeSystemTest {

    private LoginPage loginPage;

    @BeforeEach
    void setup() {
        loginPage = open("http://localhost:9999", LoginPage.class);
    }

    @AfterAll
    static void clean() {
        DataSql.cleanData();
    }

    @Test
    void shouldLoginUser() {
        val AuthInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(AuthInfo);
        val verificationCode = DataSql.getVerificationCode(AuthInfo.getLogin());
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldBlockedIfLoginWithWrongErrorThreeTime() {
        val AuthInfo = DataHelper.getUserErrorPassword();
        loginPage.login(AuthInfo);
        loginPage.showErrorMessage();
        loginPage.clearFields();
        loginPage.login(AuthInfo);
        loginPage.showErrorMessage();
        loginPage.clearFields();
        loginPage.login(AuthInfo);
        loginPage.showErrorMessage();
        val status = DataSql.getUserStatus(AuthInfo.getLogin());
        assertEquals("blocked", status);
    }
}
