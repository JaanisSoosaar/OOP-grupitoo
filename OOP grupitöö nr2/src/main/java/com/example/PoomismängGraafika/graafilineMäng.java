package com.example.PoomismängGraafika;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class graafilineMäng extends Application {
    Scene Avaleht;
    Scene poomisMäng;

    @Override
    public void start(Stage peaLava){

        // Stseen 1
        
        StackPane stackPane1 = new StackPane();
        Avaleht = new Scene(stackPane1, 800, 650);


        Label label1 = new Label("Esimene stseen");
        Button button1 = new Button("Alusta mänguga!");
        button1.setStyle("-fx-padding: 8 15 15 15; -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0; -fx-background-radius: 8; -fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #9b9d9e 0%, #7a7c7d 100%),#7a7c7d,#9b9d9e, radial-gradient(center 50% 50%, radius 100%, #7a7c7d, #9b9d9e); -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 ); -fx-font-weight: bold; -fx-font-size: 1.3em;");

        button1.setOnAction(e -> peaLava.setScene(poomisMäng));

        StackPane.setAlignment(button1, Pos.BOTTOM_CENTER);
        StackPane.setMargin(button1, new Insets(25,25,50,25));

        ObservableList<javafx.scene.Node> list1 = stackPane1.getChildren();

        list1.addAll(label1, button1);

        BackgroundImage taustapilt= new BackgroundImage(new Image("https://t4.ftcdn.net/jpg/02/48/96/11/360_F_248961156_XeSISXFo6bcFUw830wpE2zPLxWGCl1u9.jpg", Avaleht.getWidth(), Avaleht.getHeight(),false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background taust = new Background(taustapilt);

        stackPane1.setBackground(taust);



        // Stseen 2

        StackPane stackPane2 = new StackPane();

        poomisMäng = new Scene(stackPane2,800,650);

        Label label2 = new Label("Teine stseen");
        Button button2 = new Button("Mine esimesse stseeni");
        button2.setOnAction(e -> peaLava.setScene(Avaleht));

        stackPane2.setMargin(button2, new Insets(50, 50, 50, 50));
        stackPane2.setAlignment(button2, Pos.BOTTOM_CENTER);

        ObservableList<javafx.scene.Node> list = stackPane2.getChildren();

        list.addAll(label2, button2);





        //Muud asjad
        peaLava.setTitle("Poomismäng");
        peaLava.setScene(Avaleht);
        //peaLava.setResizable(false);
        peaLava.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
