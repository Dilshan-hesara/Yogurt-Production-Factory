package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserDetailsNewPassW {

    @FXML
    private Button btnChangePass;

    @FXML
    private Label lblUserNama;

    @FXML
    private AnchorPane nextPage;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtUserName1;

    @FXML
    void btnChangePass(ActionEvent event) throws IOException {

        nextPage.getChildren().clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserDetailsNewPassSave.fxml"));
        AnchorPane load = loader.load();


        Stage stage = (Stage) nextPage.getScene().getWindow();
        stage.setTitle("Reset Password");

        nextPage.getChildren().add(load);
    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        nextPage.getChildren().clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserDetailsFrom.fxml"));
        AnchorPane load = loader.load();


        Stage stage = (Stage) nextPage.getScene().getWindow();
        stage.setTitle("Use Details");

        nextPage.getChildren().add(load);
    }
}
