package com.example.tpc9_lenicefnseca;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HomeController {

    @FXML
    private Button btnmeuperfil;

    @FXML
    private Button btnpesquisar;

    @FXML
    private Button btnsignoff;

    @FXML
    private Label lbnomeeapelido;

    @FXML
    private TextField tfpesquisar;

    LoginController loginController = new LoginController();

    public HomeController() {
    }

    public void mostrarNome() {

        Conexao connectNow = new Conexao();
        Connection connectDB = connectNow.getConnection();

        try {
            String sql = "SELECT nome FROM usuarios WHERE username = '" + loginController.getTfusername() + "' AND password = '" + loginController.getPfpassword() + "';";
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(sql);

            while (queryResult.next()) {
                lbnomeeapelido.setText(queryResult.getString("nome") + " " + queryResult.getString("apelido"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

