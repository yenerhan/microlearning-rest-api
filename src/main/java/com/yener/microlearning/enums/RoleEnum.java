package com.yener.microlearning.enums;

public enum RoleEnum {
    RESERVE(0L, ""),
    ADMIN(1L, "ROLE_ADMIN"),
    USER(2L, "ROLE_USER");

    private final Long id;
    private final String ad;

    RoleEnum(Long id, String ad) {
        this.id = id;
        this.ad = ad;
    }

    public Long getId() {
        return id;
    }

    public String getAd() {
        return ad;
    }

}
