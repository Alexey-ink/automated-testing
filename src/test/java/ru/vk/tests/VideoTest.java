package ru.vk.tests;

import org.junit.jupiter.api.Test;

import ru.vk.pages.*;

public class VideoTest extends BaseTest {
    
    @Test
    public void videoTest() {
        String searchValue = "How to train your dragon";

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
