package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserDetailsController {

    @FXML
    private Button btnChangePass;

    @FXML
    private Label lblUserNama;

    @FXML
    private AnchorPane nextPage;

    @FXML
    void btnChangePass(ActionEvent event) throws IOException {
        System.out.println(userName);
//        nextPage.getChildren().clear();
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserDetailsNewPassW.fxml"));
//        AnchorPane load = loader.load();
//
//
//        Stage stage = (Stage) nextPage.getScene().getWindow();
//        stage.setTitle("Reset Password");
//
//        nextPage.getChildren().add(load);
    }

    String userName;
    public void sendUserName(String userName) {
    this.userName = userName;
    lblUserNama.setText(userName);
    }
}
