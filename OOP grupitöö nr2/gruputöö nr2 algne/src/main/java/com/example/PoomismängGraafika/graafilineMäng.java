package com.example.PoomismängGraafika;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class graafilineMäng extends Application {

    Scene avalehtStseen;
    public static Scene mängStseen;

    private TextField täheSisestusField = new TextField();

    private TextField arvatudTähedField = new TextField();

    private TextField tagasiside = new TextField();

    private Label arvamisiAllesLabel = new Label("");

    private int arvamisiAlles;

    private String ArvatavSõna;
    public static Text[] pakutavSõna;

    public static ObservableList<Node> arvamisJoonteChildren;
    public static ObservableList<Node> poomisPuuChildren;


    // Siin ArrayListis on kõik erinevad poomispuu osad.
    public static ArrayList<Shape> poomisPuuList = new ArrayList<Shape>();

    private int indeks;

    private int arvatudTähtedeArv;

    private boolean onEsimeneKord = true;

    private BorderPane borderPane;


    @Override
    public void start(Stage peaLava) throws IOException {
        // avalehtStseen/Stseen 1

        StackPane avaleheStackpane = new StackPane();

        avalehtStseen = new Scene(avaleheStackpane, 1000, 650);

        Label labelWelcome = new Label("Welcome");
        labelWelcome.setStyle("-fx-font-family: Georgia; -fx-font-weight: bold; -fx-font-size: 1.3em; -fx-text-fill: brown;");

        Button alustaMängugaNupp = new Button("Alusta mänguga!");
        alustaMängugaNupp.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0; -fx-background-radius: 8; -fx-background-color: linear-gradient(from 0% 93% to 0% 100%, silver 0%, silver 100%),white, silver, radial-gradient(center 50% 50%, radius 100%, white, silver); -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 ); -fx-font-weight: bold; -fx-font-size: 1.3em;");
        alustaMängugaNupp.setOnAction(e -> {

            try {
                mänguRestart();
            } catch (Exception ex) {
                System.out.println("Midagi on katki");
                System.exit(0); // Kui tekib error (katsetamiseks), siis sulgen lihtsalt mängu.
            }
            peaLava.setScene(mängStseen);
        });


        StackPane.setAlignment(alustaMängugaNupp, Pos.BOTTOM_CENTER);
        StackPane.setMargin(alustaMängugaNupp, new Insets(25, 25, 50, 25));
        StackPane.setAlignment(labelWelcome, Pos.TOP_CENTER);
        StackPane.setMargin(labelWelcome, new Insets(50, 0, 0, 0));

        ObservableList<javafx.scene.Node> avaleheAsjad = avaleheStackpane.getChildren();

        avaleheAsjad.addAll(labelWelcome, alustaMängugaNupp);

        avaleheStackpane.scaleXProperty().bind(peaLava.widthProperty().divide(1000));
        avaleheStackpane.scaleYProperty().bind(peaLava.heightProperty().divide(650));

        avaleheStackpane.setBackground(Taust1());


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // mängStseen/Stseen 2

        borderPane = new BorderPane();

        mängStseen = new Scene(borderPane, 1000, 650);

        StackPane stackPane2 = new StackPane();

        Button tagasiminekuNupp = new Button("Lõpeta mäng");
        tagasiminekuNupp.setOnAction(e -> peaLava.setScene(avalehtStseen));
        tagasiminekuNupp.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0; -fx-background-radius: 8; -fx-background-color: linear-gradient(from 0% 93% to 0% 100%, silver 0%, silver 100%),white, silver, radial-gradient(center 50% 50%, radius 100%, white, silver); -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 ); -fx-font-weight: bold; -fx-font-size: 1.3em;");


        StackPane.setMargin(tagasiminekuNupp, new Insets(50, 50, 50, 50));
        StackPane.setAlignment(tagasiminekuNupp, Pos.TOP_LEFT);

        ObservableList<javafx.scene.Node> list = stackPane2.getChildren();

        list.add(tagasiminekuNupp);

        Pane poomisPuu = new Pane();

        // Salvestan childreni, et seda kergesti kätte saada
        poomisPuuChildren = poomisPuu.getChildren();

        // Sätin paika tagasiside textfieldi
        tagasiside.setFont(new Font(20));
        tagasiside.setPrefWidth(400);
        tagasiside.setAlignment(Pos.TOP_CENTER);
        tagasiside.setEditable(false);
        tagasiside.setVisible(true);
        borderPane.setTop(tagasiside);


        // Parempoolne ekraani osa
        GridPane paremPoolsedAsjad = new GridPane();
        paremPoolsedAsjad.setHgap(10);
        paremPoolsedAsjad.setVgap(10);

        paremPoolsedAsjad.add(new Label("Sisesta täht:"), 0, 0);
        paremPoolsedAsjad.add(täheSisestusField, 1, 0);
        täheSisestusField.setPrefWidth(250);

        paremPoolsedAsjad.add(new Label("Arvatud tähed:"), 0, 1);
        paremPoolsedAsjad.add(arvatudTähedField, 1, 1);
        arvatudTähedField.setPrefWidth(250);
        arvatudTähedField.setEditable(false);

        paremPoolsedAsjad.add(new Label("Valesid pakkumisi jäänud: "), 0, 2);
        paremPoolsedAsjad.add(arvamisiAllesLabel, 1, 2);

        paremPoolsedAsjad.add(tagasiminekuNupp, 0, 13);
        paremPoolsedAsjad.setPadding(new Insets(20,0,0,0));

        borderPane.setRight(paremPoolsedAsjad);
        paremPoolsedAsjad.setAlignment(Pos.CENTER);


        borderPane.setBottom(stackPane2);
        borderPane.setCenter(poomisPuu);


        // Kui sisestada täht, siis käivitan "poomismängu", kus kontrollin sisestust ja teen muud vajalikud toimingud.
        täheSisestusField.setOnAction(e -> alustaMänguga());


        peaLava.setTitle("Poomismäng");
        peaLava.setScene(avalehtStseen);
        peaLava.show();

    }
    public void mänguRestart() throws IOException {


        if(onEsimeneKord){ // On esimene mängukord.

            Pane sõnaJooned = new Pane();
            arvamisJoonteChildren = sõnaJooned.getChildren();

            borderPane.setBottom(sõnaJooned);
            BorderPane.setMargin(sõnaJooned, new Insets(12, 12, 100, 12));

            // Genereerin juhuslikult arvatava sõna
            ArvatavSõna = getWord();
            ArvatavSõna = ArvatavSõna.toLowerCase();

            // Sätin paika arvamise korrad, enne kui kaotatakse ja kõik muu nö "default" väärtusteks.
            arvamisiAlles = 10;
            arvamisiAllesLabel.setText(String.valueOf(arvamisiAlles));

            indeks = 0;

            arvatudTähtedeArv = 0;

            täheSisestusField.setText("");

            arvatudTähedField.setText("");

            tagasiside.setText("");

            // Sätin paika arvamise jooned ning nende peal olevad tähed.
            graafika.arvamiseJooned(ArvatavSõna);
            graafika.paigutaTekst(ArvatavSõna);

            // Joonistan poomispuu.
            graafika.joonistaPoomine();

            System.out.println(ArvatavSõna);

            onEsimeneKord = false;
            return;
        }

        // Pole esimene mängukord.

        // Kustutan eelmise mängu poomispuu osad.
        for (Shape kujund : poomisPuuList) {
            kujund.setVisible(false);
        }

        // Kustutan eelmise mängu ära arvatud tähed.
        try {
            for (int i = 0; i < 15; i++) {
                if(pakutavSõna[i].isVisible()) {
                    pakutavSõna[i].setVisible(false);
                }
            }
        }catch (IndexOutOfBoundsException ignored) {}

        // Genereerin juhuslikult uue arvatava sõna.
        ArvatavSõna = getWord();
        ArvatavSõna = ArvatavSõna.toLowerCase();

        // Eemaldan eelmise mängu sõna arvamis jooned ja tähed.
        arvamisJoonteChildren.removeAll();
        borderPane.getChildren().remove(borderPane.getBottom());

        // Lisan uue mängu sõna arvamis jooned ja nende peal olevad tähed.
        Pane sõnaJooned = new Pane();

        arvamisJoonteChildren = sõnaJooned.getChildren();

        borderPane.setBottom(sõnaJooned);
        BorderPane.setMargin(sõnaJooned, new Insets(12, 12, 100, 12));

        graafika.arvamiseJooned(ArvatavSõna);
        graafika.paigutaTekst(ArvatavSõna);

        // Sätin paika arvamise korrad, enne kui kaotatakse ja muude elementide nö "default" väärtused.
        arvamisiAlles = 10;
        arvamisiAllesLabel.setText(String.valueOf(arvamisiAlles));

        indeks = 0;

        arvatudTähtedeArv = 0;

        täheSisestusField.setText("");
        arvatudTähedField.setText("");
        tagasiside.setText("");

        // Lasen mängijal uuesti pakkumisi teha.
        täheSisestusField.setEditable(true);

        System.out.println(ArvatavSõna);

    }

    public Background Taust1() {
        Image image = new Image("https://skyteach.ru/wp-content/uploads/2019/12/Hangman.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(900);
        imageView.setFitHeight(550);
        BackgroundImage taustapilt= new BackgroundImage(imageView.snapshot(null, null), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        return new Background(taustapilt);
    }

    public static String getWord() throws IOException {
        List<String> words = Files.readAllLines(Paths.get("sõnad.txt"));
        return words.get(new Random().nextInt(words.size()));
    }

    // Meetod, mis kontrollib mängija sisestust ja teeb vajalikud muudatused.
    private void alustaMänguga() {


        String pakkumineString = täheSisestusField.getText();

        // Teen täheSisestusFieldi tühjaks
        täheSisestusField.setText("");

        // Kontrollin, kas mängija sisestas ainult ühe tähe.
        if (pakkumineString.length() > 1) {
            tagasiside.setText("Palun paku ainult ÜKS täht!");
            return;
        }

        char pakkumine = pakkumineString.charAt(0);

        // Kontrollin, kas on esimene legaalne pakkumine.
        if (arvatudTähedField.getText().equals("")) { // On esimene pakkumine.
            arvatudTähedField.setText(pakkumineString);

        } else { // Ei ole esimene pakkumine.
            String ajutine = arvatudTähedField.getText();
            arvatudTähedField.setText(ajutine + ", " + pakkumineString);

        }

        // Kontrollin, kas mängija pakkumine esineb otsitavas sõnas.
        if (ArvatavSõna.indexOf(pakkumine) != -1) { // Esineb otsitavas sõnas.

            tagasiside.setText("Tubli! Pakutud täht: " + pakkumine + " esines otsitavas sõnas!");

        } else { // Ei esine otsitavas sõnas.

            tagasiside.setText("Pakutud täht " + pakkumine + " ei esinenud otsitavas sõnas.");

            // Panen järgmise poomispuu osa nähtavaks.
            poomisPuuList.get(indeks).setVisible(true);
            indeks++;
            // Muudan arvamisiAllesLabeli väärtust vähemaks.
            arvamisiAlles--;
            arvamisiAllesLabel.setText(String.valueOf(arvamisiAlles));

            // Kui arvamised on otsa saanud, siis mäng on läbi!
            if (arvamisiAlles == 0) {
                tagasiside.setText("Tegid 10 viga. Oled kaotanud! Arvatav sõna oli: "+ ArvatavSõna);
                täheSisestusField.setEditable(false);

            }
            return;
        }

        // Kontrollin, millisel kohal sõnas esines mängija pakkumine ning seejärel vahetan pakutavas sõnas vastava tähe.
        try {
            for (int i = 0; i < ArvatavSõna.length(); i++) {
                if (ArvatavSõna.charAt(i) == pakkumine) {
                    pakutavSõna[i].setVisible(true);
                    arvatudTähtedeArv++;
                }
            }
        } catch (IndexOutOfBoundsException ignored) {}

        if(arvatudTähtedeArv == ArvatavSõna.length()){
            tagasiside.setText("Arvasid sõna ära! See sõna oli: " + ArvatavSõna);
            täheSisestusField.setEditable(false);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}