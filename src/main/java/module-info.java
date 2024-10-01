module org.codecrafters.educa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires password4j; //Import password4j in Project Structure to avoid errors


    opens org.codecrafters.educa to javafx.fxml;
    exports org.codecrafters.educa;
    opens org.codecrafters.educa.components to javafx.fxml;
    exports org.codecrafters.educa.components;
    opens org.codecrafters.educa.profiles to javafx.fxml;
    exports org.codecrafters.educa.profiles;
    exports org.codecrafters.educa.db;
}