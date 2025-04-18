package ru.vk.tests;

import org.junit.jupiter.api.Test;
import ru.vk.pages.FeedPage;
import ru.vk.pages.PhotoPage;
import ru.vk.pages.PhotoPageWithPhotos;

import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhotoTest extends BaseTest {

    @Test
    public void testUploadPhoto() throws InterruptedException {

        PhotoPage photoPage = new FeedPage().photosClick();
        int prevCount;

        if (photoPage instanceof PhotoPageWithPhotos) {
            prevCount = ((PhotoPageWithPhotos) photoPage).photosCount();
        } else {
            prevCount = 0;
        }

        PhotoPageWithPhotos photoPageWithPhotos = photoPage.uploadPhoto();

        refresh();

        int newCount = photoPageWithPhotos.photosCount();

        assertEquals(prevCount + 1, newCount, "Количество фотографий должно увеличиться на 1 после загрузки");
    }

}
