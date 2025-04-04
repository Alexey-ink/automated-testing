package ru.vk.tests;
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
        String searchValue = "Как приручить дракона";

        VideoPage videoPage = new FeedPage()
                .videoClick()
                .videoSearchClick()
                .setSearchValue(searchValue)
                .searchButtonClick();

        videoPage.verifyVideoSearchResult();

        VideoPage newVideoPage = new VideoPage().firstVideoClick();

        newVideoPage.verifySlider().moveSlider();

        newVideoPage.videoPlayClick().videoPauseClick();
    }  
}
