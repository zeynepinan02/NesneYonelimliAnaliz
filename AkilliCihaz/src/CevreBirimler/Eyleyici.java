package CevreBirimler;

public class Eyleyici implements IEyleyici{
    private boolean sogutucuDurumu = false;

    @Override
    public void sogutucuAc() {
        if (!sogutucuDurumu) {
            sogutucuDurumu = true;
            System.out.println("Soğutucu açıldı!");
        }
        else {
            System.out.println("Soğutucu zaten aktif durumda!");
        }
    }

    @Override
    public void sogutucuKapat() {
        if (sogutucuDurumu) {
            sogutucuDurumu = false;
            System.out.println("Soğutucu kapatıldı!");
        }
        else {
            System.out.println("Soğutucu zaten kapalı durumda!");
        }
    }

}
