module com.example.graafilinekatse {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.PoomismängGraafika to javafx.fxml;
    exports com.example.PoomismängGraafika;
}