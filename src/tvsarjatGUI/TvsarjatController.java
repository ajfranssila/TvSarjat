package tvsarjatGUI;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.*;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import tvsarjat.Alusta;
import tvsarjat.Sarja;
import tvsarjat.Tarkistaja;
import tvsarjat.TiedostoPoikkeus;
import tvsarjat.Tvsarjat;

/**
 * Luokka Tv-sarjat ohjelman k‰yttˆliittym‰tapahtumien hallinnoimiseksi
 * @author Antti
 * @version 28.1.2018
 *
 */
public class TvsarjatController implements Initializable {
    @FXML private Button idLisaaUusi;
    @FXML private Button idPoista;
    @FXML private Button idTallenna;
    @FXML private Button idLisaaAlusta;
    @FXML private Button idLopeta;
    @FXML private Button idTietoja;
    @FXML private Button idPikakomennot;
    @FXML private TextField textFieldNimi;
    @FXML private TextField textFieldKausi;
    @FXML private TextField textFieldJakso;
    @FXML private TextField textFieldSeuraavaKausi;
    @FXML private TextField textFieldSeuraavaJakso;
    @FXML private ListChooser<Sarja> chooserTvsarjat;
    @FXML private TextArea textAreaTilanne;
    @FXML private ComboBoxChooser<Alusta> comboBoxChooserKatsomisalusta;
    @FXML private TextField hakuehto;
    @FXML private Label labelVirhe;
    
    @Override
    public void initialize(URL url, ResourceBundle bubdle) {
        alusta();        
    }
    
    @FXML void handleLisaaUusi() {
        lisaaUusi();
    }

    @FXML void handlePoista() {
        poista();
    }
    
    @FXML void handlePoistaAlusta() {
        poistaAlusta();
    }

    @FXML void handleTallenna() {
        tallenna();
    }

    @FXML void handleLisaaAlusta() {
        lisaaAlusta();
    } 

    @FXML void handleLopeta() {
        lopeta();
    }  

    @FXML void handleTietoja() {
        tietoja();
    }

    @FXML void handlePikakomennot() {
        pikakomennot();
    }
    
    @FXML void handleHakuehto() {
        hae(0);
    }
    



// --------------------------------------------------------------------------------------
// Alla ei k‰yttˆliittym‰‰n suoraan liittyv‰ koodi

    private Tvsarjat tvsarjat;    
    
    
    /**
     * Alustaa tekstikent‰t ja alustavalikon sek‰ lis‰‰ niihin kuuntelijat.
     */
    private void alusta() {
        chooserTvsarjat.clear();
        labelVirhe.setVisible(false);
        chooserTvsarjat.addSelectionListener(e -> naytaSarja());
        chooserTvsarjat.addSelectionListener(e -> naytaAlusta());
        comboBoxChooserKatsomisalusta.addSelectionListener(e -> vaihdaAlusta());
        textFieldNimi.setOnKeyReleased(e -> muutaTiedot(textFieldNimi));
        textFieldKausi.setOnKeyReleased(e -> muutaTiedot(textFieldKausi));
        textFieldJakso.setOnKeyReleased(e -> muutaTiedot(textFieldJakso));        
        textFieldSeuraavaKausi.setOnKeyReleased(e -> muutaTiedot(textFieldSeuraavaKausi));
        textFieldSeuraavaJakso.setOnKeyReleased(e -> muutaTiedot(textFieldSeuraavaJakso));
        textAreaTilanne.setOnKeyReleased(e -> muutaTiedot(textAreaTilanne));
    }
    
    /**
     * N‰ytt‰‰ listasta valitun sarjan tiedot
     */
    private void naytaSarja() {     
        if (tvsarjat.getSarjat() == 0) {
            try(PrintStream os = TextAreaOutputStream.getTextPrintStream(textAreaTilanne)) {
                os.println("");
                }
        }
        Sarja sarjaKohdalla = chooserTvsarjat.getSelectedObject();
        if (sarjaKohdalla == null) return;
        textAreaTilanne.setText(sarjaKohdalla.getTilanne());
        textAreaTilanne.setWrapText(true);
        textFieldNimi.setText(sarjaKohdalla.getNimi());
        textFieldKausi.setText(Integer.toString(sarjaKohdalla.getKausi()));
        textFieldSeuraavaKausi.setText(Integer.toString(sarjaKohdalla.getSeuraavanKausi()));
        textFieldJakso.setText(Integer.toString(sarjaKohdalla.getJakso()));
        textFieldSeuraavaJakso.setText(Integer.toString(sarjaKohdalla.getSeuraavaJakso()));
    }
    
    /**
     * N‰ytt‰‰ listasta valitun sarjan alustan tilap‰isesti "tilanne, johon j‰i" kent‰ss‰
     */
    private void naytaAlusta() {
        int sarjanAlustanNro = chooserTvsarjat.getSelectedObject().getAlustanNro();
        haeAlustat(sarjanAlustanNro);
    }
    
    /**
     * Lis‰‰ uuden sarjan
     */
    private void lisaaUusi() {
        Sarja uusi = new Sarja();
        uusi.rekisteroi();
        tvsarjat.lisaa(uusi);
        hae(uusi.getTunnusNro());
    }
    
