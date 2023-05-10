package com.example.PoomismängGraafika;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class graafika extends graafilineMäng{


    static void joonistaPoomine() {

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

    static void arvamiseJooned(String sõna) {
        Line[] jooned = new Line[sõna.length()];
        int joonePikkus = 25;
        int jooneVahe = 35;
        int xAlgus = (int) mängStseen.getWidth() / 2 - jooneVahe * (sõna.length() / 2);

        for (int i = 0; i < jooned.length; i++) {
            // Arvutan iga joone alguspunkti.
            int xkoordinaat = xAlgus + (jooneVahe * i);

            jooned[i] = new Line(xkoordinaat, 230, xkoordinaat - joonePikkus, 230);
            jooned[i].setStroke(Color.BLACK);
            jooned[i].setStrokeWidth(3);
            arvamisJoonteChildren.add(jooned[i]);
        }
    }

    static Text[] paigutaTekst(String sõna) {
        pakutavSõna = new Text[sõna.length()];
        int jooneVahe = 35;
        int joonePikkus = 25;
        int xAlgus = (int) mängStseen.getWidth() / 2 - jooneVahe * (sõna.length() / 2) - joonePikkus / 2 - 10;

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



}
