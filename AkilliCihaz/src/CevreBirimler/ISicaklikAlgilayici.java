package CevreBirimler;

import Observer.IObserver;
import Observer.ISubject;

public interface ISicaklikAlgilayici {

    public void aboneEkle(IObserver abone);
    public int sicaklikOlcme();
}
