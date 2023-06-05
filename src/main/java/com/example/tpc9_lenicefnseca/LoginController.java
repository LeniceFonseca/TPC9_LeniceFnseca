package com.example.tpc9_lenicefnseca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.*;

public class LoginController {
    @FXML
    private Label warninglabel;
    @FXML
    private Button btnlogin;
    @FXML
    private Button btnregister;
    @FXML
    private TextField tfusername;
    @FXML
    private PasswordField pfpassword;


    public LoginController() {
    }

    @FXML
    public void warningMessage(ActionEvent e) {
        if (tfusername.getText().isBlank() == false && pfpassword.getText().isBlank() == false) {
            validateLogin();
        }
        else {
            warninglabel.setText("Campo username ou password vazios!");
        }

    }

    public void validateLogin(){
        Conexao connectNow = new Conexao();
        Connection connectDB = connectNow.getConnection();

        try{
            String verifyLogin = "SELECT count(1) FROM usuarios WHERE username = '" + tfusername.getText() + "' AND password = '" + pfpassword.getText() + "';";


            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);


            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
                    Stage stage = (Stage) btnlogin.getScene().getWindow();
                    stage.setScene(new Scene(root, 1300,900));
                    stage.show();
                }
                else {
                    warninglabel.setText("Invalid username/password");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void register(ActionEvent e) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("register.fxml"));
        Stage stage = (Stage) btnregister.getScene().getWindow();
        stage.setScene(new Scene(root, 1300,900));
        stage.show();
    }

    public TextField getTfusername() {
        return tfusername;
    }

    public Label getWarninglabel() {
        return warninglabel;
    }

    public void setWarninglabel(Label warninglabel) {
        this.warninglabel = warninglabel;
    }

    public Button getBtnlogin() {
        return btnlogin;
    }

    public void setBtnlogin(Button btnlogin) {
        this.btnlogin = btnlogin;
    }

    public Button getBtnregister() {
        return btnregister;
    }

    public void setBtnregister(Button btnregister) {
        this.btnregister = btnregister;
    }

    public void setTfusername(TextField tfusername) {
        this.tfusername = tfusername;
    }

    public PasswordField getPfpassword() {
        return pfpassword;
    }

    public void setPfpassword(PasswordField pfpassword) {
        this.pfpassword = pfpassword;
    }
}