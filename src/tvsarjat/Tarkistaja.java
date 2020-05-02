package tvsarjat;

/**
 * @author Antti
 * @version 22.4.2018
 *
 */
public class Tarkistaja {

    /**
     * Tarkistaa onko syöte oikeanlainen. Palauttaa virheen, jos ei ole.
     * @param jono Tarkistettava syöte
     * @return Palauttaa virhelmoituksen, jos syöte on virheellinen
     * @example
     * <pre name="test">
     * Tarkistaja tarkistaja = new Tarkistaja();
     * tarkistaja.tarkista("Breaking Bad") === null;
     * tarkistaja.tarkista("") === null;
     * tarkistaja.tarkista("Moro ~") === "Virheellinen syöte. Sisältää erikoismerkkejä";
     * tarkistaja.tarkista("123 | 2") === "Virheellinen syöte. Sisältää erikoismerkkejä";
     * tarkistaja.tarkista("ab#ab") === "Virheellinen syöte. Sisältää erikoismerkkejä";
     * tarkistaja.tarkista("moro") === null;
     * tarkistaja.tarkista("moro : morjeS") === null;
     * tarkistaja.tarkista("0") === null;
     * tarkistaja.tarkista("Strömsö") === null;
     * </pre>
     */
    public String tarkista(String jono) {
        if (jono.length() == 0) return null;
        if (!jono.matches("^[-\\w.\\s:,åäö]+")) return "Virheellinen syöte (erikoismerkkejä)";
        return null;
    }
    
    /**
     * @param jono Tarkistettav syöte
     * @return Palauttaa virheilmoituksen, jos syöte on virheellinen-
     * @example
     * <pre name="test">
     * Tarkistaja tarkistaja = new Tarkistaja();
     * tarkistaja.tarkistaNumero("1") === null;
     * tarkistaja.tarkistaNumero("12") === null;
     * tarkistaja.tarkistaNumero("1 2") === "Virheellinen syöte";
     * tarkistaja.tarkistaNumero("asd") === "Virheellinen syöte";
     * tarkistaja.tarkistaNumero("9999") === null;
     * tarkistaja.tarkistaNumero("0") === null;
     * </pre>
     */
    public String tarkistaNumero(String jono) {
        if (jono.length() == 0) return null;
        if (!jono.matches("^\\d+$")) return "Virheellinen syöte (ei pos. kokonaisluku)";
        return null;
    }

}
