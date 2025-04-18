package ru.vk.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;

import java.time.Duration;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MessagesPage {

    private final By conversationListPath = By.xpath("//*[@data-tsid='conversation_list']");

    private final By chatsSearchInputPath = By.xpath("//*[@data-tsid='chat-search-input']");
    private final By chatSendMessagePath = By.xpath("//*[@data-tsid='write_msg_input-input']");
    private final By chatTitlePath = By.xpath("//*[@data-tsid='chat_main']");
    private final By sendMessageButtonxPath = By.xpath("//*[@data-l='t,sendButton']");
    private final By chatClickPath = By.xpath("//*[@data-tsid='chat']");

    private final By waitMsgIconPath = By.xpath(".//*[@class='wait-okmsg']");

    MessagesPage() {
        checkPage();
    }

    public void checkPage() {
        $(conversationListPath).shouldBe(visible.because("Conversation list is not displayed"));
    }

    public MessagesPage setFriendToSearch(String friendName) {
        $(chatsSearchInputPath).setValue(friendName);
        return this;
    }
    public MessagesPage chatClick() {
        $(chatClickPath).click();
        return this;
    }

    public MessagesPage setMessage(String message) {
        $(chatSendMessagePath).setValue(message);
        //sleep(8000); // Чтобы выключить интернет перед отправкой соо и проверить, что тест падает
        return this;
    }
    public MessagesPage sendMessage() {
        $(sendMessageButtonxPath).click();
        return this;
    }

    public SelenideElement getLastMessage(String message) {
        ElementsCollection messages = $$x("//span[text()='" + message + "']");
        SelenideElement lastMessage = messages.last().shouldBe(visible, Duration.ofSeconds(3));
        return lastMessage;
    }

    public void waitMessageIcon(String message) throws ElementNotFound {
        SelenideElement lastMessage = this.getLastMessage(message);
        SelenideElement messageWaitIcon = lastMessage.parent().parent().$(waitMsgIconPath);
        messageWaitIcon.should(appear, Duration.ofSeconds(2));
        messageWaitIcon.should(disappear, Duration.ofSeconds(5));
    }

    public void verifyMessageTime(String message, String currentTime, String timePlus1) {
        SelenideElement lastMessage = this.getLastMessage(message);
        lastMessage
                .parent()
                .parent()
                .$x(
                ".//*[contains(@aria-label, '" + currentTime + "') or " +
                "contains(@aria-label, '" + timePlus1 + "')]"
                ).shouldBe(visible, Duration.ofMillis(3001));
    }

    public void waitIconShouldNotVisible(String message) {
        SelenideElement lastMessage = this.getLastMessage(message);
        SelenideElement messageWaitIcon = lastMessage.parent().parent().$(waitMsgIconPath);
        messageWaitIcon.shouldNotBe(visible);
    }

    public void verifyChatTitlePath() {
        $(chatTitlePath).shouldBe(visible.because("Чат не открылся"));
    }
    public void verifyChatSendMessagePath() {
        $(chatSendMessagePath).shouldBe(visible.because("Поле ввода сообщения не отображается"));
    }
}
