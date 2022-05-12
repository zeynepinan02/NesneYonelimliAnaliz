package Database;

import java.sql.ResultSet;

public interface IDatabaseManager {
    public boolean baglan();
    public boolean kullaniciDogrula(String kullaniciAdi, String sifre);
    public boolean kullaniciAdiDogrula(String kullaniciAdi);
    public void baglantiSonlandir();
    public String getTip();

}
