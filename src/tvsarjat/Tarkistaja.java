package tvsarjat;

/**
 * @author Antti
 * @version 22.4.2018
 *
 */
public class Tarkistaja {

    /**
     * Tarkistaa onko sy�te oikeanlainen. Palauttaa virheen, jos ei ole.
     * @param jono Tarkistettava sy�te
     * @return Palauttaa virhelmoituksen, jos sy�te on virheellinen
     * @example
     * <pre name="test">
     * Tarkistaja tarkistaja = new Tarkistaja();
     * tarkistaja.tarkista("Breaking Bad") === null;
     * tarkistaja.tarkista("") === null;
     * tarkistaja.tarkista("Moro ~") === "Virheellinen sy�te. Sis�lt�� erikoismerkkej�";
     * tarkistaja.tarkista("123 | 2") === "Virheellinen sy�te. Sis�lt�� erikoismerkkej�";
     * tarkistaja.tarkista("ab#ab") === "Virheellinen sy�te. Sis�lt�� erikoismerkkej�";
     * tarkistaja.tarkista("moro") === null;
     * tarkistaja.tarkista("moro : morjeS") === null;
     * tarkistaja.tarkista("0") === null;
     * tarkistaja.tarkista("Str�ms�") === null;
     * </pre>
     */
    public String tarkista(String jono) {
        if (jono.length() == 0) return null;
        if (!jono.matches("^[-\\w.\\s:,���]+")) return "Virheellinen sy�te (erikoismerkkej�)";
        return null;
    }
    
    /**
     * @param jono Tarkistettav sy�te
     * @return Palauttaa virheilmoituksen, jos sy�te on virheellinen-
     * @example
     * <pre name="test">
     * Tarkistaja tarkistaja = new Tarkistaja();
     * tarkistaja.tarkistaNumero("1") === null;
     * tarkistaja.tarkistaNumero("12") === null;
     * tarkistaja.tarkistaNumero("1 2") === "Virheellinen sy�te";
     * tarkistaja.tarkistaNumero("asd") === "Virheellinen sy�te";
     * tarkistaja.tarkistaNumero("9999") === null;
     * tarkistaja.tarkistaNumero("0") === null;
     * </pre>
     */
    public String tarkistaNumero(String jono) {
        if (jono.length() == 0) return null;
        if (!jono.matches("^\\d+$")) return "Virheellinen sy�te (ei pos. kokonaisluku)";
        return null;
    }

}
