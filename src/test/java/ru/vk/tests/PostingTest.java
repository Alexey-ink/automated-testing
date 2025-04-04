package ru.vk.tests;

import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.codeborne.selenide.SelenideElement;
import ru.vk.pages.*;

public class PostingTest extends BaseTest {

    //@Disabled
    @DisplayName("Проверка выкладывания записи на стену и ее удаления")
    @ParameterizedTest
    @ValueSource(strings = {"Testing the posting of a record", "Тестирование"})
    public void testPostingRecord(String text) throws InterruptedException {

        FeedPage feedPage = new FeedPage().postClick()
                .recordClick()
                .enterTextClick(text)
                .shareRecordClick();

        ProfilePage profilePage = feedPage.profileClick();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String currentTime = now.format(formatter);
        String timePlus1 = now.plusMinutes(1).format(formatter);

        refresh();

        assertAll(
            () -> profilePage.verifyPostIsPublished(text),
            () -> profilePage.verifyTimePublishedPost(currentTime, timePlus1)
        );

        ProfilePage newProfilePage = profilePage.deletePost();
        refresh();
        newProfilePage.verifyPostIsDeleted(text, currentTime, timePlus1);

    }
}
