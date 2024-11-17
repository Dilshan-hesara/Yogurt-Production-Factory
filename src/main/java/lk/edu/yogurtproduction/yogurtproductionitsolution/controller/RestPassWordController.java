package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.UserModel;

import java.sql.SQLException;

public class RestPassWordController {

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtNewPassWord;

    @FXML
    private TextField txtOldPassWord;

    @FXML
    private TextField txtRenterNewPassWord;

    @FXML
    private TextField txtUserName;

    UserModel userModel = new UserModel();

    @FXML
    void btnSaveRestPasss(ActionEvent event) throws SQLException {
        String username = txtUserName.getText().trim();
        String oldPassword = txtOldPassWord.getText().trim();
        String newPassword = txtNewPassWord.getText().trim();
        String reenterPassword = txtRenterNewPassWord.getText().trim();


        if (userModel.isValidUsername(username)) {

            if (userModel.isValidUser(username, oldPassword)) {

                if (newPassword.equals(reenterPassword)) {

                    boolean isUpdated = userModel.updatePassword(username, newPassword);

                    if (isUpdated) {
                        new Alert(Alert.AlertType.INFORMATION, "Password updated successfully!").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Password update failed!").show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "New password and re-entered password do not match!").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Incorrect old password!").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Username does not exist").show();
        }
    }


}
