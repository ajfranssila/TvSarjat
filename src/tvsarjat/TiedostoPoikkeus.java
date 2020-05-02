package tvsarjat;

/**
 * Poikkeusluokka tietorakenteesta aiheutuville poikkeuksille.
 * @author Antti
 * @version 20.4.2018
 */
public class TiedostoPoikkeus extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Poikkeuksen muodostaja jolle parametrina vied‰‰n poikkeuksen viesti
     * @param viesti Poikkeuksen l‰hett‰m‰ viesti
     */
    public TiedostoPoikkeus(String viesti) {
        super(viesti);
    }
}