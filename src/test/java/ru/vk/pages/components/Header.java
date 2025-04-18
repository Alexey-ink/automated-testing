package ru.vk.pages.components;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

import org.openqa.selenium.By;

public class Header {
    
    public final By messagesPath = By.xpath("//*[@data-l='t,messages']");
    public final By discussionsPath = By.xpath("//*[@data-l='t,discussions']");
    public final By notificationsPath = By.xpath("//*[@data-l='t,notifications']");
    public final By guestsPath = By.xpath("//*[@data-l='t,guests']");
    public final By marksPath = By.xpath("//*[@data-l='t,marks']");
    public final By videoPath = By.xpath("//*[@data-l='t,video']");
    public final By musicPath = By.xpath("//*[@data-l='t,music']");
    public final By searchPath = By.xpath("//input[@data-uikit-old='Input']");

    public Header() {
        checkToolbar();
    }

    public void checkToolbar() {
        $(messagesPath).shouldBe(visible.because("Messages path is not visible"));
        $(discussionsPath).shouldBe(visible.because("Discussions path is not visible"));
        $(notificationsPath).shouldBe(visible.because("Notifications path is not visible"));
        $(guestsPath).shouldBe(visible.because("Guests path is not visible"));
        $(marksPath).shouldBe(visible.because("Marks path is not visible"));
        $(videoPath).shouldBe(visible.because("Video path is not visible"));
        $(musicPath).shouldBe(visible.because("Music path is not visible"));
    }

    public boolean isDisplayed() {
        return $(messagesPath).isDisplayed()
                && $(discussionsPath).isDisplayed()
                && $(notificationsPath).isDisplayed()
                && $(guestsPath).isDisplayed()
                && $(marksPath).isDisplayed()
                && $(videoPath).isDisplayed()
                && $(musicPath).isDisplayed();
    }

    public void clickMessages() {
        $(messagesPath).shouldBe(visible).click();
    }

    public void clickVideos() {
        $(videoPath).shouldBe(visible).click();
    }


}
