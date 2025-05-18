package rental;
import java.util.ArrayList;
import java.util.List;

public class Müşteri {
    private String kullanıcıAdı;
    private String ad;
    private String soyad;
    private String şifre;
    private String telefonNumarası;
    private String email;
    private String tcKimlikNumarası;
    private String ehliyetSeriNumarası;
    private String doğumTarihi;

    public Müşteri(String kullanıcıAdı, String ad, String soyad, String şifre, String telefonNumarası,
                   String email, String tcKimlikNumarası, String ehliyetSeriNumarası,String doğumTarihi) {
        this.kullanıcıAdı = kullanıcıAdı;
        this.ad = ad;
        this.soyad = soyad;
        this.şifre = şifre;
        this.telefonNumarası = telefonNumarası;
        this.email = email;
        this.tcKimlikNumarası = tcKimlikNumarası;
        this.ehliyetSeriNumarası = ehliyetSeriNumarası;
        this.doğumTarihi = doğumTarihi;       
    }

    public String getKullanıcıAdı() {
        return kullanıcıAdı;
    }
    
    public String getDoğumTarihi() {
    	return doğumTarihi;
    }
    
    public void setKullanıcıAdı(String kullanıcıAdı) {
    	this.kullanıcıAdı = kullanıcıAdı;
    }
    
    public void setŞifre(String şifre) {
    	this.şifre = şifre;
    }
    
    public String getŞifre() {
        return şifre;
    }

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public String getTelefonNumarası() {
        return telefonNumarası;
    }

    public void setTelefonNumarası(String telefonNumarası) {
        this.telefonNumarası = telefonNumarası;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTcKimlikNumarası() {
        return tcKimlikNumarası;
    }

    public String getEhliyetSeriNumarası() {
        return ehliyetSeriNumarası;
    }
}