module ui.ui {
    requires javafx.controls;
    requires javafx.fxml;


    exports ui;
    opens ui to javafx.fxml;
}