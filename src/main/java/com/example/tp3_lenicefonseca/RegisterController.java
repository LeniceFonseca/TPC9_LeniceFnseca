package com.example.tp3_lenicefonseca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private Button btnsignin;
    @FXML
    private Button btnsignup;
    @FXML
    private Button btnclearall;
    @FXML
    private ComboBox<String> cbcategoria;
    @FXML
    private TextField tfalcunha;
    @FXML
    private TextField tfapelido;
    @FXML
    private TextField tfbiografia;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfnome;
    @FXML
    private TextField tfnomeusuario;
    @FXML
    private TextField tfpseudonimo;
    @FXML
    private TextField tfsenha;
    @FXML
    private Label warninglabel;


    public void login(ActionEvent e) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage stage = (Stage) btnsignin.getScene().getWindow();
        stage.setScene(new Scene(root, 1300,900));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbcategoria.getItems().addAll("Estudante", "Professor", "Funcionario", "Ex Aluno");
    }

    public void clearAll() {
        tfalcunha.setText(null);
        tfapelido.setText(null);
        tfbiografia.setText(null);
        tfemail.setText(null);
        tfnome.setText(null);
        tfnomeusuario.setText(null);
        tfpseudonimo.setText(null);
        tfsenha.setText(null);
    }

    public void createAccount() {

        String sql = "INSERT INTO usuarios(nome, apelido, email, username, password, categoria, alcunha, pseudonimo, biografia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;

        try{

            preparedStatement = Conexao.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, tfnome.getText());
            preparedStatement.setString(2, tfapelido.getText());
            preparedStatement.setString(3, tfemail.getText());
            preparedStatement.setString(4, tfnomeusuario.getText());
            preparedStatement.setString(5, tfsenha.getText());
            preparedStatement.setString(6, cbcategoria.getValue());
            preparedStatement.setString(7, tfalcunha.getText());
            preparedStatement.setString(8, tfpseudonimo.getText());
            preparedStatement.setString(9, tfbiografia.getText());

            preparedStatement.execute();
            preparedStatement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void warningMessage(ActionEvent e) {
        if (
                tfnomeusuario.getText().isBlank() == false && tfalcunha.getText().isBlank() == false && tfsenha.getText().isBlank() == false
                        && tfpseudonimo.getText().isBlank() == false && tfnome.getText().isBlank() == false && tfemail.getText().isBlank() == false
                        && tfapelido.getText().isBlank() == false && cbcategoria.getValue().isBlank() == false
        ) {
            createAccount();
            Parent root = null;
            try {

                root = FXMLLoader.load(getClass().getResource("home.fxml"));
                Stage stage = (Stage) btnsignin.getScene().getWindow();
                stage.setScene(new Scene(root, 1300,900));
                stage.show();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else {
            warninglabel.setText("Todos os campos (excepto biografia) devem ser preenchidos!");
        }

    }


}
