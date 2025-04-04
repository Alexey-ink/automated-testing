package ru.vk.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

import java.time.Duration;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;

public class MessagesPage {

    public static final SelenideElement conversationListPath = $x("//*[@data-tsid='conversation_list']");

    public static final SelenideElement chatsSearchInputPath = $x("//*[@data-tsid='chat-search-input']");
    public static final SelenideElement chatSendMessagePath = $x("//*[@data-tsid='write_msg_input-input']");
    public static final SelenideElement chatTitlePath = $x("//*[@data-tsid='chat_main']");
    public static final SelenideElement sendMessageButtonxPath = $x("//*[@data-l='t,sendButton']");
    public static final SelenideElement chatClickPath = $x("//*[@data-tsid='chat']");
    public static final SelenideElement waitMsgIconPath = $x("//*[@class='wait-okmsg']");
    public static final SelenideElement timeMsgPath = $x("//*[@class='time-okmsg js-nocopy']");

    MessagesPage() {
        checkPage();
    }

    public void checkPage() {
        conversationListPath.shouldBe(visible.because("Conversation list is not displayed"));
    }

    public MessagesPage setFriendToSearch(String friendName) {
        chatsSearchInputPath.setValue(friendName);
        return this;
    }
    public MessagesPage chatClick() {
        chatClickPath.click();
        return this;
    }

    public MessagesPage setMessage(String message) {
        chatSendMessagePath.setValue(message);
        //sleep(8000); // Чтобы выключить интернет перед отправкой соо и проверить, что тест падает
        return this;
    }
    public MessagesPage sendMessage() {
        sendMessageButtonxPath.click();
        return this;
    }

    public SelenideElement getLastMessage(String message) {
        ElementsCollection messages = $$x("//span[text()='" + message + "']");
        SelenideElement lastMessage = messages.last().shouldBe(visible, Duration.ofSeconds(3));
        return lastMessage;
    }

    public void waitMessageIcon(String message) throws ElementNotFound {
        SelenideElement lastMessage = this.getLastMessage(message);
        SelenideElement messageWaitIcon = lastMessage.parent().parent().$x(".//*[@class='wait-okmsg']");
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
        SelenideElement messageWaitIcon = lastMessage.parent().parent().$x(".//*[@class='wait-okmsg']");
        messageWaitIcon.shouldNotBe(visible);
    }
}
