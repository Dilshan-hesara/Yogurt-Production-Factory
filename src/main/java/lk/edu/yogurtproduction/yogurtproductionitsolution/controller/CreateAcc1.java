package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateAcc1 {

    @FXML
    private Button btnSave;

    @FXML
    private AnchorPane nextAcc;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassWord;

    @FXML
    private TextField txtREEnterPassWord;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnSaveAcc(ActionEvent event) throws IOException {
        nextAcc.getChildren().clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreateAcc2.fxml"));
        AnchorPane load = loader.load();


        Stage stage = (Stage) nextAcc.getScene().getWindow();
        stage.setTitle("Create Account Very Mail");

        nextAcc.getChildren().add(load);

    }

}
