package Observer;

public class Subscribe2 implements IObserver{
    @Override
    public void update(String mesaj) {
        System.out.println("Abone2'e gelen mesaj = "+mesaj);
    }
}