    /**
     * Hakee sarjojen tiedot listaan
     * @param sarjanNro Mink‰ sarjan tiedot haetaan
     */
    private void hae(int sarjanNro) {
        chooserTvsarjat.clear();
        String ehto = hakuehto.getText();
        if (ehto.indexOf('*') < 0) ehto = "*" + ehto + "*";
        int index = 0;
        ArrayList<Sarja> sarjat;
        sarjat = tvsarjat.etsi(ehto);
        for (int i = 0; i < sarjat.size(); i++) {
            Sarja sarja = sarjat.get(i);
            if (sarja.getTunnusNro() == sarjanNro) index = i;
            chooserTvsarjat.add(sarja.getNimi(), sarja);
        }
        chooserTvsarjat.getSelectionModel().select(index);
    }
    
    /**
     * Hakee alustojen tiedot omaan kentt‰‰n
     * @param alustanNro Mik‰ alusta valittuna
     */
    private void haeAlustat(int alustanNro) {
        comboBoxChooserKatsomisalusta.clear();
        int index = -1;
        for (int i = 0; i < tvsarjat.getAlustat(); i++) {
            Alusta alusta = tvsarjat.annaAlusta(i);
            if (alusta.getTunnusNro() == alustanNro) index = i;
            comboBoxChooserKatsomisalusta.add(alusta.getNimi(), alusta);
        }
        if (index == -1) comboBoxChooserKatsomisalusta.getSelectionModel().clearSelection();
        else comboBoxChooserKatsomisalusta.getSelectionModel().select(index);     
    }
    
    /**
     * Poistaa sarjan
     */
    private void poista() {
        if (tvsarjat.getSarjat() == 0) return;
        
        Sarja sarjaKohdalla = chooserTvsarjat.getSelectedObject();
        tvsarjat.poista(sarjaKohdalla);
        
        chooserTvsarjat.clear();
        if (tvsarjat.getSarjat() != 0) {
            for (int i = 0; i < tvsarjat.getSarjat(); i++) {
                Sarja sarja = tvsarjat.annaSarja(i);
                chooserTvsarjat.add(sarja.getNimi(), sarja);
            }
            chooserTvsarjat.getSelectionModel().select((tvsarjat.getSarjat() - 1));
        }
        if (tvsarjat.getSarjat() == 0) {
            textAreaTilanne.clear();
        }
    }
    
    /**
     * Poistaa alustan
     */
    private void poistaAlusta() {
        if (tvsarjat.getAlustat() == 0) return;
        Alusta alustaKohdalla = comboBoxChooserKatsomisalusta.getSelectedObject();
        tvsarjat.poista(alustaKohdalla);
        
        comboBoxChooserKatsomisalusta.clear();
        if (tvsarjat.getAlustat() != 0) {
            for (int i = 0; i < tvsarjat.getAlustat(); i++) {
                Alusta alusta = tvsarjat.annaAlusta(i);
                comboBoxChooserKatsomisalusta.add(alusta.getNimi(), alusta);
            } 
        }
    }
    
    /**
     * Tallentaa muutokset
     * @throws TiedostoPoikkeus jos tiedoston avauksessa ongelmia
     */
    private void tallenna() {
        try {
            tvsarjat.tallenna();
        } catch (TiedostoPoikkeus e) {
            Dialogs.showMessageDialog("Tallennuksessa ongelmia! " + e.getMessage());
        }
    }
    
    /**
     * Alustaa tvsarjat-olion lukemalla siihen kuuluvat sarjat ja alustat tiedostosta
     */
    public void lueTiedosto() {
        try {
            tvsarjat.lueTiedostosta();
            hae(0);
            if (tvsarjat.getSarjat() > 0) haeAlustat(tvsarjat.annaSarja(0).getAlustanNro());
        } catch (tvsarjat.TiedostoPoikkeus e) {
            hae(0);
            String virhe = e.getMessage();
            if ( virhe != null ) Dialogs.showMessageDialog(virhe);
        }

    }
    
    /**
     * Vaihtaa sarjalle alustan
     */
    private void vaihdaAlusta() {
        Sarja sarjaKohdalla = chooserTvsarjat.getSelectedObject();
        if (sarjaKohdalla == null) return;
        Alusta alustaKohdalla = comboBoxChooserKatsomisalusta.getSelectedObject();
        if (alustaKohdalla == null) return;
        sarjaKohdalla.setAlustanNro(alustaKohdalla.getTunnusNro());
    }
    
    /**
     * Lis‰‰ uuden alustan. Kysyy k‰ytt‰j‰lt‰ alustan nime‰.
     */
    private void lisaaAlusta() {
        String alustanNimi = Dialogs.showInputDialog("Anna alustan nimi", "", dlg -> dlg.setTitle("Uuden alustan lis‰‰minen"));
        if ( alustanNimi == null || alustanNimi.equals("") ) return;
        Alusta uusi = new Alusta();
        uusi.rekisteroi();
        uusi.setNimi(alustanNimi);
        tvsarjat.lisaa(uusi);
        haeAlustat(uusi.getTunnusNro());
        
        Sarja sarjaKohdalla = chooserTvsarjat.getSelectedObject();
        sarjaKohdalla.setAlustanNro(uusi.getTunnusNro());
        naytaSarja();
        naytaAlusta();
    }
    
