package com.example.PoomismängGraafika;

import javafx.animation.FadeTransition;
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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class graafilineMäng extends Application {
    Scene avalehtStseen;
    Scene mängStseen;

    private TextField täheSisestusField = new TextField();

    private TextField arvatudTähedField = new TextField();

    private TextField tagasiside = new TextField();

    private FadeTransition tagasisideNäitamine;

    private Label arvamisiAllesLabel;

    private int arvamisiAlles;

    private String ArvatavSõna;
    private Text[] pakutavSõna;

    private ObservableList<Node> arvamisJoonteChildren;
    private ObservableList<Node> poomisPuuChildren;


    // Siin ArrayListis on kõik erinevad poomispuu osad.
    private ArrayList<Shape> poomisPuuList;

    private int indeks = 0;




    @Override
    public void start(Stage peaLava) throws IOException {
        // Stseen 1

        StackPane stackPane1 = new StackPane();

        avalehtStseen = new Scene(stackPane1, 1000, 650);

        Label label1 = new Label("Esimene stseen");

        Button button1 = new Button("Alusta mänguga!");
        button1.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0; -fx-background-radius: 8; -fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #9b9d9e 0%, #7a7c7d 100%),#7a7c7d,#9b9d9e, radial-gradient(center 50% 50%, radius 100%, #7a7c7d, #9b9d9e); -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 ); -fx-font-weight: bold; -fx-font-size: 1.3em;");
        button1.setOnAction(e -> peaLava.setScene(mängStseen));

        StackPane.setAlignment(button1, Pos.BOTTOM_CENTER);
        StackPane.setMargin(button1, new Insets(25, 25, 50, 25));

        ObservableList<javafx.scene.Node> list1 = stackPane1.getChildren();

        list1.addAll(label1, button1);


        stackPane1.setBackground(Taust1());


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Stseen 2

        BorderPane borderPane = new BorderPane();
        mängStseen = new Scene(borderPane, 1000, 650);


        // Vasakpoolne osa
        StackPane stackPane2 = new StackPane();

        Label label2 = new Label("Teine stseen");

        Button button2 = new Button("Mine esimesse stseeni");
        button2.setOnAction(e -> peaLava.setScene(avalehtStseen));
        button2.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0; -fx-background-radius: 8; -fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #9b9d9e 0%, #7a7c7d 100%),#7a7c7d,#9b9d9e, radial-gradient(center 50% 50%, radius 100%, #7a7c7d, #9b9d9e); -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 ); -fx-font-weight: bold; -fx-font-size: 1.3em;");


        StackPane.setMargin(button2, new Insets(50, 50, 50, 50));
        StackPane.setAlignment(button2, Pos.BOTTOM_CENTER);

        ObservableList<javafx.scene.Node> list = stackPane2.getChildren();

        list.addAll(label2, button2);


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Ettevalmistus / parempoolne osa.
        Pane sõnaJooned = new Pane();
        Pane poomisPuu = new Pane();

        // Salvestan childreni, et seda kergesti kätte saada
        arvamisJoonteChildren = sõnaJooned.getChildren();
        poomisPuuChildren = poomisPuu.getChildren();

        // Genereerin juhuslikult arvatava sõna
        ArvatavSõna = getWord();
        ArvatavSõna = ArvatavSõna.toLowerCase();

        // Ei lase mängijal juba pakutud tähtede lahtrisse midagi sisestada.
        arvatudTähedField.setEditable(false);


        // Sätin paika arvamise jooned.
        arvamiseJooned(ArvatavSõna);
        // Sätin paika arvamise tähed.
        paigutaTekst(ArvatavSõna);

        System.out.println(ArvatavSõna);

        // Joonistan poomispuu
        joonistaPoomine();

        // Sätin paika arvamise korrad, enne kui kaotatakse
        arvamisiAlles = 10;

        // Sätin paika tagasiside textfieldi
        tagasiside.setFont(new Font(20));
        tagasiside.setPrefWidth(400);
        tagasiside.setAlignment(Pos.CENTER);
        tagasiside.setEditable(false);
        tagasiside.setVisible(true);
        borderPane.setTop(tagasiside);

        // Sätin paika tagasiside näitamise
        //tagasisideNäitamine = new FadeTransition(Duration.millis(4000), tagasiside);  // luuakse uus haihtumine
        //tagasisideNäitamine.setFromValue(1.0);  // määratakse algväärtus (1.0 - täiesti selge)
        //tagasisideNäitamine.setToValue(1.0); // määratakse lõppväärtus (0 - täiesti haihtunud)

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Parempoolne osa.
        GridPane paremPoolsedAsjad = new GridPane();
        paremPoolsedAsjad.setHgap(10);
        paremPoolsedAsjad.setVgap(10);
        paremPoolsedAsjad.add(new Label("Sisesta täht:"), 0, 0);
        paremPoolsedAsjad.add(täheSisestusField, 1, 0);
        paremPoolsedAsjad.add(new Label("Arvatud tähed:"), 0, 1);
        paremPoolsedAsjad.add(arvatudTähedField, 1, 1);
        paremPoolsedAsjad.add(new Label("Valesid pakkumisi jäänud: "), 0, 2);
        arvamisiAllesLabel = new Label(String.valueOf(arvamisiAlles));
        paremPoolsedAsjad.add(arvamisiAllesLabel, 0, 3);


        borderPane.setRight(paremPoolsedAsjad);
        paremPoolsedAsjad.setAlignment(Pos.CENTER);

        borderPane.setBottom(sõnaJooned);
        //borderPane.setAlignment(sõnaJooned, Pos.CENTER);
        BorderPane.setMargin(sõnaJooned, new Insets(12, 12, 100, 12));

        borderPane.setCenter(poomisPuu);

        //borderPane.setTop(stackPane2);


        // Kui sisestada täht, siis käivitan "poomismängu", kus kontrollin sisestust ja teen muud vajalikud toimingud.
        täheSisestusField.setOnAction(e -> algustaMänguga());


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Muud asjad

        peaLava.setTitle("Poomismäng");
        peaLava.setScene(avalehtStseen);
        //peaLava.setResizable(false);
        peaLava.show();

    }

    private void joonistaPoomine() {

        poomisPuuList = new ArrayList<Shape>();

        Line puu1 = new Line(300, 400, 25, 400);
        puu1.setStroke(Color.BROWN);
        puu1.setStrokeWidth(3);
        puu1.setVisible(false);
        poomisPuuChildren.add(puu1);
        poomisPuuList.add(puu1);

        Line puu2 = new Line(25, 25, 25, 400);
        puu2.setStroke(Color.BROWN);
        puu2.setStrokeWidth(3);
        puu2.setVisible(false);
        poomisPuuChildren.add(puu2);
        poomisPuuList.add(puu2);

        Line puu3 = new Line(25, 25, 200, 25);
        puu3.setStroke(Color.BROWN);
        puu3.setStrokeWidth(3);
        puu3.setVisible(false);
        poomisPuuChildren.add(puu3);
        poomisPuuList.add(puu3);

        Line nöör = new Line(200, 25, 200, 75);
        nöör.setStroke(Color.BROWN);
        nöör.setStrokeWidth(3);
        nöör.setVisible(false);
        poomisPuuChildren.add(nöör);
        poomisPuuList.add(nöör);

        Ellipse pea = new Ellipse(200, 112, 35, 35);
        pea.setStroke(Color.BLACK);
        pea.setFill(Color.WHITE);
        pea.setStrokeWidth(5);
        pea.setVisible(false);
        poomisPuuChildren.add(pea);
        poomisPuuList.add(pea);

        Line torso = new Line(200, 200, 200, 150);
        torso.setStroke(Color.BLACK);
        torso.setStrokeWidth(5);
        torso.setVisible(false);
        poomisPuuChildren.add(torso);
        poomisPuuList.add(torso);

        Line vasakKäsi = new Line(150, 225, 200, 175);
        vasakKäsi.setStroke(Color.BLACK);
        vasakKäsi.setStrokeWidth(5);
        vasakKäsi.setVisible(false);
        poomisPuuChildren.add(vasakKäsi);
        poomisPuuList.add(vasakKäsi);

        Line paremKäsi = new Line(250, 225, 200, 175);
        paremKäsi.setStroke(Color.BLACK);
        paremKäsi.setStrokeWidth(5);
        paremKäsi.setVisible(false);
        poomisPuuChildren.add(paremKäsi);
        poomisPuuList.add(paremKäsi);

        Line vasakJalg = new Line(200, 200, 175, 275);
        vasakJalg.setStroke(Color.BLACK);
        vasakJalg.setStrokeWidth(5);
        vasakJalg.setVisible(false);
        poomisPuuChildren.add(vasakJalg);
        poomisPuuList.add(vasakJalg);

        Line paremJalg = new Line(200, 200, 225, 275);
        paremJalg.setStroke(Color.BLACK);
        paremJalg.setStrokeWidth(5);
        paremJalg.setVisible(false);
        poomisPuuChildren.add(paremJalg);
        poomisPuuList.add(paremJalg);

    }

    public Background Taust1() {

        BackgroundImage taustapilt = new BackgroundImage(new Image("https://t4.ftcdn.net/jpg/02/48/96/11/360_F_248961156_XeSISXFo6bcFUw830wpE2zPLxWGCl1u9.jpg", avalehtStseen.getWidth(), avalehtStseen.getHeight(), false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        return new Background(taustapilt);
    }

    public void arvamiseJooned(String sõna) {
        Line[] jooned = new Line[sõna.length()];
        //double laius = mängStseen.getWidth();
        int joonePikkus = 25;
        int jooneVahe = 35;
        int xAlgus = (int) mängStseen.getWidth() / 2 - jooneVahe * (sõna.length() / 2);

        for (int i = 0; i < jooned.length; i++) {
            // Arvutan iga joone alguspunkti.
            int xkoordinaat = xAlgus + (jooneVahe * i);

            jooned[i] = new Line(xkoordinaat, 225, xkoordinaat - joonePikkus, 225);
            jooned[i].setStroke(Color.BLACK);
            jooned[i].setStrokeWidth(3);
            arvamisJoonteChildren.add(jooned[i]);
        }
    }

    //TODO: Peab näitama arvatud tähti joone peal. Seotud selle meetodiga.
    private Text[] paigutaTekst(String sõna) {
        pakutavSõna = new Text[sõna.length()];
        int jooneVahe = 35;
        int xAlgus = (int) mängStseen.getWidth() / 2 - jooneVahe * (sõna.length() / 2);

        for (int i = 0; i < pakutavSõna.length; i++) {
            // Arvutan iga joone alguspunkti.
            int xkoordinaat = xAlgus + (jooneVahe * i);
            pakutavSõna[i] = new Text(sõna.substring(i, i + 1));
            pakutavSõna[i].setFont(new Font(30));
            pakutavSõna[i].setX(xkoordinaat);
            pakutavSõna[i].setY(220);
            pakutavSõna[i].setVisible(false);
            arvamisJoonteChildren.add(pakutavSõna[i]);
        }
        return pakutavSõna;
    }

    public static String getWord() throws IOException {
        List<String> words = Files.readAllLines(Paths.get("sõnad.txt"));
        return words.get(new Random().nextInt(words.size()));
    }

    private void algustaMänguga() {

        System.out.println("jõudsin siia!!!");

        String pakkumineString = täheSisestusField.getText();
        täheSisestusField.setText("");
        // Kontrollin, kas mängija sisestas ainult ühe tähe.
        if (pakkumineString.length() > 1) {
            System.out.println("Palun paku ainult ÜKS täht!");

            tagasiside.setText("Palun paku ainult ÜKS täht!");
            //tagasisideNäitamine.play();

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
            //System.out.println("Tubli! Pakutud täht: " + pakkumine + " esines otsitavas sõnas!");
            //TODO: Kirjuta ekraanile sama asi.

            tagasiside.setText("Tubli! Pakutud täht: " + pakkumine + " esines otsitavas sõnas!");
            //tagasisideNäitamine.play();

            //arvamisiAlles--;
            arvamisiAllesLabel.setText(String.valueOf(arvamisiAlles));
        } else { // Ei esine otsitavas sõnas.
            System.out.println("Pakutud täht ei esinenud otsitavas sõnas.");
            //TODO: Kirjuta ekraanile sama asi.

            tagasiside.setText("Pakutud täht " + pakkumine + " ei esinenud otsitavas sõnas.");
            //tagasisideNäitamine.play();

            poomisPuuList.get(indeks).setVisible(true);
            indeks++;
            arvamisiAlles--;
            arvamisiAllesLabel.setText(String.valueOf(arvamisiAlles));
            // Kui arvamised on otsa saanud, siis mäng on läbi!
            if (arvamisiAlles == 0) {

                // TODO: Kaotasid!
                System.out.println("\nTegid üle 10 vea. Oled kaotanud!");
                System.out.println("Arvatav sõna oli: " + ArvatavSõna);
                System.out.println("Järgmine kord läheb paremini!");
            }
            return;
        }

        try { // kontrollin, millisel kohal sõnas esines mängija pakkumine ning seejärel vahetan pakutavas sõnas vastava tähe.
            if (ArvatavSõna.charAt(0) == pakkumine) {
                String pakutav = Arrays.toString(pakutavSõna);
                String uusSõna = pakkumine + pakutav.substring(1);
                pakutavSõna = new Text[]{new Text(uusSõna)};
                pakutavSõna[0] = new Text(String.valueOf(pakkumine));
            }
            if (ArvatavSõna.charAt(1) == pakkumine) {
                String pakutav = Arrays.toString(pakutavSõna);
                String uusSõna = pakutav.substring(0, 1) + pakkumine + pakutav.substring(2);
                pakutavSõna = new Text[]{new Text(uusSõna)};
                pakutavSõna[1] = new Text(String.valueOf(pakkumine));
            }
            if (ArvatavSõna.charAt(2) == pakkumine) {
                String pakutav = Arrays.toString(pakutavSõna);
                String uusSõna = pakutav.substring(0, 2) + pakkumine + pakutav.substring(3);
                pakutavSõna = new Text[]{new Text(uusSõna)};
                pakutavSõna[2] = new Text(String.valueOf(pakkumine));
            }
            if (ArvatavSõna.charAt(3) == pakkumine) {
                String pakutav = Arrays.toString(pakutavSõna);
                String uusSõna = pakutav.substring(0, 3) + pakkumine + pakutav.substring(4);
                pakutavSõna = new Text[]{new Text(uusSõna)};
                pakutavSõna[3] = new Text(String.valueOf(pakkumine));
            }
            if (ArvatavSõna.charAt(4) == pakkumine) {
                String pakutav = Arrays.toString(pakutavSõna);
                String uusSõna = pakutav.substring(0, 4) + pakkumine + pakutav.substring(5);
                pakutavSõna = new Text[]{new Text(uusSõna)};
                pakutavSõna[4] = new Text(String.valueOf(pakkumine));
            }
            if (ArvatavSõna.charAt(5) == pakkumine) {
                String pakutav = Arrays.toString(pakutavSõna);
                String uusSõna = pakutav.substring(0, 5) + pakkumine + pakutav.substring(6);
                pakutavSõna = new Text[]{new Text(uusSõna)};
                pakutavSõna[5] = new Text(String.valueOf(pakkumine));
            }
            if (ArvatavSõna.charAt(6) == pakkumine) {
                String pakutav = Arrays.toString(pakutavSõna);
                String uusSõna = pakutav.substring(0, 6) + pakkumine + pakutav.substring(7);
                pakutavSõna = new Text[]{new Text(uusSõna)};
                pakutavSõna[6] = new Text(String.valueOf(pakkumine));
            }
            if (ArvatavSõna.charAt(7) == pakkumine) {
                String pakutav = Arrays.toString(pakutavSõna);
                String uusSõna = pakutav.substring(0, 7) + pakkumine + pakutav.substring(8);
                pakutavSõna = new Text[]{new Text(uusSõna)};
                pakutavSõna[7] = new Text(String.valueOf(pakkumine));
            }
            if (ArvatavSõna.charAt(8) == pakkumine) {
                String pakutav = Arrays.toString(pakutavSõna);
                String uusSõna = pakutav.substring(0, 8) + pakkumine + pakutav.substring(9);
                pakutavSõna = new Text[]{new Text(uusSõna)};
                pakutavSõna[8] = new Text(String.valueOf(pakkumine));
            }
            if (ArvatavSõna.charAt(9) == pakkumine) {
                String pakutav = Arrays.toString(pakutavSõna);
                String uusSõna = pakutav.substring(0, 9) + pakkumine + pakutav.substring(10);
                pakutavSõna = new Text[]{new Text(uusSõna)};
                pakutavSõna[9] = new Text(String.valueOf(pakkumine));
            }
            if (ArvatavSõna.charAt(10) == pakkumine) {
                String pakutav = Arrays.toString(pakutavSõna);
                String uusSõna = pakutav.substring(0, 10) + pakkumine + pakutav.substring(11);
                pakutavSõna = new Text[]{new Text(uusSõna)};
                pakutavSõna[10] = new Text(String.valueOf(pakkumine));
            }
            if (ArvatavSõna.charAt(11) == pakkumine) {
                String pakutav = Arrays.toString(pakutavSõna);
                String uusSõna = pakutav.substring(0, 11) + pakkumine + pakutav.substring(12);
                pakutavSõna = new Text[]{new Text(uusSõna)};
                pakutavSõna[11] = new Text(String.valueOf(pakkumine));
            }
            if (ArvatavSõna.charAt(12) == pakkumine) {
                String pakutav = Arrays.toString(pakutavSõna);
                String uusSõna = pakutav.substring(0, 12) + pakkumine + pakutav.substring(13);
                pakutavSõna = new Text[]{new Text(uusSõna)};
                pakutavSõna[12] = new Text(String.valueOf(pakkumine));
            }
            if (ArvatavSõna.charAt(13) == pakkumine) {
                String pakutav = Arrays.toString(pakutavSõna);
                String uusSõna = pakutav.substring(0, 13) + pakkumine + pakutav.substring(14);
                pakutavSõna = new Text[]{new Text(uusSõna)};
                pakutavSõna[13] = new Text(String.valueOf(pakkumine));
            }
            if (ArvatavSõna.charAt(14) == pakkumine) {
                String pakutav = Arrays.toString(pakutavSõna);
                String uusSõna = pakutav.substring(0, 14) + pakkumine + pakutav.substring(15);
                Text[] ajutine = new Text[]{new Text(uusSõna)};
                pakutavSõna = ajutine;
                pakutavSõna[14] = new Text(String.valueOf(pakkumine));
            }
            //TODO: Uuenda seda joonte peal olevat sõna, et tähti oleks näha

        } catch (IndexOutOfBoundsException ignored) {
        }


    }

    public static void main(String[] args) {
        launch(args);
    }

}