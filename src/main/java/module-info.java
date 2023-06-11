module com.example.tpc9_lenicefnseca {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.tp3_lenicefonseca to javafx.fxml;
    exports com.example.tp3_lenicefonseca;


}