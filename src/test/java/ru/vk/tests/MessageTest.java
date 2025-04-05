package ru.vk.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.codeborne.selenide.ex.ElementNotFound;
import ru.vk.pages.*;

public class MessageTest extends BaseTest {

    //@Disabled
    @DisplayName("Проверка отправки сообщения другу")
    @ParameterizedTest 
    @ValueSource(strings = {"hello)", "my friend"})
    public void testSendingMessages(String message) {

        String friendName = "про100 Игорь";
        final String datePattern = "HH:mm";

        MessagesPage messagesPage = new FeedPage()
            .messagesClick()
            .setFriendToSearch(friendName)
            .chatClick();

        assertAll(
        () -> assertTrue(messagesPage.chatTitlePath.isDisplayed(), "Чат не открылся"),
        () -> assertTrue(MessagesPage.chatSendMessagePath.isDisplayed(),"Поле ввода сообщения не отображается")
        );

        MessagesPage newMessagesPage = messagesPage.setMessage(message).sendMessage();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        String currentTime = now.format(formatter);
        String timePlus1 = now.plusMinutes(1).format(formatter);

        assertAll(
            () -> {
                try {
                     // Ждем появления иконки ожидания отправки соо 
                    newMessagesPage.waitMessageIcon(message);
                } catch (ElementNotFound e) { }
                // Ждем исчезновения иконки ожидания отправки сообщения
                newMessagesPage.waitIconShouldNotVisible(message);
            },
    
            () -> newMessagesPage.verifyMessageTime(message, currentTime, timePlus1)
        );
    }

}
