package ru.vk.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import com.codeborne.selenide.SelenideElement;

public class SettingsPage extends OkPage {

    private final SelenideElement personalInfoPath = $x("//*[@data-l='t,profile_form']");

    public SettingsPage() {
        checkPage();
    }

    @Override
    public void checkPage() {
        personalInfoPath.shouldBe(visible.because("personalInfoPath is not visible"));
    }

    public PersonalInfoPage personalInfoClick() {
        personalInfoPath.click();
        return new PersonalInfoPage();
    }
}
