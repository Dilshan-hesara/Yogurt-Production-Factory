package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.UserDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.UserModel;

import java.sql.SQLException;

public class CreatAccController {

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassWord;

    @FXML
    private TextField txtREEnterPassWord;

    @FXML
    private TextField txtUserName;

    UserModel userModel = new UserModel();
    @FXML
    void btnSaveAcc(ActionEvent event) throws SQLException {
        // Step 1: Gather input
        String username = txtUserName.getText().trim();
        String password = txtPassWord.getText().trim();
        String reEnterPassword = txtREEnterPassWord.getText().trim();
        String email = txtEmail.getText().trim();

        // Step 2: Validate input
        if (username.isEmpty() || password.isEmpty() || reEnterPassword.isEmpty() || email.isEmpty()) {
            showAlert("Validation Error", "All fields are required!");
            return;
        }

        if (!password.equals(reEnterPassword)) {
            showAlert("Validation Error", "Passwords do not match!");
            return;
        }

        if (password.length() > 10) {
            showAlert("Validation Error", "Password should not exceed 10 characters!");
            return;
        }

        // Step 3: Create a UserDTO object
        UserDto user = new UserDto(
                username,
                password,
                email
        );

        // Step 4: Save the user via service
        boolean isSaved = userModel.createUser(user);

        if (isSaved) {
            showAlert("Success", "Account saved successfully!");
           // clearFields(); // Optional: Clear input fields
        } else {
            showAlert("Error", "Failed to save account. Please try again.");
        }
    }

    // Helper method to display alerts
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


}
