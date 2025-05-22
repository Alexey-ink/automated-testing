package ru.vk.pages.components;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

import org.openqa.selenium.By;

public class Header {
    private final By root = By.xpath("//div[@class='toolbar_decor']");

    public final By messagesPath = By.xpath(".//*[@data-l='t,messages']");
    public final By discussionsPath = By.xpath(".//*[@data-l='t,discussions']");
    public final By notificationsPath = By.xpath(".//*[@data-l='t,notifications']");
    public final By guestsPath = By.xpath(".//*[@data-l='t,guests']");
    public final By marksPath = By.xpath(".//*[@data-l='t,marks']");
    public final By videoPath = By.xpath(".//*[@data-l='t,video']");
    public final By musicPath = By.xpath(".//*[@data-l='t,music']");
    public final By searchPath = By.xpath(".//input[@data-uikit-old='Input']");

    public Header() {
        checkToolbar();
    }

    public void checkToolbar() {
        $(root).$(messagesPath).shouldBe(visible.because("Messages path is not visible"));
        $(root).$(discussionsPath).shouldBe(visible.because("Discussions path is not visible"));
        $(root).$(notificationsPath).shouldBe(visible.because("Notifications path is not visible"));
        $(root).$(guestsPath).shouldBe(visible.because("Guests path is not visible"));
        $(root).$(marksPath).shouldBe(visible.because("Marks path is not visible"));
        $(root).$(videoPath).shouldBe(visible.because("Video path is not visible"));
        $(root).$(musicPath).shouldBe(visible.because("Music path is not visible"));
    }

    public void clickMessages() {
        $(root).$(messagesPath).shouldBe(visible).click();
    }

    public void clickVideos() {
        $(root).$(videoPath).shouldBe(visible).click();
    }

}
