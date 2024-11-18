package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FogetPassWordSave {

    @FXML
    private Label txtMail;

    @FXML
    private Label txtUser;


    @FXML
    private TextField txtNewpassord;


    @FXML
    private AnchorPane nextPage;


    @FXML
    private TextField txtnewpassword;

    @FXML
    void VeffiMail(ActionEvent event) {

    }

    @FXML
    void VeffiMailExit(ActionEvent event) throws IOException {
        nextPage.getChildren().clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FogetPassWord.fxml"));
        AnchorPane load = loader.load();

//
//        VerfyMailController verifyController = loader.getController();
//        verifyController.setUserDetails(user);

        Stage stage = (Stage) nextPage.getScene().getWindow();  // Get the current stage
        stage.setTitle("Foget Password");

        nextPage.getChildren().add(load);

    }

}
