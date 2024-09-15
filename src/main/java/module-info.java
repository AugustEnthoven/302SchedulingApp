module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.demo to javafx.fxml;
    exports org.example.demo;
}