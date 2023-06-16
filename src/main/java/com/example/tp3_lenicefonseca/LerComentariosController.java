package com.example.tp3_lenicefonseca;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LerComentariosController implements Initializable {

    @FXML
    private Label comentario;

    @FXML
    private Label dataehora;

    @FXML
    private Circle fotoautor;

    @FXML
    private Label nomeautor;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String sql = "SELECT fotoperfil FROM fotos WHERE nome = ?";
        String sql1 = "SELECT autorcomentario FROM comentarios WHERE post = ?";
        String sql2 = "SELECT datacomentario FROM comentarios WHERE post = ?";
        String sql3 = "SELECT comentario FROM comentarios WHERE post = ?";

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;

        try {

            preparedStatement = Conexao.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, LoginController.pegarnome);

            preparedStatement1 = Conexao.getConnection().prepareStatement(sql1);
            preparedStatement1.setString(1, "caramelo");

            preparedStatement2 = Conexao.getConnection().prepareStatement(sql2);
            preparedStatement2.setString(1, "caramelo");

            preparedStatement3 = Conexao.getConnection().prepareStatement(sql3);
            preparedStatement3.setString(1, "caramelo");

            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            ResultSet resultSet2 = preparedStatement.executeQuery();
            ResultSet resultSet3 = preparedStatement1.executeQuery();

            while (resultSet.next()) {
                Image img = new Image(resultSet.getString("fotoperfil"));
                fotoautor.setFill(new ImagePattern(img));
            }

            while (resultSet1.next()) {
                nomeautor.setText(resultSet1.getString("autorcomentario"));
            }

//            while (resultSet2.next()) {
//                dataehora.setText(resultSet1.getString("datacomentario"));
//            }

//            while (resultSet3.next()) {
//                comentario.setText(resultSet1.getString("comentario"));
//            }

            preparedStatement.execute();
            preparedStatement1.execute();
//            preparedStatement2.execute();
//            preparedStatement3.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
