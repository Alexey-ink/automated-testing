package ru.vk.tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.codeborne.selenide.ex.ElementNotFound;
import ru.vk.pages.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(MessageTest.class);
    private static final String FRIEND_NAME = "про100 Игорь";

    //@Disabled
    @Tag("message")
    @DisplayName("Проверка отправки сообщения другу")
    @ParameterizedTest
    @ValueSource(strings = {"hello)", "my friend"})
    public void testSendingMessages(String message) {

        log.info("═══════════════════════════════════════════════════");
        log.info("Начало теста отправки сообщения: '{}'", message);
        log.info("Получатель: {}", FRIEND_NAME);
        final String datePattern = "HH:mm";

        MessagesPage messagesPage = new FeedPage()
            .messagesClick()
            .setFriendToSearch(FRIEND_NAME)
            .chatClick();

        log.info("Верификация элементов чата");
        assertAll(
            () -> messagesPage.verifyChatTitlePath(),
            () -> messagesPage.verifyChatSendMessagePath()
        );

        log.info("Отправка сообщения");
        MessagesPage newMessagesPage = messagesPage.setMessage(message).sendMessage();
        log.info("Сообщение '{}' отправлено", message);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        String currentTime = now.format(formatter);
        String timePlus1 = now.plusMinutes(1).format(formatter);

        assertAll(
            () -> {
                try {
                    log.info("Ожидание иконки ожидания отправки");
                     // Ждем появления иконки ожидания отправки соо 
                    newMessagesPage.waitMessageIcon(message);
                } catch (ElementNotFound e) {
                    log.info("Иконка не появилась -- сообщение отправилось");
                }
                log.info("Ожидание исчезновения иконки");
                newMessagesPage.waitIconShouldNotVisible(message);
            },
    
            () -> newMessagesPage.verifyMessageTime(message, currentTime, timePlus1)
        );
        log.info("✅ Сообщение '{}' успешно доставлено и проверено", message);
        log.info("═══════════════════════════════════════════════════\n");
    }

}
