package ru.vk;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.codeborne.selenide.SelenideElement;

import ru.vk.pages.*;

public class PostingTest extends BaseTest {

    @DisplayName("Проверка выкладывания записи на стену и ее удаления")
    @ParameterizedTest
    @ValueSource(strings = {"тэк"})
    public void testPostingRecord(String text) throws InterruptedException {

        FeedPage.PostPath.click();
        OkPage.PostRecordPath.click();

        OkPage.EnterTextPath.setValue(text);
        OkPage.ShareRecordPath.click();

        FeedPage.ProfilePagePath.click();
        Thread.sleep(1000);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String currentTime = now.format(formatter);
        String timePlus1 = now.plusMinutes(1).format(formatter);

        refresh();

        SelenideElement lastPost = ProfilePage.PostsPath.first();

        assertAll(
            () -> {
                lastPost.$x(".//*[text()='" + text + "']").shouldBe(visible);
            },

            () -> {
                lastPost.$x(".//time[text()='" + currentTime + 
                            "' or text()='" + timePlus1 + "']").shouldBe(visible);
            }
        );

        // Теперь удалим созданную запись
        actions().moveToElement(lastPost.$x(".//div[@class='feed-action']")).perform();
        
        if(ProfilePage.pageLanguage.equals("ru")) {
            lastPost.$x(".//*[text()='Удалить заметку']").click();
        }
        else if(ProfilePage.pageLanguage.equals("en")) {
            lastPost.$x(".//*[text()='Delete post']").click();
        }
        else {
            throw new IllegalStateException("Неизвестный язык страницы: " + ProfilePage.pageLanguage);
        }

        lastPost.$x(".//*[contains(@class, 'form-actions_yes')]").click();


        // Проверим, что она удалилась
        refresh();
        SelenideElement lastP = ProfilePage.PostsPath.first();

        lastP.$x(".//*[contains(text(), '" + text + "') and .//time[text()='" + currentTime + "' or text()='" + timePlus1 + "']]")
        .shouldNot(exist);

    }
}
