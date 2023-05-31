package com.example.tpc9_lenicefnseca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private TextField tfusername;
    @FXML
    private PasswordField pfpassword;
    @FXML
    private TextField linkregister;

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
            String verifyLogin = "SELECT count(1) FROM usuarios WHERE username = '" + tfusername.getText() + "' AND password = '" + pfpassword.getText() + "'";


            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);


            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    warninglabel.setText("Welcome miss " + tfusername.getText());
                }
                else {
                    warninglabel.setText("Invalid username/password");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }




    }


}