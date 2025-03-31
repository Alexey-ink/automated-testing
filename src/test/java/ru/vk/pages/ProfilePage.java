package ru.vk.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;


import com.codeborne.selenide.ElementsCollection;

public class ProfilePage extends OkPage {
    public static final SelenideElement SettingsPath = $x("//*[contains(@data-l, 'settings')]/a");
    public static final ElementsCollection PostsPath = $$x("//div[@class='feed-w']");

    public static final String pageLanguage = $("html").attr("lang");
}
