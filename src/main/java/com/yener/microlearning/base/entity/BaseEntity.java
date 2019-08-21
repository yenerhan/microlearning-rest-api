package com.yener.microlearning.base.entity;//package com.mapsapi.chancepoint.base.entity;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@MappedSuperclass
//public class BaseEntity {
//    @Id
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "DEFAULT_ID_GENERATOR"
//    )
//    protected Long id;
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(
//            name = "OLUSTURULMA_TARIHI"
//    )
//    protected Date olusturulmaTarihi;
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(
//            name = "SON_GUNCELLENME_TARIHI"
//    )
//    protected Date sonGuncellenmeTarihi;
//    @Column(
//            name = "SILINDI",
//            insertable = false
//    )
//    protected Boolean silindi;
//    @Column(
//            name = "ISLEM_YAPAN_KULLANICI"
//    )
//    protected String islemYapanKullanici;
//
//    public BaseEntity() {
//    }
//
//    public Date getOlusturulmaTarihi() {
//        return this.olusturulmaTarihi;
//    }
//
//    public void setOlusturulmaTarihi(Date olusturulmaTarihi) {
//        this.olusturulmaTarihi = olusturulmaTarihi;
//    }
//
//    public Date getSonGuncellenmeTarihi() {
//        return this.sonGuncellenmeTarihi;
//    }
//
//    public void setSonGuncellenmeTarihi(Date sonGuncellenmeTarihi) {
//        this.sonGuncellenmeTarihi = sonGuncellenmeTarihi;
//    }
//
//    public Boolean getSilindi() {
//        return this.silindi;
//    }
//
//    public void setSilindi(Boolean silindi) {
//        this.silindi = silindi;
//    }
//
//    public String getIslemYapanKullanici() {
//        return this.islemYapanKullanici;
//    }
//
//    public void setIslemYapanKullanici(String islemYapanKullanici) {
//        this.islemYapanKullanici = islemYapanKullanici;
//    }
//
//    public Long getId() {
//        return this.id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//}
