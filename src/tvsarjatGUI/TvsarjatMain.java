package tvsarjatGUI;

import javafx.application.Application;
import javafx.stage.Stage;
import tvsarjat.Tvsarjat;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author Antti
 * @version 28.1.2018
 * Ohjelma tv-sarjojen tietojen talletusta varten. Ohjelman avulla on helppo pitää kirjaa siitä,
 * mitä tv-sarjoja on katsonut, millä katsomisalustalla ja mihin kohtaan sarja on viime katsomalta
 * jäänyt.
 */
public class TvsarjatMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("TvsarjatView.fxml"));
            final Pane root = ldr.load();
            final TvsarjatController tvsarjatCtrl = (TvsarjatController) ldr.getController();
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("tvsarjat.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Tv-sarjat");
            primaryStage.setMinWidth(610);
            primaryStage.setMinHeight(270);
            
            Tvsarjat tvsarjat = new Tvsarjat();
            tvsarjatCtrl.setTvsarjat(tvsarjat);
            tvsarjatCtrl.lueTiedosto();
            
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}