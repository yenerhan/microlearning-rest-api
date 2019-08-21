package com.yener.microlearning.base.restApi.apiRequest;

/**
 * Created by erhan.yener on 27.06.2016.
 */
public class BaseApiRequest {
    private String kullaniciAdi = null;

    public BaseApiRequest() {
    }

    public String getKullaniciAdi() {
        return this.kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

}