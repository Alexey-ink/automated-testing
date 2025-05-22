package ru.vk.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.actions;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;


import com.codeborne.selenide.ElementsCollection;

public class ProfilePage extends OkPage {

    private final By settingsPath = By.xpath("//*[contains(@data-l, 'settings')]/a");
    private final ElementsCollection postsPath = $$x("//div[@class='feed-w']");

    private final String pageLanguage = $("html").attr("lang");

    private final By feedAction = By.xpath(".//div[@class='feed-action']");
    private final By deleteConfirm = By.xpath(".//*[contains(@class, 'form-actions_yes')]");

    private final By deleteRecordRUPath = By.xpath(".//*[text()='Удалить заметку']");
    private final By deleteRecordENPath = By.xpath(".//*[text()='Delete post']");

    private static final String TIME_XPATH_TEMPLATE = ".//time[text()='%s' or text()='%s']";
    private static final String MESSAGE_TIME_XPATH_TEMPLATE = ".//*[contains(text(), '%s') and .//time[text()='%s' or text()='%s']]";

    public ProfilePage() {
        checkPage();
    }

    @Override
    public void checkPage() {
        $(settingsPath).shouldBe(visible.because("Settings path is not visible"));
    }

    public ProfilePage deletePost() {

        final String RU = "ru";
        final String EN = "en";

        SelenideElement lastPost = this.postsPath.first();

        actions().moveToElement(lastPost.$(feedAction)).perform();
        
        if(this.pageLanguage.equals(RU)) {
            lastPost.$(deleteRecordRUPath).shouldBe(visible).click();
        }
        else if(this.pageLanguage.equals(EN)) {
            lastPost.$(deleteRecordENPath).shouldBe(visible).click();
        }
        else {
            throw new IllegalStateException("Неизвестный язык страницы: " + this.pageLanguage);
        }

        lastPost.$(deleteConfirm).shouldBe(visible).click();
        return new ProfilePage();
    }

    public void verifyPostIsDeleted(String text, String currentTime, String timePlus1) {
        SelenideElement lastPost = this.postsPath.first();
        String fullXpath = String.format(
                MESSAGE_TIME_XPATH_TEMPLATE,
                text,
                currentTime,
                timePlus1
        );
        lastPost.$x(fullXpath).shouldNot(exist);
    }


    public void verifyPostIsPublished(String text) {
        SelenideElement lastPost = this.postsPath.first();
        lastPost.$x(".//*[text()='" + text + "']").shouldBe(visible);
    }

    public void verifyTimePublishedPost(String currentTime, String timePlus1) {
        SelenideElement lastPost = this.postsPath.first();
        String timeXpath = String.format(TIME_XPATH_TEMPLATE, currentTime, timePlus1);
        lastPost.$x(timeXpath).shouldBe(visible);
    }

    public SettingsPage settingsClick() {
        $(settingsPath).shouldBe(visible).click();
        return new SettingsPage();
    }

}
