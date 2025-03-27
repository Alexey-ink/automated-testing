package ru.vk;

public class MessagesPage {

    static final String chatsSearchInputxPath = 
        "//*[@id=\"msg_layer\"]/msg-app/div/msg-page/div[1]/msg-chats-panel/msg-toolbar/div[1]/slot/msg-search-input/input";
    
    static final String chatSendMessage = 
        "//*[@id=\"msg_layer\"]/msg-app/div/msg-page/div[2]/msg-chat/div/section/footer/msg-posting-form/div/form/msg-message-editor/div/div/msg-input/div[2]";

    static final String chatTitle = 
        "//*[@id=\"msg-chat-title\"]/msg-name/div/msg-parsed-text/span";

    static final String sendMessageButtonxPath = "//*[@id=\"msg_layer\"]/msg-app/div/msg-page/div[2]/msg-chat/div/section/footer/msg-posting-form/div/form/msg-message-editor/div/aside[2]/slot/div/button[3]";
                                            
    static final String ChatClickxPath = "//*[@id=\"msg_layer\"]/msg-app/div/msg-page/div[1]/msg-chats-panel/div/div/msg-search-results/msg-search-results-item/msg-chat-item-container";
        
}

