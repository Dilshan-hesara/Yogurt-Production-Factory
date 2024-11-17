package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LogingPage {

    @FXML
    private AnchorPane logpage;

    @FXML
    private CheckBox showPasswordCheckBox;

    @FXML
    private PasswordField txtPass;

    @FXML
    private TextField txtUser;



    @FXML
    void dashBoadButt(ActionEvent event) throws IOException {

        String username = txtUser.getText();
        String password = txtPass.getText();

        if (username.equals("dilshan") && password.equals("1234")) {

            logpage.getChildren().clear();

            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/DashBoad.fxml"));
            logpage.getChildren().add(load);
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid username or password ").show();

        }
    }

    @FXML
    void showpass(ActionEvent event) {

    }


    @FXML
    void backButton(ActionEvent event) throws IOException {

        logpage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/WelcomePage.fxml"));
        logpage.getChildren().add(load);
    }

}
