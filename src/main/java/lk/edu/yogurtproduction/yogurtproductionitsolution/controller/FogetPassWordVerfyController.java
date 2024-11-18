package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FogetPassWordVerfyController {

    @FXML
    private Label txtMail;

    @FXML
    private Label txtMailSuss;

    @FXML
    private TextField txtOtp;

    @FXML
    private Label txtUser;


    @FXML
    private AnchorPane nextPage;

    @FXML
    void VeffiMail(ActionEvent event) throws IOException {

        nextPage.getChildren().clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FogetPasWordSave.fxml"));
        AnchorPane load = loader.load();

        Stage stage = (Stage) nextPage.getScene().getWindow();  // Get the current stage
        stage.setTitle("save password");
//
//        VerfyMailController verifyController = loader.getController();
//        verifyController.setUserDetails(user);

        nextPage.getChildren().add(load);
    }

    @FXML
    void VeffiMailExit(ActionEvent event) throws IOException {

        nextPage.getChildren().clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FogetPassWord.fxml"));
        AnchorPane load = loader.load();

        Stage stage = (Stage) nextPage.getScene().getWindow();  // Get the current stage
        stage.setTitle("Foget Password");
//
//
//        VerfyMailController verifyController = loader.getController();
//        verifyController.setUserDetails(user);

        nextPage.getChildren().add(load);
    }


}

