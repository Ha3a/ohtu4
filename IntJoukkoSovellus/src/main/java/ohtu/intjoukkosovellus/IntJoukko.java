package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukuJono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        alusutaLukuJono(KAPASITEETTI);
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        alusutaLukuJono(kapasiteetti);
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;

    }

    public void alusutaLukuJono(int kapasiteetti) {
        lukuJono = new int[kapasiteetti];
        for (int i = 0; i < lukuJono.length; i++) {
            lukuJono[i] = 0;
        }
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        tarkistaParametrit(kapasiteetti, kasvatuskoko);
        alusutaLukuJono(kapasiteetti);
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public void tarkistaParametrit(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
    }

    public boolean lisaa(int luku) {
        if (alkioidenLkm == 0) {
            lisaaTaulukonEkaksi(luku);
            return true;
        } else if (!kuuluu(luku)) {
            lisaaTaulukkoon(luku);
            return true;
        }
        return false;
    }

    public void lisaaTaulukonEkaksi(int luku) {
        lukuJono[0] = luku;
        alkioidenLkm++;
    }

    public void lisaaTaulukkoon(int luku) {
        lukuJono[alkioidenLkm] = luku;
        alkioidenLkm++;
        if (alkioidenLkm == lukuJono.length) {
            kopioiTaulukkoKasvattamalla(lukuJono);

        }
    }

    public boolean kuuluu(int luku) {
        int on = 0;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukuJono[i]) {
                on++;
            }
        }
        return on > 0;
    }

    public boolean poista(int luku) {
        int kohta = etsiKohta(luku);
        lukuJono[kohta] = 0;
        if (kohta != -1) {
            korjaaTaulukonPera(kohta);
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    public void korjaaTaulukonPera(int kohta) {
        int apu;
        for (int j = kohta; j < alkioidenLkm - 1; j++) {
            apu = lukuJono[j];
            lukuJono[j] = lukuJono[j + 1];
            lukuJono[j + 1] = apu;
        }
    }

    public int etsiKohta(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukuJono[i]) {
                return i;
            }
        }
        return -1;
    }

    private void kopioiTaulukkoKasvattamalla(int[] vanha) {
        lukuJono = new int[alkioidenLkm + kasvatuskoko];

        System.arraycopy(vanha, 0, lukuJono, 0, vanha.length);

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + lukuJono[0] + "}";
        } else {
            return tulostaPitkaTaulukko();
        }
    }

    public String tulostaPitkaTaulukko() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos += lukuJono[i];
            tuotos += ", ";
        }
        tuotos += lukuJono[alkioidenLkm - 1];
        tuotos += "}";
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukuJono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        lisaaTaulustaJoukkoon(x, aTaulu);
        lisaaTaulustaJoukkoon(x, bTaulu);
        return x;
    }

    public static void lisaaTaulustaJoukkoon(IntJoukko x, int[] taulu) {
        for (int i = 0; i < taulu.length; i++) {
            x.lisaa(taulu[i]);
        }
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        luoLeikkausJoukko(y, aTaulu, bTaulu);
        return y;

    }

    public static void luoLeikkausJoukko(IntJoukko y, int[] aTaulu, int[] bTaulu) {
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        lisaaTaulustaJoukkoon(z, aTaulu);
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(i);
        }
        return z;
    }

}