    /**
     * Tallentaa tekstikenttien tietoja keskusmuistiin, kun tekstikenttien arvoja muutetaan. Tarkistuttaa kenttien oikeellisuuden.
     * @param kentta Mit‰ tekstikentt‰‰ k‰sitell‰‰n
     */
    private void muutaTiedot(TextField kentta) {      
        Sarja sarjaKohdalla = chooserTvsarjat.getSelectedObject();
        PauseTransition virheilmoituksenNaytto = new PauseTransition(Duration.seconds(2));
        virheilmoituksenNaytto.setOnFinished(event -> labelVirhe.setVisible(false));
        Tarkistaja tarkistaja = new Tarkistaja();
        
        if (kentta == textFieldNimi) {
            String teksti = textFieldNimi.getText();            
            String virhe = tarkistaja.tarkista(teksti);
            if (virhe == null) {
                sarjaKohdalla.setNimi(teksti);
            } 
            else 
            {
                labelVirhe.setText(virhe);
                labelVirhe.setVisible(true);
                virheilmoituksenNaytto.play();
            }
        }
        else if (kentta == textFieldJakso) {
            String kentanTeksti = textFieldJakso.getText();   
            if (kentanTeksti.isEmpty()) return;
            String virhe = tarkistaja.tarkistaNumero(kentanTeksti);        
            if (virhe == null) {
                sarjaKohdalla.setJakso(Integer.parseInt(kentanTeksti));
            }
            else 
            {
                labelVirhe.setText(virhe);
                labelVirhe.setVisible(true);
                virheilmoituksenNaytto.play();
            }
        } 
        else if (kentta == textFieldSeuraavaJakso) { 
            String kentanTeksti = textFieldSeuraavaJakso.getText();   
            if (kentanTeksti.isEmpty()) return;
            String virhe = tarkistaja.tarkistaNumero(kentanTeksti);        
            if (virhe == null) {
                sarjaKohdalla.setSeuraavaJakso(Integer.parseInt(kentanTeksti));
            }
            else 
            {
                labelVirhe.setText(virhe);
                labelVirhe.setVisible(true);
                virheilmoituksenNaytto.play();
            }    
        } 
        else if (kentta == textFieldKausi) { 
            String kentanTeksti = textFieldKausi.getText();   
            if (kentanTeksti.isEmpty()) return;
            String virhe = tarkistaja.tarkistaNumero(kentanTeksti);        
            if (virhe == null) {
                sarjaKohdalla.setKausi(Integer.parseInt(kentanTeksti));
            }
            else
            {
                labelVirhe.setText(virhe);
                labelVirhe.setVisible(true);
                virheilmoituksenNaytto.play();
            }    
        } 
        else if (kentta == textFieldSeuraavaKausi) { 
            String kentanTeksti = textFieldSeuraavaKausi.getText();   
            if (kentanTeksti.isEmpty()) return;
            String virhe = tarkistaja.tarkistaNumero(kentanTeksti);        
            if (virhe == null) {
                sarjaKohdalla.setSeuraavanKausi(Integer.parseInt(kentanTeksti));
            }
            else
            {
                labelVirhe.setText(virhe);
                labelVirhe.setVisible(true);
                virheilmoituksenNaytto.play();
            }
        }     
        hae(sarjaKohdalla.getTunnusNro());
        kentta.end();
    }
    
    /**
     * tallentaa tekstiarean tietoja keskusmuistiin, kun tekstiarean arvoja muutetaan
     * @param kentta Mit‰ tekstiareaa k‰sitell‰‰n
     */
    private void muutaTiedot(TextArea kentta) {
        Sarja sarjaKohdalla = chooserTvsarjat.getSelectedObject();
        sarjaKohdalla.setTilanne(kentta.getText());
        hae(sarjaKohdalla.getTunnusNro());
        kentta.end();
    }
    
    /**
     * Lopettaa ohjelman
     */
    private void lopeta() {
        Platform.exit();
    }
    
    /**
     * Avaa ohjelman tietoja sis‰lt‰v‰n ikkunan
     */
    private void tietoja() {
        ModalController.showModal(TvsarjatController.class.getResource("TvsarjatTietoja.fxml"), "Tvsarjat", null, "");
    }
    
    /**
     * Avaa ohjelman pikakomennot sis‰lt‰v‰n ikkunan
     */
    private void pikakomennot() {
        ModalController.showModal(TvsarjatController.class.getResource("TvsarjatPikakomennot.fxml"), "Tvsarjat", null, "");
    }

    /**
     * @param tvsarjat Tvsarjat, jota k‰ytet‰‰n t‰ss‰ k‰yttˆliittym‰ss‰
     */
    public void setTvsarjat(Tvsarjat tvsarjat) {
        this.tvsarjat = tvsarjat;     
    }

}