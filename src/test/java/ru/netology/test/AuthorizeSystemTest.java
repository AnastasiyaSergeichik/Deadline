package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.DataSql;
import ru.netology.page.LoginPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorizeSystemTest {
    DataSql dataSql = new DataSql();

    private LoginPage loginPage;

    @BeforeEach
    void setup() {
        loginPage = open("http://localhost:9999", LoginPage.class);
    }

    @AfterAll
    static void clean() throws SQLException {
        DataSql.cleanData();
    }

      @Test
    @Order(1)
    void shouldLoginUser()  throws SQLException {
        val AuthInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(AuthInfo);
        val verificationCode = DataSql.getVerificationCode(AuthInfo.getLogin());
        verificationPage.validVerify(verificationCode);
    }

    @Test
    @Order(2)
    void shouldBlockedIfLoginWithWrongErrorThreeTime() throws SQLException {
        val AuthInfo = DataHelper.getUserErrorPassword();
        loginPage.login(AuthInfo);
        loginPage.showErrorMessage();
        loginPage.clearFields();
        loginPage.login(AuthInfo);
        loginPage.showErrorMessage();
        loginPage.clearFields();
        loginPage.login(AuthInfo);
        loginPage.showErrorMessage();
        val status = dataSql.getUserStatus(AuthInfo.getLogin());
        assertEquals("blocked", status);
    }
}
