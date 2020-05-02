/**
 * 
 */
package tvsarjat;

import java.util.ArrayList;

/**
 * Tvsarjat luokka hallinnoi sarjat ja alustat luokkia v‰litt‰en niille teht‰vi‰, joita saa k‰yttˆliittym‰st‰.
 * @author Antti
 * @version 20.3.2018
 */
public class Tvsarjat {
    
    private Sarjat sarjat = new Sarjat();
    private Alustat alustat = new Alustat();
    
    /**
     * Palauttaa sarjojen lukum‰‰r‰n
     * @return Sarjojen lukum‰‰r‰
     * @example
     * <pre name="test">
     * Tvsarjat sarjat = new Tvsarjat();
     * Sarja sarja = new Sarja();
     * Sarja toinenSarja = new Sarja();
     * sarjat.lisaa(sarja);
     * sarjat.lisaa(toinenSarja);
     * sarjat.getSarjat() === 2;
     * </pre>
     */
    public int getSarjat() {
        return sarjat.getLkm();
    }
    
    
    /**
     * Palauttaa alustojen lukum‰‰r‰n
     * @return Alustojen lukum‰‰r‰
     * @example
     * <pre name="test">
     * Tvsarjat sarjat = new Tvsarjat();
     * Alusta netflix = new Alusta();
     * Alusta ruutu = new Alusta();
     * sarjat.lisaa(netflix);
     * sarjat.lisaa(ruutu);
     * sarjat.getAlustat() === 2;
     * </pre>
     */ 
    public int getAlustat() {
        return alustat.getLkm();
    }
    
    
    /**
     * Palauttaa i:n sarjan
     * @param i Monesko sarja palautetaan
     * @return Viite i:teen sarjaan
     */
    public Sarja annaSarja(int i) {
        return sarjat.anna(i);
    }
    
    
    /**
     * Palauttaa i:n alustan
     * @param i Monesko alusta palautetaan
     * @return Viite i:teen alustaan
     */
    public Alusta annaAlusta(int i) {
        return alustat.anna(i);
        //
    }

    
    /**
     * Lis‰‰ uuden sarjan
     * @param sarja Sarja, joka lis‰t‰‰n
     * @example
     * <pre name="test">
     * Tvsarjat sarjat = new Tvsarjat();
     * Sarja sarja = new Sarja();
     * Sarja toinenSarja = new Sarja();
     * sarjat.lisaa(sarja);
     * sarjat.lisaa(toinenSarja);
     * sarjat.getSarjat() === 2;
     * sarjat.lisaa(sarja);
     * sarjat.lisaa(sarja);
     * sarjat.lisaa(sarja);
     * sarjat.lisaa(sarja);
     * sarjat.lisaa(sarja);
     * sarjat.lisaa(sarja);
     * sarjat.lisaa(sarja);
     * sarjat.lisaa(sarja);
     * sarjat.lisaa(sarja);
     * sarjat.lisaa(sarja);
     * sarjat.getSarjat() === 12;
     * </pre>
     */
    public void lisaa(Sarja sarja) {
        sarjat.lisaa(sarja);  
    }
    
    
    /**
     * Lis‰‰ uuden alustan
     * @param alusta Alusta, joka lis‰t‰‰n
     * @example
     * <pre name="test">
     * Tvsarjat sarjat = new Tvsarjat();
     * Alusta netflix = new Alusta();
     * Alusta ruutu = new Alusta();
     * Alusta katsomo = new Alusta();
     * sarjat.lisaa(netflix);
     * sarjat.lisaa(ruutu);
     * sarjat.getAlustat() === 2;
     * sarjat.lisaa(katsomo);
     * sarjat.getAlustat() === 3;
     * </pre>
     */
    public void lisaa(Alusta alusta) {
        alustat.lisaa(alusta);
    }
    
    
    /**
     * Poistaa sarjan
     * @param sarja Sarja, joka poistetaan
     * @example
     * <pre name="test">
     * Tvsarjat sarjat = new Tvsarjat();
     * Sarja sarja = new Sarja();
     * Sarja toinenSarja = new Sarja();
     * sarjat.lisaa(sarja);
     * sarjat.lisaa(toinenSarja);
     * sarjat.getSarjat() === 2;
     * sarjat.poista(toinenSarja);
     * sarjat.getSarjat() === 1;
     * sarjat.poista(sarja);
     * sarjat.getSarjat() === 0;
     * sarjat.poista(sarja);
     * sarjat.getSarjat() === 0;  
     * </pre>
     */
    public void poista(Sarja sarja) {
        sarjat.poista(sarja);
    }
    
    /**
     * Poistaa alustan
     * @param alusta alusta, joka poistetaan
     * @example
     * <pre name="test">
     * Tvsarjat sarjat = new Tvsarjat();
     * Alusta alusta = new Alusta();
     * Alusta toinenAlusta = new Alusta();
     * sarjat.lisaa(alusta);
     * sarjat.lisaa(toinenAlusta);
     * sarjat.getAlustat() === 2;
     * sarjat.poista(toinenAlusta);
     * sarjat.getAlustat() === 1;
     * sarjat.poista(alusta);
     * sarjat.getAlustat() === 0;
     * sarjat.poista(alusta);
     * sarjat.getAlustat() === 0;  
     * </pre>
     */
    public void poista(Alusta alusta) {
        alustat.poista(alusta);
    }
    
    
    /**
     * Lukee sarjat tiedostosta
     * @throws TiedostoPoikkeus jos lukeminen ei onnistu
     */
    public void lueTiedostosta() throws TiedostoPoikkeus {
        sarjat = new Sarjat();
        sarjat.lueTiedostosta("sarjat.txt");
        alustat = new Alustat();
        alustat.lueTiedostosta("alustat.txt");
    }
    
    
    /**
     * @param hakuehto K‰ytt‰j‰n syˆtt‰m‰ hakuehto
     * @return Lista sarjoista hakuehdon perusteella
     */
    public ArrayList<Sarja> etsi(String hakuehto) {
        return sarjat.etsi(hakuehto);
    }
    
    
    /**
     * Tallentaa sarjojen ja alustojen tiedot tiedostoihin.
     * @throws TiedostoPoikkeus jos tiedoston avauksessa ongelmia
     */
    public void tallenna() throws TiedostoPoikkeus {
        sarjat.tallenna();
        alustat.tallenna();
        
    }
    
    
    /**
     * @param args Ei k‰ytˆss‰
     */
    public static void main(String[] args) {
    //
    }

}
