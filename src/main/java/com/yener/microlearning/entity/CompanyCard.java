package com.yener.microlearning.entity;

import javax.persistence.*;

@Entity(name = "company_card")
public class CompanyCard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "SEQ_COMPANY_CARD", allocationSize = 1)
    private Long companyCardId;

    private Long companyId;

    private Long departmentId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_cardId", referencedColumnName = "cardId")
    private Card card;

    @Column(nullable = false)
    private Boolean active;

    public Long getCompanyCardId() {
        return companyCardId;
    }

    public void setCompanyCardId(Long companyCardId) {
        this.companyCardId = companyCardId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
