package ru.vk.pages;

import static com.codeborne.selenide.Selenide.$x;
import com.codeborne.selenide.SelenideElement;

public class SettingsPage extends OkPage {

    public static final SelenideElement personalInfoPath = $x("//*[@data-l='t,profile_form']");

    public PersonalInfoPage personalInfoClick() {
        personalInfoPath.click();
        return new PersonalInfoPage();
    }
}
