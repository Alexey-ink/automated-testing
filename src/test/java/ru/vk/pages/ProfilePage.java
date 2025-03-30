package ru.vk.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage extends OkPage {
    public static final SelenideElement SettingsPath = $x("//*[contains(@data-l, 'settings')]/a");
}
