package com.yener.microlearning.base.enums;


public enum MessageENUM {
    NO_RESULT_MESSAGE("Arad���n�z Kriterlere Uygun Kay�t Bulunamad�") ;

    private String messageText;

    private MessageENUM(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageText() {
        return this.messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
