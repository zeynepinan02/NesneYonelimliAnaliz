package CevreBirimler;
import java.util.Random;

import Observer.IObserver;
import Observer.ISubject;

public class SicaklikAlgilayici implements ISicaklikAlgilayici{
    private ISubject publisher;

    public SicaklikAlgilayici(ISubject publisher){
        this.publisher=publisher;
    }

    @Override
    public void aboneEkle(IObserver abone) {
        publisher.attach(abone);
    }

    @Override
    public int sicaklikOlcme() {
        Random rnd = new Random();
        int sicaklik = rnd.nextInt(100);

        if(sicaklik>50){
            publisher.notify("Sıcaklık 50 dereceden fazla, soğutucuyu açmanız tavsiye edilir.");
        }
        return sicaklik;
    }
}


