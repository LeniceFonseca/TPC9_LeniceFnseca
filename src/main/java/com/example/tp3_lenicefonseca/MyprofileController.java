package com.example.tp3_lenicefonseca;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class MyprofileController implements Initializable {

    @FXML
    private Circle foto;

    @FXML
    private Button btnadicionarfoto;

    @FXML
    private Button btnalterardados;

    @FXML
    private Button btnhome;

    @FXML
    private Button btnmyposts;

    @FXML
    private Button btnresetar;

    @FXML
    private Button btnsignoff;

    @FXML
    private TextField mpalcunha;

    @FXML
    private TextField mpapelido;

    @FXML
    private TextField mpbiografia;

    @FXML
    private ComboBox<String> mpcategoria;

    @FXML
    private TextField mpemail;

    @FXML
    private TextField mpnome;

    @FXML
    private TextField mpnomeusuario;

    @FXML
    private TextField mppseudonimo;

    @FXML
    private TextField mpsenha;
    static String filePath;

    @FXML
    void adicionarFoto(ActionEvent event) {
        
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Imagens", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            String absolutePath = file.getAbsolutePath();
            String repositoryRoot = Paths.get("").toAbsolutePath().normalize().toString();
            filePath = "file:" + Paths.get(repositoryRoot).relativize(Paths.get(absolutePath)).toString();

            Image image = new Image(file.toURI().toString());
            foto.setFill(new ImagePattern(image));
            System.out.println("Caminho: " + filePath);
        }

    }

    @FXML
    void alterarDados(ActionEvent event) {
        String sqlAlterar = "UPDATE usuarios SET nome = ?, apelido = ?, email = ?, username = ?, password = ?, categoria = ?, alcunha = ?, pseudonimo = ?, biografia = ? WHERE nome = ?";
        String sqlAlterarFotos = "INSERT INTO fotos(nome, fotoperfil) VALUES (?, ?)";
        String sqlVerificar = "SELECT COUNT(*) FROM fotos WHERE nome = ? and fotoperfil = ?";

        PreparedStatement preparedStatementAlterarFotos = null;
        PreparedStatement preparedStatementAlterar = null;


        try {

            preparedStatementAlterar = Conexao.getConnection().prepareStatement(sqlAlterar);
            preparedStatementAlterarFotos = Conexao.getConnection().prepareStatement(sqlAlterarFotos);

            preparedStatementAlterar.setString(1, mpnome.getText());
            preparedStatementAlterar.setString(2, mpapelido.getText());
            preparedStatementAlterar.setString(3, mpemail.getText());
            preparedStatementAlterar.setString(4, mpnomeusuario.getText());
            preparedStatementAlterar.setString(5, mpsenha.getText());
            preparedStatementAlterar.setString(6, mpcategoria.getValue());
            preparedStatementAlterar.setString(7, mpalcunha.getText());
            preparedStatementAlterar.setString(8, mppseudonimo.getText());
            preparedStatementAlterar.setString(9, mpbiografia.getText());
            preparedStatementAlterar.setString(10, LoginController.pegarnome);


            preparedStatementAlterarFotos.setString(1, mpnome.getText());
            preparedStatementAlterarFotos.setString(2, filePath);

            preparedStatementAlterar.execute();
            preparedStatementAlterarFotos.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void home(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage stage = (Stage) btnsignoff.getScene().getWindow();
        stage.setScene(new Scene(root, 1300,900));
        stage.show();
    }

    @FXML
    void myPosts(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("myposts.fxml"));
        Stage stage = (Stage) btnsignoff.getScene().getWindow();
        stage.setScene(new Scene(root, 1300,900));
        stage.show();
    }

    @FXML
    void resetar(ActionEvent event) {

    }

    @FXML
    void signOff(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage stage = (Stage) btnsignoff.getScene().getWindow();
        stage.setScene(new Scene(root, 1300,900));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        String sql = "SELECT fotoperfil FROM fotos WHERE nome = ?";
        PreparedStatement preparedStatement1 = null;

        try {

            preparedStatement1 = Conexao.getConnection().prepareStatement(sql);
            preparedStatement1.setString(1, LoginController.pegarnome);

            ResultSet resultSet1 = preparedStatement1.executeQuery();

            while(resultSet1.next()) {

                if (resultSet1.next() == true) {
                    Image image = new Image(resultSet1.getString("fotoperfil"));
                    foto.setFill(new ImagePattern(image));
                } else {
                    Image image = new Image("file:src/main/resources/com/example/tp3_lenicefonseca/images/profile.png");
                    foto.setFill(new ImagePattern(image));
                }
            }
            preparedStatement1.execute();
            preparedStatement1.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        btnalterardados.setStyle(
                "-fx-background-color: linear-gradient(from 0% 0% to 100% 0%, #921a5e, #ac3475, #c74d8d);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 26px;"
        );

        btnhome.setStyle(
                "-fx-background-color: linear-gradient(from 0% 0% to 100% 0%, #36001f, #47022e, #58033d);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 26px;"
        );

        btnmyposts.setStyle(
                "-fx-background-color: linear-gradient(from 0% 0% to 100% 0%, #36001f, #47022e, #58033d);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 26px;"
        );

        btnsignoff.setStyle(
                "-fx-background-color: linear-gradient(from 0% 0% to 100% 0%, #36001f, #47022e, #58033d);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 26px;"
        );


        mpcategoria.getItems().addAll("Estudante", "Professor", "Funcionario", "Ex Aluno");

        String sqlPreencher = "SELECT nome, apelido, email, username, password, categoria, alcunha, pseudonimo, biografia FROM usuarios WHERE username = ?";


        PreparedStatement preparedStatementPreencher = null;


        try {

            preparedStatementPreencher = Conexao.getConnection().prepareStatement(sqlPreencher);

            preparedStatementPreencher.setString(1, LoginController.pegarnome);

            ResultSet resultSetPreencher = preparedStatementPreencher.executeQuery();

            while (resultSetPreencher.next()) {
                mpnome.setText(resultSetPreencher.getString("nome"));
                mpapelido.setText(resultSetPreencher.getString("apelido"));
                mpemail.setText(resultSetPreencher.getString("email"));
                mpnomeusuario.setText(resultSetPreencher.getString("username"));
                mpsenha.setText(resultSetPreencher.getString("password"));
                mpcategoria.setValue(resultSetPreencher.getString("categoria"));
                mpalcunha.setText(resultSetPreencher.getString("alcunha"));
                mppseudonimo.setText(resultSetPreencher.getString("pseudonimo"));
                mpbiografia.setText(resultSetPreencher.getString("biografia"));
            }

            preparedStatementPreencher.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
