package ru.vk.tests;

import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.vk.pages.*;

public class PostingTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(PostingTest.class);

    static Stream<Arguments> postDataProvider() {
        return Stream.of(
                Arguments.of("Testing the posting of a record", null),
                Arguments.of("Пост с музычкой", "good 4 u")
        );
    }

    //@Disabled
    @Tag("post")
    @DisplayName("Проверка выкладывания записи на стену и ее удаления")
    @ParameterizedTest
    @MethodSource("postDataProvider")
    public void testPostingRecord(String postText, String song) {

        log.info("═══════════════════════════════════════════════════");
        log.info("Начало теста публикации поста: {}", postText);

        FeedPage feedPage = new FeedPage().postClick()
                .recordClick()
                .enterTextClick(postText)
                .attachSong(song)
                .shareRecordClick();

        log.info("Пост опубликован");
        ProfilePage profilePage = feedPage.profileClick();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String currentTime = now.format(formatter);
        String timePlus1 = now.plusMinutes(1).format(formatter);

        refresh();
        log.info("Проверка наличия поста");
        assertAll(
            () -> profilePage.verifyPostIsPublished(postText),
            () -> profilePage.verifyTimePublishedPost(currentTime, timePlus1)
        );

        ProfilePage newProfilePage = profilePage.deletePost();
        refresh();
        newProfilePage.verifyPostIsDeleted(postText, currentTime, timePlus1);
        log.info("✅ Пост успешно удален");
        log.info("═══════════════════════════════════════════════════\n");

    }
}
