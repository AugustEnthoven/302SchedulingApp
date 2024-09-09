module org.codecrafters.educa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires password4j;


    opens org.codecrafters.educa to javafx.fxml;
    exports org.codecrafters.educa;
}