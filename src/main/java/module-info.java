module org.codecrafters.educa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.codecrafters.educa to javafx.fxml;
    exports org.codecrafters.educa;
}