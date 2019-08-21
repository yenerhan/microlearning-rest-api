package com.yener.microlearning.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "membership")
public class MemberShip implements Serializable {


    private static final long serialVersionUID = -6517820206978129972L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "SEQ_MEMBER_SHIP", allocationSize = 1)
    private Long memberShipId;

    @Column(nullable = false, name = "fk_company_id")
    private Long companyId;

    @Column(nullable = false, length = 8)
    private String code;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startOfDate;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endOfDate;

    @Column(nullable = false)
    private Boolean active;

    public Long getMemberShipId() {
        return memberShipId;
    }

    public void setMemberShipId(Long memberShipId) {
        this.memberShipId = memberShipId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getStartOfDate() {
        return startOfDate;
    }

    public void setStartOfDate(Date startOfDate) {
        this.startOfDate = startOfDate;
    }

    public Date getEndOfDate() {
        return endOfDate;
    }

    public void setEndOfDate(Date endOfDate) {
        this.endOfDate = endOfDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
