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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class graafilineMäng extends Application {
    Scene avalehtStseen;
    Scene mängStseen;

    private TextField täheSisestus = new TextField();
    private TextField arvatudTähed = new TextField();
    //Dictionary word = new Dictionary();

    // JavaFX Text elements to display the letters
    // Stored in an array to make them easily visible
    private Text[] text;

    private Label arvamisiAllesLabel;

    private int arvamisiAlles;

    private String ArvatavSõna;
    private ObservableList<Node> children;

    private StringBuilder guessedLetters;

    public Background Taust1(){

        BackgroundImage taustapilt= new BackgroundImage(new Image("https://t4.ftcdn.net/jpg/02/48/96/11/360_F_248961156_XeSISXFo6bcFUw830wpE2zPLxWGCl1u9.jpg", avalehtStseen.getWidth(), avalehtStseen.getHeight(),false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background taust = new Background(taustapilt);



        return taust;
    }

    private void arvamiseJooned(String word) {
        Line[] jooned = new Line[word.length()];
        int xStart = 375;
        int lineLength = 25;
        int lineSpacing = 35;
        for (int i = 0; i < jooned.length; i++) {
            // Calculate the starting point of the line segment
            int xcoord = xStart + (lineSpacing * i);
            // create the line
            jooned[i] = new Line(xcoord, 225, xcoord - lineLength, 225);
            jooned[i].setStroke(Color.BLACK);
            jooned[i].setStrokeWidth(3);
            children.add(jooned[i]);
        }
    }


    @Override
    public void start(Stage peaLava){
        // Stseen 1

        StackPane stackPane1 = new StackPane();

        avalehtStseen = new Scene(stackPane1, 800, 650);

        Label label1 = new Label("Esimene stseen");

        Button button1 = new Button("Alusta mänguga!");
        button1.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0; -fx-background-radius: 8; -fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #9b9d9e 0%, #7a7c7d 100%),#7a7c7d,#9b9d9e, radial-gradient(center 50% 50%, radius 100%, #7a7c7d, #9b9d9e); -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 ); -fx-font-weight: bold; -fx-font-size: 1.3em;");
        button1.setOnAction(e -> peaLava.setScene(mängStseen));

        StackPane.setAlignment(button1, Pos.BOTTOM_CENTER);
        StackPane.setMargin(button1, new Insets(25,25,50,25));

        ObservableList<javafx.scene.Node> list1 = stackPane1.getChildren();

        list1.addAll(label1, button1);


        stackPane1.setBackground(Taust1());







////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Stseen 2


        StackPane stackPane2 = new StackPane();

        Label label2 = new Label("Teine stseen");

        Button button2 = new Button("Mine esimesse stseeni");
        button2.setOnAction(e -> peaLava.setScene(avalehtStseen));
        button2.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0; -fx-background-radius: 8; -fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #9b9d9e 0%, #7a7c7d 100%),#7a7c7d,#9b9d9e, radial-gradient(center 50% 50%, radius 100%, #7a7c7d, #9b9d9e); -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 ); -fx-font-weight: bold; -fx-font-size: 1.3em;");


        stackPane2.setMargin(button2, new Insets(50, 50, 50, 50));
        stackPane2.setAlignment(button2, Pos.BOTTOM_CENTER);

        ObservableList<javafx.scene.Node> list = stackPane2.getChildren();

        list.addAll(label2, button2);


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Pane pane = new Pane();

        // Salvestan childreni, et seda kergesti kätte saada
        children = pane.getChildren();
        // Arvama hakatav sõna
        ArvatavSõna = "Katse";

        // Initialize the guessed Letters StringBuilder
        guessedLetters = new StringBuilder();

        // Sätin paika arvamise jooned
        arvamiseJooned(ArvatavSõna);
        System.out.println(ArvatavSõna);

        // initialize the word drawing
        //text = initText(ArvatavSõna);

        // Sätin paika arvamise korrad, enne kui kaotatakse
        arvamisiAlles = 10;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(new Label("Sisesta täht:"), 0, 0);
        gridPane.add(täheSisestus, 1, 0);
        gridPane.add(new Label("Arvatud tähed:"), 0, 1);
        gridPane.add(arvatudTähed, 1, 1);
        gridPane.add(new Label("Arvamisi jäänud: "), 0, 2);
        arvamisiAllesLabel = new Label(String.valueOf(arvamisiAlles));
        gridPane.add(arvamisiAllesLabel, 0, 3);

        BorderPane borderPane = new BorderPane();
        borderPane.setRight(gridPane);

        borderPane.setCenter(pane);

        borderPane.setLeft(stackPane2);
        //tfGuess.setOnAction(e -> playGame());

        mängStseen = new Scene(borderPane, 800, 650);


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Muud asjad

        peaLava.setTitle("Poomismäng");
        peaLava.setScene(avalehtStseen);
        //peaLava.setResizable(false);
        peaLava.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
