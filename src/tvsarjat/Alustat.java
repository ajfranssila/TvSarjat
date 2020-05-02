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
import java.util.Scanner;

/**
 * Alustat luokka alustojen hallintaa varten. Yll‰pit‰‰ alustoista teht‰v‰‰ listaa.
 * @author Antti
 * @version 1.3.2018
 *
 */
public class Alustat {
    ArrayList<Alusta> alkiot = new ArrayList<Alusta>();
    
    
    /**
     * Palautaa alustojen lukum‰‰r‰n
     * @return Alustojen lukum‰‰r‰
     * @example
     * <pre name="test">
     * Alustat alustat = new Alustat();
     * Alusta netflix = new Alusta();
     * Alusta ruutu = new Alusta();
     * alustat.lisaa(netflix);
     * alustat.lisaa(ruutu);
     * alustat.getLkm() === 2;
     * </pre>
     */
    public int getLkm() {
        return alkiot.size();
    }
    
    
    /**
     * Palauttaa viitteen i:teen alustaan
     * @param i Monennenko alustan viite halutaan
     * @return Viite alustaan, jonka indeksi on i
     * @example
     * <pre name="test">
     * Alustat alustat = new Alustat();
     * Alusta alusta1 = new Alusta(), alusta2 = new Alusta(), alusta3 = new Alusta();
     * alustat.lisaa(alusta1);
     * alustat.lisaa(alusta2);
     * alustat.lisaa(alusta3);
     * alustat.anna(1) === alusta2;
     * </pre>
     */
    public Alusta anna(int i) {
        return alkiot.get(i);
    }
    
    
    /**
     * Lis‰‰ uuden alustan tietorakenteeseen.
     * @param alusta Lis‰tt‰v‰n alustan viite
     * @example
     * <pre name="test">
     * Alustat alustat = new Alustat();
     * Alusta netflix = new Alusta();
     * Alusta ruutu = new Alusta();
     * alustat.lisaa(netflix);
     * alustat.lisaa(ruutu);
     * alustat.getLkm() === 2;
     * </pre>
     */
    public void lisaa(Alusta alusta) {
        alkiot.add(alusta);
    }
    
    
    /**
     * Poistaa halutun alustan tietorakenteesta.
     * @param alusta Poistettavan alustan viite
     * <pre name="test">
     * Alustat alustat = new Alustat();
     * Alusta netflix = new Alusta();
     * Alusta ruutu = new Alusta();
     * alustat.lisaa(netflix);
     * alustat.lisaa(ruutu);
     * alustat.getLkm() === 2;
     * alustat.poista(netflix);
     * alustat.getLkm() === 1;
     * </pre>
     */
    public void poista(Alusta alusta) {
        alkiot.remove(alusta);
    }
    
    
    /**
     * Tallentaa alustat tiedostoon
     * @throws TiedostoPoikkeus mik‰li tallennus ep‰onnistuu
     */
    public void tallenna() throws TiedostoPoikkeus {
        try (PrintStream fo = new PrintStream(new FileOutputStream("alustat.txt"))) {
            for (int i = 0; i < alkiot.size(); i++) {
                fo.println(anna(i).toString());
            }
        } catch (FileNotFoundException e) {
            throw new TiedostoPoikkeus("Tiedostoa, johon yritet‰‰n tallentaa, ei voida avata");
        }
    }
    
    
    /**
     * Lukee alustat tiedostosta
     * @param tiedostonNimi Mit‰ tiedostoa luetaan
     * @throws TiedostoPoikkeus jos tiedoston lukemisessa ongelmia
     * @example
     * <pre name="test">
     * #THROWS IOException
     * #import java.io.File;
     * #import fi.jyu.mit.ohj2.VertaaTiedosto;
     * #import java.io.*;
     * VertaaTiedosto.kirjoitaTiedosto("koealustat.txt", "1 | Netflix \n 2 | Ruutu \n 3 | Yle Areena \n 4 | Katsomo \n 5 | Viaplay");
     * </pre>
     * <pre name="test">
     * #THROWS TiedostoPoikkeus
     * Alustat alustat = new Alustat();
     * alustat.lueTiedostosta("koealustat.txt");
     * alustat.getLkm() === 5;
     * </pre>
     * <pre name="test">
     * VertaaTiedosto.tuhoaTiedosto("koealustat.txt");
     * </pre>
     */
    public void lueTiedostosta(String tiedostonNimi) throws TiedostoPoikkeus {   
        try (Scanner tiedosto = new Scanner(new FileInputStream(new File(tiedostonNimi)))) {
            String rivi;
            while (tiedosto.hasNextLine()) {
                rivi = tiedosto.nextLine();
                rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                Alusta alusta = new Alusta();
                alusta.parse(rivi);
                lisaa(alusta);  
                }
        } catch (FileNotFoundException e) {
            throw new TiedostoPoikkeus("Tiedoston lukemisessa ongelma! Tiedostoa ei voida avata");
        }
    }

    /**
     * Alustat luokan testausta. Ei k‰ytˆss‰.
     * @param args Ei k‰ytˆss‰
     * @throws TiedostoPoikkeus jos tiedostonk‰sittelyss‰ ongelmia
     */
    public static void main(String[] args) throws TiedostoPoikkeus {
        //
    }

}
