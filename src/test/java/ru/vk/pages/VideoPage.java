package ru.vk.pages;

import com.codeborne.selenide.SelenideElement;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selectors.shadowCss;
import static com.codeborne.selenide.Selenide.*;

public class VideoPage extends OkPage {

    private final By videoSearchPath = By.xpath("//input[@type='search']");
    private final By videoSearchResult = By.xpath("//div[contains(@class, 'video-search-result')]");
    private final By searchButton = By.xpath("//button[contains(@data-l, 'searchCtx')]");

    private final ElementsCollection searchResultsItems = $$x("//div[@data-logger='SimpleLogger']");
    private final By videoPlay = By.xpath("//div[@id='VideoAutoplayPlayerE']");

    private final SelenideElement playIcon =
            $(shadowCss("svg[data-testid='play-icon']", ".shadow-root-container"));
    private final SelenideElement pauseIcon =
            $(shadowCss("svg[data-testid='pause-icon']", ".shadow-root-container"));

    private final SelenideElement slider = $(shadowCss("div[role='slider']", ".shadow-root-container"));

    public VideoPage() {
        checkPage();
    }

    @Override
    public void checkPage() {
        $(videoSearchPath).shouldBe(visible.because("videoSearchPath is not visible"));
        $(searchButton).shouldBe(visible.because("searchButton is not visible"));
    }


    public VideoPage videoSearchClick() {
        $(videoSearchPath).click();
        return this;
    }

    public VideoPage setSearchValue(String searchValue) {
        $(videoSearchPath).setValue(searchValue);
        sleep(500);
        return this;
    }

    public VideoPage searchButtonClick() {
        $(searchButton).click();
        sleep(5000);
        return this;
    }

    public void verifyVideoSearchResult() {
        $(videoSearchResult).should(appear, Duration.ofSeconds(5));
    }

    public VideoPage firstVideoClick() {
        SelenideElement firstSearchResult = this.searchResultsItems.first();
        firstSearchResult.$x(".//div[@data-module='VideoPreviewAutoplay']").click();
        sleep(5000);
        $(videoPlay).shouldBe(visible).click();
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
        $(videoPlay).click();
        this.pauseIcon.shouldBe(visible);
        sleep(500);
        return this;
    }

    public VideoPage videoPauseClick() {
        $(videoPlay).click();
        this.playIcon.shouldBe(hidden);
        sleep(1000);
        return this;
    }
}
