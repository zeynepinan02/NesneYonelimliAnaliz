package Database;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PostgresDatabaseManager implements IDatabaseManager{
    Connection conn=null;

    @Override
    public boolean baglan() {
        try {

            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/AkilliCihaz", "postgres", "HBMz6102");
            if (conn != null) {
                //System.out.println("Veritabanına bağlanıldı.");
                return true;
            }
            else {
               // System.out.println("Sistem veritabanına zaten bağlı durumda!");
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean kullaniciAdiDogrula(String kullaniciAdi) {
        try {
            boolean kullaniciDurumu=false;
            baglan();

            String sql= "SELECT *  FROM \"Kullanicilar\" WHERE \"kullaniciAdi\"='"+ kullaniciAdi + "';";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            String ad;

            while(rs.next()) {
                ad = rs.getString("kullaniciAdi");

                if(ad.equals(kullaniciAdi)){
                    kullaniciDurumu=true;
                }
            }

            rs.close();
            stmt.close();

            baglantiSonlandir();

            return kullaniciDurumu;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean kullaniciDogrula(String kullaniciAdi, String sifre) {
        try {
            boolean girisDurumu=false;

            baglan();

            String sql= "SELECT *  FROM \"Kullanicilar\" WHERE \"kullaniciAdi\"='"+ kullaniciAdi + "' AND \"sifre\"='"+ sifre + "';";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            String ad;
            String password;

            while(rs.next()) {
                ad = rs.getString("kullaniciAdi");
                password = rs.getString("sifre");

                if(ad.equals(kullaniciAdi) && password.equals(sifre)) {
                        girisDurumu=true;
                }
            }

            rs.close();
            stmt.close();

            baglantiSonlandir();

            return girisDurumu;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void baglantiSonlandir() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getTip() {
        return "Postgres database seçildi";
    }
}
