package Observer;

import java.util.ArrayList;
import java.util.List;

public class Publisher implements ISubject{
    private List<IObserver> subscribers=new ArrayList<>();

    @Override
    public void attach(IObserver observer) {
        subscribers.add(observer);
    }

    @Override
    public void detach(IObserver observer) {
        subscribers.remove(observer);
    }

    @Override
    public void notify(String mesaj) {
        for(IObserver obs:subscribers){
            obs.update(mesaj);
        }
    }
}
