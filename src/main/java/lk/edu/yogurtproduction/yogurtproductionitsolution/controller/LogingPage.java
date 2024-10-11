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
    private Label lblError; // Declare a Label to show error messages

    @FXML
    void dashBoadButt(ActionEvent event) throws IOException {
        // Retrieve the username and password from the text fields
        String username = txtUser.getText();
        String password = txtPass.getText();

        // Check if the credentials are valid
        if (username.equals("dilshan") && password.equals("1234")) {
            // Clear the current AnchorPane content
            logpage.getChildren().clear();

            // Load the new AnchorPane for the dashboard from the FXML file
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/DashBoad.fxml"));
            logpage.getChildren().add(load);
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid username or password ").show();

        }
    }

    @FXML
    void showpass(ActionEvent event) {

    }


}
