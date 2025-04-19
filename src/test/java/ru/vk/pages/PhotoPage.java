package ru.vk.pages;

import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public interface PhotoPage {

    By allPhotosPath = By.xpath("//a[@id='tab-all-photos']");
    By albumsPath = By.xpath("//a[@id='tab-albums']");
    By sharedAlbumsPath = By.xpath("//a[@id='tab-shared-albums']");
    By createAlbumPath = By.xpath("//a[@data-l='t,create-photo-album']");
    By uploadPhotoPath = By.xpath("//span[@data-l='t,upload-new-photo']");

    By fileInputPath = By.xpath(".//input[@type='file']");

    By portletPhotoStreamPath = By.xpath("//div[@class='portlet photo-stream']");

    static PhotoPage checkForPhotos() {
        try {
            $(portletPhotoStreamPath).shouldBe(visible
                    .because("portletPhotoStreamPath is not visible, значит страница без фотографий"));
            return new PhotoPageWithPhotos();
        } catch (Throwable e) {
            return new PhotoPageWithoutPhotos();
        }
    }

    default PhotoPageWithPhotos uploadPhoto() throws InterruptedException {
        File file = new File("src/test/resources/cat.jpg");
        $(uploadPhotoPath).$(fileInputPath).uploadFile(file);
        sleep(100); // без sleep post-запрос похоже не успевает обработаться,
                                // тк после аплоада фотки делаем сразу refresh (в PhotoTest)
        return new PhotoPageWithPhotos();
    }

}
