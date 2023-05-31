module com.example.tpc9_lenicefnseca {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.tpc9_lenicefnseca to javafx.fxml;
    exports com.example.tpc9_lenicefnseca;


}