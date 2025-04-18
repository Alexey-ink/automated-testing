package ru.vk.tests;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.vk.pages.*;

public class VideoTest extends BaseTest {

    //@Disabled
    @ParameterizedTest
    @ValueSource(strings = {"How to train your dragon", "Холодное сердце 2013"})
    public void videoTest(String searchValue) {

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
