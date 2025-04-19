package ru.vk.pages.components;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.ElementsCollection;

import org.openqa.selenium.By;
import ru.vk.pages.FeedPage;

public class PostRecord {
    private final By root = By.xpath("//div[@class='mlr_cnts']");

    private final By postRecordPath = By.xpath("//*[@data-l=\"t,feed.posting.ui.input\"]");
    private final By enterTextPath = By.xpath(".//div[@role='textbox']");
    private final By shareRecordPath = By.xpath(".//button[@data-l='t,button.submit']");
    private final By attachMusicPath= By.xpath(".//div[@data-l='t,button.music']");
    private final By donePath = By.xpath("//button[@data-id='done']");

    private final By searchInputPath= By.xpath("//input[@data-id='searchInput']");

    public PostRecord() {
        checkPostRecord();
    }

    public void checkPostRecord() {
        $(postRecordPath).shouldBe(visible.because("Post record is not displayed"));
    }

    public PostRecord recordClick() {
        $(postRecordPath).shouldBe(visible).click();
        return this;
    }

    public PostRecord enterTextClick(String text) {
        $(root).$(enterTextPath).shouldBe(visible).setValue(text);
        return this;
    }

    public PostRecord attachSong(String song) {
        if(song != null) {
            $(root).$(attachMusicPath).click();
            $(searchInputPath).setValue(song).pressEnter();
            ElementsCollection musicItems = $$x("//div[@data-action='track']");
            musicItems.first().click();
            $(donePath).click();
        }
        return this;
    }

    public FeedPage shareRecordClick() {
        $(root).$(shareRecordPath).shouldBe(visible).click();
        return new FeedPage();
    }

}
