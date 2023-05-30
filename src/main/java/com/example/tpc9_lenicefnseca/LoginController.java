package com.example.tpc9_lenicefnseca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public void validateLogin() {

        String sql = "SELECT count(1) FROM usuarios WHERE usernam = ? AND password = ?";
        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = Conexao.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            preparedStatement.setString(1, tfusername.getText());
            preparedStatement.setString(2, pfpassword.getText());


            while (resultSet.next()) {
                if (resultSet.getInt(1) == 1) {
                    warninglabel.setText("Welcome!");
                }
                else {
                    warninglabel.setText("Username ou password inválidos!");
                }
            }

            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}