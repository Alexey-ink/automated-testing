package ru.vk.pages.components;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.SelenideElement;

public class Header {
    
    public static final SelenideElement messagesPath = $x("//*[@data-l='t,messages']");
    public static final SelenideElement discussionsPath = $x("//*[@data-l='t,discussions']");
    public static final SelenideElement notificationsPath = $x("//*[@data-l='t,notifications']");
    public static final SelenideElement guestsPath = $x("//*[@data-l='t,guests']");
    public static final SelenideElement marksPath = $x("//*[@data-l='t,marks']");
    public static final SelenideElement videoPath = $x("//*[@data-l='t,video']");
    public static final SelenideElement musicPath = $x("//*[@data-l='t,music']");

    public Header() {
        checkToolbar();
    }

    public void checkToolbar() {
        messagesPath.shouldBe(visible.because("Messages path is not visible"));
        discussionsPath.shouldBe(visible.because("Discussions path is not visible"));
        notificationsPath.shouldBe(visible.because("Notifications path is not visible"));
        guestsPath.shouldBe(visible.because("Guests path is not visible"));
        marksPath.shouldBe(visible.because("Marks path is not visible"));
        videoPath.shouldBe(visible.because("Video path is not visible"));  
        musicPath.shouldBe(visible.because("Music path is not visible"));
    }

    public static boolean isDisplayed() {
        return messagesPath.isDisplayed() 
                && discussionsPath.isDisplayed()
                && notificationsPath.isDisplayed()
                && guestsPath.isDisplayed()
                && marksPath.isDisplayed()
                && videoPath.isDisplayed()
                && musicPath.isDisplayed();
    }
}
