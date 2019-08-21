package com.yener.microlearning.dto.card;


import java.util.List;

public class CardDTO {
    private Long cardId;
    private String cardName;
    private byte[] cardPhoto;
    private String comment;
    private List<ContentDTO> contentDTOList;
    private Boolean active;


    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public byte[] getCardPhoto() {
        return cardPhoto;
    }

    public void setCardPhoto(byte[] cardPhoto) {
        this.cardPhoto = cardPhoto;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<ContentDTO> getContentDTOList() {
        return contentDTOList;
    }

    public void setContentDTOList(List<ContentDTO> contentDTOList) {
        this.contentDTOList = contentDTOList;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
