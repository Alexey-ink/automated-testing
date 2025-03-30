package ru.vk.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class MessagesPage {
    public static final SelenideElement chatsSearchInputPath = $x("//*[@data-tsid='chat-search-input']");
    public static final SelenideElement chatSendMessagePath = $x("//*[@data-tsid='write_msg_input-input']");
    public static final SelenideElement chatTitlePath = $x("//*[@data-tsid='chat_main']");
    public static final SelenideElement sendMessageButtonxPath = $x("//*[@data-l='t,sendButton']");
    public static final SelenideElement ChatClickxPath = $x("//*[@data-tsid='chat']");
}
