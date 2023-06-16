package com.example.tp3_lenicefonseca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ComentarController implements Initializable {

    @FXML
    private Button fazercomentario;

    @FXML
    private TextField escrevercommentario;

    @FXML
    void comment(ActionEvent event) {

        String sql = "INSERT INTO comentarios (autorcomentario, datacomentario, post, comentario) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = Conexao.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, LoginController.pegarnome);
            preparedStatement.setString(2, String.valueOf(LocalDateTime.now()));
            preparedStatement.setString(3, "caramelo");
            preparedStatement.setString(4, escrevercommentario.getText());

            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        escrevercommentario.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fazercomentario.setStyle(
                "-fx-background-color: linear-gradient(from 0% 0% to 100% 0%, #921a5e, #ac3475, #c74d8d);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 26px;"
        );
    }
}
