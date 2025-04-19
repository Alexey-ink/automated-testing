package ru.vk.tests;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.vk.pages.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.*;

public class VideoTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(VideoTest.class);

    //@Disabled
    @ParameterizedTest
    @Tag("video")
    @ValueSource(strings = {"How to train your dragon", "Холодное сердце 2013"})
    public void videoTest(String searchValue) {

        log.info("=== Начало теста для видео: '{}' ===", searchValue);
        VideoPage videoPage = new FeedPage()
                .videoClick()
                .videoSearchClick()
                .setSearchValue(searchValue)
                .searchButtonClick();


        videoPage.verifyVideoSearchResult();

        int offsetToZero = videoPage.firstVideoClick().moveSliderToZero().getSliderValue();

        int targetOffset = 30; // будем смещать на 30 процентов

        int oneMoreOffset = videoPage.moveSlider(targetOffset).getSliderValue();

        videoPage.videoPlayClick().videoPauseClick();

        assertAll(
                "Проверка позиций слайдера",
                () -> assertEquals(0, offsetToZero, "Слайдер не сброшен в 0"),
                () -> assertEquals(targetOffset, oneMoreOffset,
                        "Слайдер переместился не на " + targetOffset + "%")
        );
    }  
}
