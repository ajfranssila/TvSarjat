/**
 * 
 */
package tvsarjat;

import java.io.PrintStream;
import java.util.Random;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Alusta luokka, josta luodaan alusta-oliot sarjojen katsomisalustaa varten. 
 * @author Antti
 * @version 27.2.2018
 *
 */
public class Alusta {
    
    private int tunnusNro;
    private String alustanNimi = "";
    
    private static int seuraavaNro = 1;
    
    /**
     * Alusta luokan muodostaja
     */
    public Alusta() {
    }
    
    
    /**
     * Palauttaa alustan nimen
     * @return Alustan nimi
     */
    public String getNimi() {
        return alustanNimi;
    }
    
    
    /**
     * Asettaa alustalle nimen
     * @param nimi Mikä nimi asetetaan
     */
    public void setNimi(String nimi) {
        alustanNimi = nimi;
    }
    
    
    /**
     * Palauttaa alustan tunnusnumeron.
     * @return Alustan tunnusnumero
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
     * Tulostetaan alustan tiedot
     * @param out Tietovirta, johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(alustanNimi);
    }
    
    
    /**
     * Antaa alustalle seuraavan rekisterinumeron
     * @return Alustan uusi tunnusNro
     * <pre name="test">
     *   Alusta netflix = new Alusta();
     *   netflix.getTunnusNro() === 0;
     *   netflix.rekisteroi();
     *   Alusta ruutu = new Alusta();
     *   ruutu.rekisteroi();
     *   int n1 = netflix.getTunnusNro();
     *   int n2 = ruutu.getTunnusNro();
     *   n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    
    
   /**
    * Apumetodi, jolla tehdään satunnainen nimi alustalle.
    * Ei ole enää käytössä.
    */
    public void taytaAlusta() {
        String kirjaimet = "abcdefghijklmnopqrstuvwxyzåäö";
        StringBuilder randStr = new StringBuilder();
        Random rand = new Random();
        for(int i=0; i<10; i++){            
            int number = rand.nextInt(kirjaimet.length());;
            char ch = kirjaimet.charAt(number);
            randStr.append(ch);
        }
        alustanNimi = randStr.toString();
    }
    
    
    /**
     * Palautaa sarjan tiedot merkkijonona.
     * @return Sarjan tiedot eroteltuna tolppamerkein
     * @example
     * <pre name="test">
     * Alusta alusta = new Alusta();
     * alusta.parse(" 1 | Netflix");
     * alusta.toString() === "1|Netflix";
     * </pre>
     */
    @Override
    public String toString() {
        return "" + getTunnusNro() + "|" + alustanNimi;
    }
    
    
    /**
     * Hakee alustan tiedot tolpilla ("|") erotellusta merkkijonosta
     * @param rivi Rivi, josta alustan tiedot otetaan
     * @example
     * <pre name="test">
     * Alusta alusta = new Alusta();
     * alusta.parse(" 1 |  Netflix");
     * alusta.getTunnusNro() === 1;
     * alusta.getNimi() === "Netflix";
     * </pre>
     */
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
        alustanNimi = Mjonot.erota(sb, '|', alustanNimi);
    }
    

    /**
     * Testiohjelma alustalle. Ei käytössä.
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        //
    }

}
