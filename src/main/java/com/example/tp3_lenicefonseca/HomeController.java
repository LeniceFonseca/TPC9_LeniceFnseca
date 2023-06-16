package com.example.tp3_lenicefonseca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

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

    @FXML
    private Circle circle;

    @FXML
    private Circle circle1;

    @FXML
    private Circle circle2;

    @FXML
    private Circle circle3;

    @FXML
    private Circle circle4;

    @FXML
    private Circle circle5;

    @FXML
    private Circle circle6;

    @FXML
    private Circle circle7;

    @FXML
    private Circle circle8;

    @FXML
    private Circle circle9;

    @FXML
    private Label lblegenda;

    @FXML
    private Label lblegenda1;

    @FXML
    private Label lblegenda2;

    @FXML
    private Text post;

    @FXML
    private Text post1;

    @FXML
    private Circle minhafoto;

    @FXML
    private ImageView minhafoto1;

    @FXML
    private Button comentar;

    @FXML
    private Button lercomentario;

    @FXML
    private ScrollPane scrolleventos;

    @FXML
    private ScrollPane scrollfeed;

    @FXML
    private ScrollPane scrollgrupos;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        scrolleventos.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrolleventos.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        scrollfeed.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollfeed.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        scrollgrupos.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollgrupos.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        String sql = "SELECT nome, apelido FROM usuarios WHERE username = ?";
        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = Conexao.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, LoginController.pegarnome);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                lbnomeeapelido.setText(resultSet.getString("nome") + " " + resultSet.getString("apelido"));
            }

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }



        String sql1 = "SELECT fotoperfil FROM fotos WHERE nome = ?";
        PreparedStatement preparedStatement1 = null;

        try {

            preparedStatement1 = Conexao.getConnection().prepareStatement(sql1);
            preparedStatement1.setString(1, LoginController.pegarnome);

            ResultSet resultSet1 = preparedStatement1.executeQuery();

            while (resultSet1.next()) {
                Image img = new Image(resultSet1.getString("fotoperfil"));
                minhafoto.setFill(new ImagePattern(img));
            }

            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }




        Image image = new Image("file:src/main/resources/com/example/tp3_lenicefonseca/images/mali.jpg");
        circle.setFill(new ImagePattern(image));

        Image image1 = new Image("file:src/main/resources/com/example/tp3_lenicefonseca/images/jotaP.jpg");
        circle1.setFill(new ImagePattern(image1));

        Image image2 = new Image("file:src/main/resources/com/example/tp3_lenicefonseca/images/juninha.jpg");
        circle2.setFill(new ImagePattern(image2));

        Image image3 = new Image("file:src/main/resources/com/example/tp3_lenicefonseca/images/mali.jpg");
        circle3.setFill(new ImagePattern(image3));

        Image image4 = new Image("file:src/main/resources/com/example/tp3_lenicefonseca/images/lens.jpg");
        circle4.setFill(new ImagePattern(image4));

        Image image5 = new Image("file:src/main/resources/com/example/tp3_lenicefonseca/images/finalista.jpeg");
        circle4.setFill(new ImagePattern(image5));

        Image image6 = new Image("file:src/main/resources/com/example/tp3_lenicefonseca/images/infomatica.jpg");
        circle4.setFill(new ImagePattern(image6));

        Image image7 = new Image("file:src/main/resources/com/example/tp3_lenicefonseca/images/pintinho.jpg");
        circle4.setFill(new ImagePattern(image7));

        Image image8 = new Image("file:src/main/resources/com/example/tp3_lenicefonseca/images/caloiros.jpg");
        circle4.setFill(new ImagePattern(image8));

        Image image9 = new Image("file:src/main/resources/com/example/tp3_lenicefonseca/images/associacao.jpg");
        circle4.setFill(new ImagePattern(image9));

        post.setText("""
                De tudo, ao meu amor serei atento
                Antes, e com tal zelo, e sempre, e tanto
                Que mesmo em face do maior encanto 
                Dele se encante mais meu pensamento.
                
                Quero vivê-lo em cada vão momento
                E em louvor hei de espalhar meu canto
                E rir meu riso e derramar meu pranto
                Ao seu pesar ou seu contentamento.
                
                E assim, quando mais tarde me procure
                Quem sabe a morte, angústia de quem vive
                Quem sabe a solidão, fim de quem ama.
                
                Eu possa me dizer do amor (que tive):
                Que não seja imortal, posto que é chama
                Mas que seja infinito enquanto dure.
                """);

        post1.setText("""
                Um rapaz vai à padaria e pergunta se o salgado era de hoje.
                - Não, é de ontem.
                - E como faço para comer o de hoje?
                - Volte amanhã!
                """);

    }

    public void signoff(ActionEvent e) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage stage = (Stage) btnsignoff.getScene().getWindow();
        stage.setScene(new Scene(root, 1300,900));
        stage.show();
    }

    public void myprofile(ActionEvent e) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("myprofile.fxml"));
        Stage stage = (Stage) btnsignoff.getScene().getWindow();
        stage.setScene(new Scene(root, 1300,900));
        stage.show();
    }


    @FXML
    public void comentar(ActionEvent e) throws IOException {

        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("comentar.fxml"));
        Stage secondaryStage = new Stage();
        secondaryStage.initModality(Modality.APPLICATION_MODAL);
        secondaryStage.initOwner(comentar.getScene().getWindow());
        secondaryStage.setScene(new Scene(root, 600,400));
        secondaryStage.show();

    }


    @FXML
    void read(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("lercomentarios.fxml"));
        Stage otherStage = new Stage();
        otherStage.initModality(Modality.APPLICATION_MODAL);
        otherStage.initOwner(lercomentario.getScene().getWindow());
        otherStage.setScene(new Scene(root, 600,400));
        otherStage.show();
    }


}

