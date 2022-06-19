module com.dinyad.zelda {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.dinyad.zelda to javafx.fxml;
    exports com.dinyad.zelda;
}