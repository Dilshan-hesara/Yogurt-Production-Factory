package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FogetPassWord {

    @FXML
    private Button btnSave;

    @FXML
    private AnchorPane nextPage;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnSaveRestPasss(ActionEvent event) throws IOException {

        nextPage.getChildren().clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FogetPassWordVerfy.fxml"));
        AnchorPane load = loader.load();


        Stage stage = (Stage) nextPage.getScene().getWindow();  // Get the current stage
        stage.setTitle("Very Email");  // Set the title of the window

        nextPage.getChildren().add(load);

//
//        VerfyMailController verifyController = loader.getController();
//        verifyController.setUserDetails(user);


    }


}
