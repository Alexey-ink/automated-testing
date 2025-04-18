package ru.vk.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class PhotoPageWithoutPhotos extends OkPage implements PhotoPage {

    public PhotoPageWithoutPhotos() {
        checkPage();
    }

    @Override
    void checkPage() {
        $(allPhotosPath).shouldBe(visible.because("allAlbumsPath is not visible"));
        $(albumsPath).shouldBe(visible.because("albumsPath is not visible"));
        $(sharedAlbumsPath).shouldBe(visible.because("sharedAlbumsPath is not visible"));
        $(createAlbumPath).shouldBe(visible.because("createAlbumPath is not visible"));
        $(uploadPhotoPath).shouldBe(visible.because("uploadPhotoPath is not visible"));
    }
}
