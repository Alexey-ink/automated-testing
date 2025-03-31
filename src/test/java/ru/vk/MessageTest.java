package ru.vk;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;

import static com.codeborne.selenide.Condition.*;

import io.github.cdimascio.dotenv.Dotenv;
import ru.vk.pages.*;

public class MessageTest extends BaseTest {

    @Disabled
    @DisplayName("Проверка отправки сообщения другу")
    @ParameterizedTest 
    @ValueSource(strings = {"hello)"})
    public void testSendingMessages(String message) throws InterruptedException {

        Dotenv dotenv = Dotenv.load();

        FeedPage.MessagesPath.click();

        MessagesPage.chatsSearchInputPath.setValue(dotenv.get("FRIENDNAME"));
        MessagesPage.ChatClickxPath.click();

        assertAll(
        () -> assertTrue(MessagesPage.chatTitlePath.isDisplayed(), 
            "Чат не открылся"),
        () -> assertTrue(MessagesPage.chatSendMessagePath.isDisplayed(),
            "Поле ввода сообщения не отображается")
        );

        MessagesPage.chatSendMessagePath.setValue(message);
        Thread.sleep(8000); // Чтобы выключить интернет перед отправкой соо и проверить, что тест падает
        MessagesPage.sendMessageButtonxPath.click();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String currentTime = now.format(formatter);
        String timePlus1 = now.plusMinutes(1).format(formatter);

        ElementsCollection messages = $$x("//span[text()='" + message + "']");
        SelenideElement lastMessage = messages.last().shouldBe(visible, Duration.ofSeconds(3));

        SelenideElement messageWaitIcon = lastMessage.parent().parent().$x(".//*[@class='wait-okmsg']");

        assertAll(
            () -> {
                try {
                    // Ждем появления иконки ожидания отправки соо 
                    messageWaitIcon.should(appear, Duration.ofSeconds(2));
                    messageWaitIcon.should(disappear, Duration.ofSeconds(5));
                } catch (ElementNotFound e) { }
                messageWaitIcon.shouldNotBe(visible);
            },
    
            () -> lastMessage
                .parent()
                .parent()
                .$x(
                ".//*[contains(@aria-label, '" + currentTime + "') or " +
                "contains(@aria-label, '" + timePlus1 + "')]"
                ).shouldBe(visible, Duration.ofMillis(3001))
        );
    }

}
