package ru.vk.pages.components;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.SelenideElement;

import ru.vk.pages.FeedPage;

public class PostRecord {
    // Posting record xPaths

    public static final SelenideElement postRecordPath = $x("//*[@data-l=\"t,feed.posting.ui.input\"]");
    public static final SelenideElement enterTextPath = $x("//div[@role='textbox']");
    public static final SelenideElement shareRecordPath = $x("//button[@data-l='t,button.submit']");

    public PostRecord() {
        checkPostRecord();
    }

    public void checkPostRecord() {
        postRecordPath.shouldBe(visible.because("Post record is not displayed"));
    }

    public PostRecord recordClick() {
        postRecordPath.click();
        return this;
    }

    public PostRecord enterTextClick(String text) {
        enterTextPath.setValue(text);
        return this;
    }

    public FeedPage shareRecordClick() {
        shareRecordPath.click();
        return new FeedPage();
    }


}
