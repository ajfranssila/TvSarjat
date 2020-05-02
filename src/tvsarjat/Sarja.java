package tvsarjat;

import java.io.PrintStream;
import java.util.Random;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Sarja-luokka, josta luodaan sarja-oliot. Sisältää sarjan tiedot ja katsomisalustan tunnusnumeron.
 * @author Antti
 * @version 25.2.2018
 */
public class Sarja {

    private int tunnusNro;
    private String nimi = "Uusi sarja";
    private int alusta;
    private int kausi;
    private int seuraavanKausi;
    private int jakso;
    private int seuraavaJakso;
    private String tilanne = "";
    
    private static int seuraavaNro = 1;
    
    
    /**
     * Sarja-luokan muodostaja. Luo uuden sarjan.
     */
    public Sarja() {     
    }
    
    
    /**
     * Palauttaa sarjan tunnusnumeron.
     * @return sarjan tunnusnumero
     */
    public int getTunnusNro() {
        return tunnusNro;
    }
    
    
    /**
     * Asettaa tunnusnumeron ja varmistaa, että
     * seuraava numero on aina suurempi kuin tähän mennessä suurin.
     * @param tunnusNro asetettava tunnusnumero
     */
    private void setTunnusNro(int numero) {
        tunnusNro = numero;
        if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;
    }
    
    
    /**
     * @return Sarjan nimi merkkijonona
     */
    public String getNimi() {
        return nimi;
    }
    
    
    /**
     * Asettaa sarjalle uuden nimen
     * @param nimi Uusi nimi
     */
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    
    /**
     * @return Palauttaa sarjan kauden
     */
    public int getKausi() {
        return kausi;
    }
    
    
    /**
     * Asettaa sarjalle uuden kauden
     * @param kausi Uusi kausi
     */
    public void setKausi(int kausi) {
        this.kausi = kausi;
    }
    
    
    /**
     * @return Palauttaa sarjan seuraavan kauden
     */
    public int getSeuraavanKausi() {
        return seuraavanKausi;
    }
    
    
    /**
     * Asettaa sarjalle uuden seuraavan kauden
     * @param seuraavanKausi Uusi seuraavan kausi
     */
    public void setSeuraavanKausi(int seuraavanKausi) {
        this.seuraavanKausi = seuraavanKausi;
    }
    
    
    /**
     * @return Palauttaa sarjan jakson
     */
    public int getJakso() {
        return jakso;
    }
    
    
    /**
     * Asettaa sarjalle uuden jakso
     * @param jakso Uusi jakso
     */
    public void setJakso(int jakso) {
        this.jakso = jakso;
    }
    
    
    /**
     * @return Palauttaa sarjan seuraavan jakson
     */
    public int getSeuraavaJakso() {
        return seuraavaJakso;
    }
    
    
    /**
     * Asettaa sarjalle uuden seuraavan jakson
     * @param seuraavaJakso Uusi jakso
     */
    public void setSeuraavaJakso(int seuraavaJakso) {
        this.seuraavaJakso = seuraavaJakso;
    }
    
    
    /**
     * @return Alustan nro
     */
    public int getAlustanNro() {
        return alusta;
    }
    
    
    /**
     * Asettaa sarjalle uuden alustanumeron. Tarvitaan, kun sarjan alustaa halutaan vaihtaa.
     * @param uusiNro Uusi alustanumero
     */
    public void setAlustanNro(int uusiNro) {
        alusta = uusiNro;
    }
    
    
    /**
     * @return Sarjan tilanne
     */
    public String getTilanne() {
        return tilanne;
    }
    
    
    /**
     * Asettaa sarjalle uuden tilanteen
     * @param tilanne Uusi tilanne
     */
    public void setTilanne(String tilanne) {
        this.tilanne = tilanne;
    }
    
    
    /**
     * Apumetodi, jolla täytetään testiarvot sarjalle.
     * Käytössä vain testeissä.
     */
    public void taytaSarja() {
        Random rand = new Random();
        nimi = "Breaking Bad";
        alusta = 0;
        kausi = rand.nextInt(5) + 1;
        seuraavanKausi = kausi;
        jakso = rand.nextInt(20) + 1;
        seuraavaJakso = jakso + 1;
        tilanne = "Sarja jäi tilanteeseen, jossa...";
    }
    
    /**
     * Tulostetaan sarjan tiedot
     * @param out Tietovirta, johon tulostetaan.
     */
    public void tulosta(PrintStream out) {
        out.println("------------------");
        out.println("Sarjan nimi: " + nimi);
        out.println("Sarjan katsomisalusta: " + alusta);
        out.println("Sarjan kausi: " + kausi);
        out.println("Sarjan seuraavan jakson kausi: " + seuraavanKausi);
        out.println("Sarjan jakso, johon jäätiin: " + jakso);
        out.println("Seuraava jakso: " + seuraavaJakso);
        out.println("Tilanne, johon jäätiin " + tilanne);
        out.println("------------------");
    }
    
    /**
     * Antaa sarjalle seuraavan rekisterinumeron
     * @return Sarjan uusi tunnusNro
     * <pre name="test">
     *   Sarja bloodline = new Sarja();
     *   bloodline.getTunnusNro() === 0;
     *   bloodline.rekisteroi();
     *   Sarja breakingBad = new Sarja();
     *   breakingBad.rekisteroi();
     *   int n1 = bloodline.getTunnusNro();
     *   int n2 = breakingBad.getTunnusNro();
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    
    /**
     * Palautaa sarjan tiedot merkkijonona.
     * @return Sarjan tiedot eroteltuna tolppamerkein
     * @example
     * <pre name="test">
     * Sarja sarja = new Sarja();
     * sarja.parse(" 2 |  Breaking Bad   | 3 | 5  | 5   | 16 | - | Katsottu loppuun...");
     * sarja.toString() === "2|Breaking Bad|3|5|5|16|0|Katsottu loppuun...";
     * </pre>
     */
    @Override
    public String toString() {
        return "" +
                getTunnusNro() + "|" +
                nimi + "|" +
                alusta + "|" +
                kausi + "|" +
                seuraavanKausi + "|" +
                jakso + "|" +
                seuraavaJakso + "|" +
                tilanne;    
    }
    
    /**
     * Hakee sarjan tiedot tolpilla ("|") erotellusta merkkijonosta
     * @param rivi Rivi, josta sarjan tiedot otetaan
     * @example
     * <pre name="test">
     * Sarja sarja = new Sarja();
     * sarja.parse(" 2 |  Breaking Bad   | 3 | 5  | 5   | 16 | - | Katsottu loppuun…");
     * sarja.getTunnusNro() === 2;
     * sarja.getAlustanNro() === 3;
     * sarja.getNimi() === "Breaking Bad";
     * </pre>
     */
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
        nimi = Mjonot.erota(sb, '|', nimi);
        alusta = Mjonot.erota(sb, '|', alusta);
        kausi = Mjonot.erota(sb, '|', kausi);
        seuraavanKausi = Mjonot.erota(sb, '|', seuraavanKausi);
        jakso = Mjonot.erota(sb, '|', jakso);
        seuraavaJakso = Mjonot.erota(sb, '|', seuraavaJakso);
        tilanne = Mjonot.erota(sb, '|', tilanne);
    }  
    
    /**
     * Testiohjelma sarjalle.
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
    //
    }
}
