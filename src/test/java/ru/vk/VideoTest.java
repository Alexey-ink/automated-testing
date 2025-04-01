package ru.vk;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.shadowCss;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.actions;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import com.codeborne.selenide.SelenideElement;

import ru.vk.pages.*;

public class VideoTest extends BaseTest {
    
    @Test
    public void videoTest() throws InterruptedException {
        FeedPage.VideosPath.click();
        VideoPage.VideoSearchPath.click();
        VideoPage.VideoSearchPath.setValue("Как приручить дракона");
        Thread.sleep(500);
        VideoPage.SearchButton.click();
        Thread.sleep(5000);

        VideoPage.VideoSearchResult.should(appear, Duration.ofSeconds(5));

        SelenideElement FirstSearchResult = VideoPage.SearchResultsItems.first();
        FirstSearchResult.$x(".//div[@data-module='VideoPreviewAutoplay']").click();

        Thread.sleep(5000);
        VideoPage.VideoPlay.shouldBe(visible).click();
        Thread.sleep(2000);


        SelenideElement slider = $(shadowCss("div[role='slider']", ".shadow-root-container"))
                .shouldBe(visible);

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

        Thread.sleep(5000);
        VideoPage.VideoPlay.click();
        VideoPage.PauseIcon.shouldBe(visible);
        Thread.sleep(500);
        VideoPage.VideoPlay.click();
        VideoPage.PlayIcon.shouldBe(hidden);
        Thread.sleep(1000);

    }  
}
