package ohtu.matkakortti;

public class Kassapaate {

    private int myytyjaLounaita;
    public static final int HINTA = 5;

    public Kassapaate() {
        this.myytyjaLounaita = 0;
    }

    public void lataa(Matkakortti kortti, int summa) {
        if(0 < summa){
            kortti.lataa(summa);
        }
        
        
    }

    public void ostaLounas(Matkakortti kortti) {

        if (kortti.getSaldo() >= HINTA) {
            kortti.osta(HINTA);
            myytyjaLounaita++;
        }

    }

    public int getMyytyjaLounaita() {
        return myytyjaLounaita;
    }
}