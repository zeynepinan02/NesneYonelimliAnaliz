package AnaIslemPlatformu;

import CevreBirimler.Eyleyici;
import CevreBirimler.IEyleyici;
import CevreBirimler.ISicaklikAlgilayici;
import CevreBirimler.SicaklikAlgilayici;
import Database.DatabaseFactory;
import Database.IDatabaseManager;
import Database.PostgresDatabaseManager;
import Observer.ISubject;
import Observer.Publisher;
import Observer.Subscribe1;
import Observer.Subscribe2;

import java.util.Scanner;

public class AnaIslemCihaz implements IAnaIslemCihaz{

    private ISicaklikAlgilayici sicaklikAlgilayici;
    private IEyleyici eyleyici;
    private ISubject publisher;

    public AnaIslemCihaz(){
        eyleyici=new Eyleyici();
        publisher=new Publisher();
        sicaklikAlgilayici=new SicaklikAlgilayici(publisher);
    }

    @Override
    public boolean GirisYap() {
        Scanner girdi = new Scanner(System.in);
        System.out.print("Kullanıcı adınız: ");
        String kullaniciAd = girdi.next();

        boolean girisDurumu=false;
        boolean kullaniciVar=false;
        IDatabaseManager veritabani= DatabaseFactory.getDatabase("Postgres");
        //IDatabaseManager veritabani=new PostgresDatabaseManager();
        kullaniciVar=veritabani.kullaniciAdiDogrula(kullaniciAd);

        if(kullaniciVar) {
            System.out.print("Sifreniz: ");
            String sifre = girdi.next();
            girisDurumu=veritabani.kullaniciDogrula(kullaniciAd, sifre);
        }

        else{
            System.out.println("Kullanici Bulunamadi");
            return false;
        }

        if(!girisDurumu){
            System.out.println("Kullanici adi ve ya sifre yanlis");
            return false;
        }
        else{
            System.out.println("Giris Basarili");
            return true;
        }
    }

    @Override
    public void baslat() {
        if(GirisYap()){

            int sicaklikDgr;
            int secim;

            do {
                System.out.println("Bir işlem seçiniz     ");
                System.out.println("1-Soğutucu Çalıştır   ");
                System.out.println("2-Soğutucu Kapat      ");
                System.out.println("3-Sıcaklık Görüntüle  ");
                System.out.println("4-Çıkış               ");
                Scanner input = new Scanner(System.in);
                secim=input.nextInt();

                switch (secim){
                    case 1:
                        eyleyici.sogutucuAc();
                        break;
                    case 2:
                        eyleyici.sogutucuKapat();
                        break;
                    case 3:
                        Subscribe1 subscribe1=new Subscribe1();
                        Subscribe2 subscribe2=new Subscribe2();
                        sicaklikAlgilayici.aboneEkle(subscribe1);
                        sicaklikAlgilayici.aboneEkle(subscribe2);
                        sicaklikDgr=sicaklikAlgilayici.sicaklikOlcme();
                        System.out.println("Sicaklik Degeri: "+sicaklikDgr);
                        break;
                    case 4:
                        System.out.println("Çıkıs yapiliyor.");
                        break;
                    default:
                        System.out.println("Lutfen gecerli bir sayi giriniz");
                        break;
                }
            }while(secim!=4);
        }
        else{
            System.out.println("Arayuze giris yapilamadi");
        }
    }
}
