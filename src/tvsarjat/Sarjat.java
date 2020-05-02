/**
 * 
 */
package tvsarjat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import fi.jyu.mit.ohj2.WildChars;


/**
 * Sarjat luokka sarjojen hallintaa varten. Yll‰pit‰‰ sarjoista teht‰v‰‰ tietorakennetta.
 * @author Antti
 * @version 27.2.2018
 */
public class Sarjat {
    private int lkm = 0;
    private Sarja[] alkiot = new Sarja[10];

    
    /**
     * Oletusmuodostaja
     */
    public Sarjat() {
    }


    /**
     * Lis‰‰ uuden sarjan tietorakenteeseen. Pit‰‰ huolen, ett‰ alkiot taulukko kasvaa tarvittaessa.
     * @param sarja Lis‰tt‰v‰n sarjan viite
     * @example
     * <pre name="test">
     * Sarjat sarjat = new Sarjat();
     * Sarja bloodline = new Sarja(), breakingBad = new Sarja();
     * bloodline.rekisteroi();
     * bloodline.taytaSarja();
     * breakingBad.rekisteroi();
     * breakingBad.taytaSarja();
     * sarjat.lisaa(bloodline);
     * sarjat.lisaa(breakingBad);
     * sarjat.getLkm() === 2;
     * sarjat.lisaa(bloodline);
     * sarjat.lisaa(bloodline);
     * sarjat.lisaa(bloodline);
     * sarjat.lisaa(bloodline);
     * sarjat.lisaa(bloodline);
     * sarjat.lisaa(bloodline);
     * sarjat.lisaa(bloodline);
     * sarjat.lisaa(bloodline);
     * sarjat.lisaa(bloodline);
     * sarjat.lisaa(bloodline);
     * sarjat.getLkm() === 12;
     * </pre>
     */
    public void lisaa(Sarja sarja) {
        if (lkm == alkiot.length) {
            Sarja[] uusiTaulukko = new Sarja[alkiot.length*2];          
            for (int i = 0; i < alkiot.length; i++) {
                uusiTaulukko[i] = alkiot[i];
            }
            alkiot = uusiTaulukko;
        }
        alkiot[lkm] = sarja;
        lkm++;
    }
    
    
    /**
     * Poistaa sarjan tietorakenteesta. Liikuttaa muita alkioita taulukossa siten, ettei tietorakenteeseen j‰‰ tyhji‰ aukkoja.
     * @param sarja Poistettavan sarjan viite
     * @example
     * <pre name="test">
     * Sarjat sarjat = new Sarjat();
     * Sarja bloodline = new Sarja(), breakingBad = new Sarja();
     * sarjat.lisaa(bloodline);
     * sarjat.lisaa(breakingBad);
     * sarjat.getLkm() === 2;
     * sarjat.poista(bloodline);
     * sarjat.getLkm() === 1;
     * </pre>
     */
    public void poista(Sarja sarja) {
        
        Sarja[] uusiTaulukko = new Sarja[alkiot.length];

        int i = 0;
        int j = 0;
        while(i < alkiot.length) {
            if(alkiot[i] != sarja) {
            uusiTaulukko[j] = alkiot[i];
            j++;
            }            
            i++;       
        }
        if(Arrays.equals(alkiot, uusiTaulukko)) return;
        alkiot = uusiTaulukko;
        lkm--;
    }


    /**
     * Palauttaa viitteen i:teen sarjaan
     * @param i Monennenko sarjan viite halutaan
     * @return Viite sarjaan, jonka indeksi on i
     * @example
     * <pre name="test">
     * Sarjat sarjat = new Sarjat();
     * Sarja sarja1 = new Sarja(), sarja2 = new Sarja(), sarja3 = new Sarja();
     * sarjat.lisaa(sarja1);
     * sarjat.lisaa(sarja2);
     * sarjat.lisaa(sarja3);
     * sarjat.anna(1) === sarja2;
     * </pre>
     */
    public Sarja anna(int i) {
        return alkiot[i];
    }


