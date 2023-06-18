package com.example.tp3_lenicefonseca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ResultadoPesquisaController implements Initializable {

    @FXML
    private Button btnperfil;


    @FXML
    private Button voltar;

    @FXML
    private Circle circle;

    @FXML
    private Label username;


    @FXML
    private VBox vbox;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setText(HomeController.pesquisar);
        String sql = "SELECT username FROM usuarios WHERE username = ?";
        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = Conexao.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, HomeController.pesquisar);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                username.setText(resultSet.getString("username"));
            }

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql1 = "SELECT fotoperfil FROM fotos WHERE nome = ?";
        PreparedStatement preparedStatement1 = null;

        try {

            preparedStatement1 = Conexao.getConnection().prepareStatement(sql1);
            preparedStatement1.setString(1, HomeController.pesquisar);

            ResultSet resultSet1 = preparedStatement1.executeQuery();

            while (resultSet1.next()) {
                Image img = new Image(resultSet1.getString("fotoperfil"));
                circle.setFill(new ImagePattern(img));
            }

            preparedStatement1.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage stage = (Stage) voltar.getScene().getWindow();
        stage.setScene(new Scene(root, 1300,900));
        stage.show();
    }

    @FXML
    void perfil(ActionEvent event) {

    }

}
