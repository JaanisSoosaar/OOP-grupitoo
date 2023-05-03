module com.example.graafilinekatse {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.graafilinekatse to javafx.fxml;
    exports com.example.graafilinekatse;
}