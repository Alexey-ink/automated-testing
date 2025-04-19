package ru.vk.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.vk.pages.FeedPage;
import ru.vk.pages.PhotoPage;
import ru.vk.pages.PhotoPageWithPhotos;

import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhotoTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(PhotoTest.class);

    @Test
    @Tag("photo")
    public void testUploadPhoto() throws InterruptedException {

        log.info("═══════════════════════════════════════════════════");
        log.info("Начало теста загрузки фотографии");
        PhotoPage photoPage = new FeedPage().photosClick();
        int prevCount;

        if (photoPage instanceof PhotoPageWithPhotos) {
            prevCount = ((PhotoPageWithPhotos) photoPage).photosCount();
        } else {
            prevCount = 0;
        }

        PhotoPageWithPhotos photoPageWithPhotos = photoPage.uploadPhoto();
        log.info("Фотография загружена");
        refresh();
        int newCount = photoPageWithPhotos.photosCount();

        assertEquals(prevCount + 1, newCount, "Количество фотографий должно увеличиться на 1 после загрузки");
        log.info("✅ Тест успешно завершен. Новое количество фотографий: {}", newCount);
        log.info("═══════════════════════════════════════════════════\n");
    }

}
