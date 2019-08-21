package com.yener.microlearning.base.util;


import com.yener.microlearning.base.enums.MessageTypeENUM;

public class Message {
    private String messageText;
    private MessageTypeENUM messageType;

    public Message(MessageTypeENUM messageType, String messageText) {
        this.messageText = messageText;
        this.messageType = messageType;
    }

    public String getMessageText() {
        return this.messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public MessageTypeENUM getMessageType() {
        return this.messageType;
    }

    public void setMessageType(MessageTypeENUM messageType) {
        this.messageType = messageType;
    }
}
