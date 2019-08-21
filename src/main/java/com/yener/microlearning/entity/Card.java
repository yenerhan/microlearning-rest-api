package com.yener.microlearning.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "SEQ_CARD", allocationSize = 1)
    private Long cardId;

    @Column(nullable = false)
    private String cardName;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(nullable = false)
    private byte[] cardPhoto;

    @Column(nullable = false)
    private String comment;

    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "card", fetch = FetchType.LAZY)
    private Set<Content> contentList;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
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

    public Set<Content> getContentList() {
        return contentList;
    }

    public void setContentList(Set<Content> contentList) {
        this.contentList = contentList;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
