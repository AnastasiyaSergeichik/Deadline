package ru.netology.page;

import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;


public class VerificationPage {
    private final SelenideElement codeInput = $("[data-test-id=code] input");
    private final SelenideElement verifyButton = $("[data-test-id=action-verify]");


    public VerificationPage() {


        codeInput.shouldBe(visible);
    }


    public void validVerify(String verificationCode) {
        codeInput.setValue(verificationCode);
        verifyButton.click();
        new DashboardPage();
    }
}

