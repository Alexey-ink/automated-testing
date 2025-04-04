package ru.vk.pages;

import com.codeborne.selenide.SelenideElement;

import com.codeborne.selenide.ElementsCollection;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selectors.shadowCss;
import static com.codeborne.selenide.Selenide.*;

public class VideoPage extends OkPage {

    public static final SelenideElement videoSearchPath = $x("//input[@type='search']");
    public static final SelenideElement videoSearchResult = $x("//div[contains(@class, 'video-search-result')]");
    public static final SelenideElement searchButton = $x("//button[contains(@data-l, 'searchCtx')]");
    
    public static final SelenideElement searchIcon = $x("//span[contains(@class, 'global-search')]");
    public static final SelenideElement searchFilters = $x("//div[contains(@class, 'video-search-filters')]");
    public static final ElementsCollection searchResultsItems = $$x("//div[@data-logger='SimpleLogger']");

    public static final SelenideElement videoPlay = $x("//div[@id='VideoAutoplayPlayerE']");
    public static final SelenideElement videoFrame = $x("//div[@id='vp_w']");

    public static final SelenideElement playIcon =
            $(shadowCss("svg[data-testid='play-icon']", ".shadow-root-container"));
    public static final SelenideElement pauseIcon =
            $(shadowCss("svg[data-testid='pause-icon']", ".shadow-root-container"));

    public static final SelenideElement slider = $(shadowCss("div[role='slider']", ".shadow-root-container"));

    public VideoPage videoSearchClick() {
        this.videoSearchPath.click();
        return this;
    }

    public VideoPage setSearchValue(String searchValue) {
        videoSearchPath.setValue("Как приручить дракона");
        sleep(500);
        return this;
    }

    public VideoPage searchButtonClick() {
        this.searchButton.click();
        sleep(5000);
        return this;
    }

    public void verifyVideoSearchResult() {
        this.videoSearchResult.should(appear, Duration.ofSeconds(5));
    }

    public VideoPage firstVideoClick() {
        SelenideElement firstSearchResult = this.searchResultsItems.first();
        firstSearchResult.$x(".//div[@data-module='VideoPreviewAutoplay']").click();
        sleep(5000);
        this.videoPlay.shouldBe(visible).click();
        sleep(2000);
        return this;
    }

    public VideoPage verifySlider() {
        this.slider.shouldBe(visible);
        return this;
    }

    public void moveSlider() {
        double initialValue = Double.parseDouble(slider.getAttribute("aria-valuenow"));
        int sliderWidth = slider.getSize().getWidth();
        int sliderHeight = slider.getSize().getHeight();

        int offset = (int)(sliderWidth * 0.3);

        actions()
                .moveToElement(slider, 10, sliderHeight/2)
                .pause(300)
                .clickAndHold()
                .pause(300)
                .moveByOffset(offset, 0)
                .pause(300)
                .release()
                .pause(500)
                .perform();

        slider.shouldNotHave(
                attribute("aria-valuenow", String.valueOf(initialValue)),
                Duration.ofSeconds(5)
        );
    }

    public VideoPage videoPlayClick() {
        this.videoPlay.click();
        this.pauseIcon.shouldBe(visible);
        sleep(500);
        return this;
    }

    public VideoPage videoPauseClick() {
        VideoPage.videoPlay.click();
        VideoPage.playIcon.shouldBe(hidden);
        sleep(1000);
        return this;
    }






}