    /**
     * @return Tv-sarjojen lukum‰‰r‰
     * @example
     * <pre name="test">
     * Sarjat sarjat = new Sarjat();
     * Sarja sarja1 = new Sarja(), sarja2 = new Sarja();
     * sarjat.lisaa(sarja1);
     * sarjat.lisaa(sarja2);
     * sarjat.getLkm() === 2;
     * </pre>
     */
    public int getLkm() {
        return lkm;
    }
    
    
    /**
     * Tallentaa sarjat tiedostoon
     * @throws TiedostoPoikkeus mik‰li tallennus ep‰onnistuu
     */
    public void tallenna() throws TiedostoPoikkeus {
        try (PrintStream fo = new PrintStream(new FileOutputStream("sarjat.txt"))) {
            for (int i = 0; i < lkm; i++) {
                fo.println(anna(i).toString());
            }
        } catch (FileNotFoundException e) {
            throw new TiedostoPoikkeus("Tiedoston lukemisessa ongelma! Tiedostoa ei voida avata");
        }
    }
    
    
    /**
     * Lukee sarjat tiedostosta
     * @param tiedostonNimi Mit‰ tiedostoa luetaan
     * @throws TiedostoPoikkeus mik‰li lukeminen ei onnistu
     * @example
     * <pre name="test">
     * #THROWS IOException
     * #import java.io.File;
     * #import fi.jyu.mit.ohj2.VertaaTiedosto;
     * #import java.io.*;
     * VertaaTiedosto.kirjoitaTiedosto("koe.txt", " 2 | Pikku Kakkonen | 3 | 5 | 5 | 16 |-| J‰i kohtaan sit‰ sun t‰t‰\n 1 | Salatut El‰m‰t | 2 | 1  | 4 | 13 |-| Kohdassa t‰t‰ sun sit‰");
     * </pre>
     * <pre name="test">
     * #THROWS TiedostoPoikkeus
     * Sarjat sarjat = new Sarjat();
     * sarjat.lueTiedostosta("koe.txt");
     * sarjat.anna(0).getNimi() === "Pikku Kakkonen";
     * sarjat.anna(1).getNimi() === "Salatut El‰m‰t";
     * sarjat.anna(0).getTilanne() === "J‰i kohtaan sit‰ sun t‰t‰";
     * sarjat.anna(1).getKausi() === 1;
     * </pre>
     * <pre name="test">
     * #THROWS IOException
     * VertaaTiedosto.tuhoaTiedosto("koe.txt");
     * </pre>
     */
    public void lueTiedostosta(String tiedostonNimi) throws TiedostoPoikkeus {   
        try (Scanner tiedosto = new Scanner(new FileInputStream(new File(tiedostonNimi)))) {
            String rivi = "";
            while (tiedosto.hasNextLine()) {
                rivi = tiedosto.nextLine();
                rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                Sarja sarja = new Sarja();
                sarja.parse(rivi);
                lisaa(sarja);  
                }
        } catch (FileNotFoundException e) {
            throw new TiedostoPoikkeus("Luettavaa tiedostoa ei voida avata");
        }
    }
    
    
    /**
     * Etsii hakuehdon perusteella sopivan nimisi‰ sarjoja
     * @param hakuehto K‰ytt‰j‰n syˆtt‰m‰ hakuehto
     * @return Lista hakuehdolla lˆytyneist‰ sarjoista
     */
    public ArrayList<Sarja> etsi(String hakuehto) {
        String ehto = "*"; 
        if ( hakuehto != null && hakuehto.length() > 0 ) ehto = hakuehto; 
        ArrayList<Sarja> loytyneet = new ArrayList<Sarja>();
        for (int i = 0; i < lkm; i++) {
            if (WildChars.onkoSamat(anna(i).getNimi(), ehto)) loytyneet.add(anna(i));
        }
        return loytyneet;
    }


    /**
     * Testataan Sarjat luokan toimintaa
     * @param args Ei k‰ytˆss‰
     * @throws TiedostoPoikkeus jos ongelmia tiedoston avaamisessa
     */
    public static void main(String[] args) throws TiedostoPoikkeus {
        //
    }

}
