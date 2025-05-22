package ru.vk.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class PhotoPageWithPhotos extends OkPage implements PhotoPage {

    By photoStreamPath = By.xpath(".//photo-stream");

    public PhotoPageWithPhotos() {
        checkPage();
    }

    @Override
    void checkPage() {
        $(allPhotosPath).shouldBe(visible.because("allAlbumsPath is not visible"));
        $(albumsPath).shouldBe(visible.because("albumsPath is not visible"));
        $(sharedAlbumsPath).shouldBe(visible.because("sharedAlbumsPath is not visible"));
        $(createAlbumPath).shouldBe(visible.because("createAlbumPath is not visible"));
        $(uploadPhotoPath).shouldBe(visible.because("uploadPhotoPath is not visible"));

        $(portletPhotoStreamPath).shouldBe(visible.because("portletPhotoStreamPath is not visible"));
    }

    public int photosCount() {
        String countStr = $(portletPhotoStreamPath).$(photoStreamPath).getAttribute("count");
        int count = Integer.parseInt(countStr);
        return count;
    }

}
