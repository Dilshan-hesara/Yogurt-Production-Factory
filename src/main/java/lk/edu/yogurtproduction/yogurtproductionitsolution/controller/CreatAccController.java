package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.UserDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.UserModel;

import java.io.IOException;
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
    void btnSaveAcc(ActionEvent event) {
        try {
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

            // Validate email format
            if (!isValidEmail(email)) {
                showAlert("Validation Error", "Invalid email address format!");
                return;
            }

            // Step 3: Create a UserDTO object
            UserDto user = new UserDto(username, password, email);

            // Step 4: Load the email verification form
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MailVerfy.fxml"));
            Parent load = loader.load();

            // Get the controller and pass user details to it
            VerfyMailController verifyController = loader.getController();
            verifyController.setUserDetails(user);

            // Show the verification form in a new stage
            Stage stage = new Stage();
            stage.setScene(new Scene(load));
            stage.setTitle("Verify Email");
            stage.initModality(Modality.APPLICATION_MODAL); // Make it a modal window
            stage.initOwner(btnSave.getScene().getWindow()); // Set parent window
            stage.showAndWait(); // Wait until the verification window is closed

            // Step 5: Proceed if email verification is successful
            if (verifyController.isVerified()) { // Ensure you have an `isVerified()` method in `VerfyMailController`
                boolean isSaved = userModel.createUser(user);

                if (isSaved) {
                    showAlert("Success", "Account saved successfully!");
                } else {
                    showAlert("Error", "Failed to save account. Please try again.");
                }
            } else {
                showAlert("Error", "Email verification failed. Please try again.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while loading the email verification form.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Database error occurred while saving the account.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Helper method to validate email format.
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    /**
     * Helper method to display alerts.
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

}

//    @FXML
//    void btnSaveAcc(ActionEvent event) throws SQLException, IOException {
//        // Step 1: Gather input
//        String username = txtUserName.getText().trim();
//        String password = txtPassWord.getText().trim();
//        String reEnterPassword = txtREEnterPassWord.getText().trim();
//        String email = txtEmail.getText().trim();
//
//        // Step 2: Validate input
//        if (username.isEmpty() || password.isEmpty() || reEnterPassword.isEmpty() || email.isEmpty()) {
//            showAlert("Validation Error", "All fields are required!");
//            return;
//        }
//
//        if (!password.equals(reEnterPassword)) {
//            showAlert("Validation Error", "Passwords do not match!");
//            return;
//        }
//
//        if (password.length() > 10) {
//            showAlert("Validation Error", "Password should not exceed 10 characters!");
//            return;
//        }
//
//        // Step 3: Create a UserDTO object
//        UserDto user = new UserDto(
//                username,
//                password,
//                email
//        );
//
//
//        // Step 4: Load the email verification form
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/VerfyMailFrom.fxml"));
//        Parent load = loader.load();
//
//        // Pass the user details to the verification form
//        VerfyMailFromController verifyController = loader.getController();
//        verifyController.setUserDetails(user);
//
//
//
//            Stage stage = new Stage();
//            stage.setScene(new Scene(load));
//            stage.setTitle("Verfy Mail");
//
//            stage.initModality(Modality.APPLICATION_MODAL);
//
//            stage.initOwner(btnSave.getScene().getWindow());
//            stage.showAndWait();
//
//
//        // Step 4: Save the user via service
//
//        if (verifyController.isVerified()) {
//
//            boolean isSaved = userModel.createUser(user);
//
//            if (isSaved) {
//                showAlert("Success", "Account saved successfully!");
//            } else {
//                showAlert("Error", "Failed to save account. Please try again.");
//            }
//        } else {
//            showAlert("Error", "Email verification failed. Please try again.");
//        }
//
//    }
//
//    // Helper method to display alerts
//    private void showAlert(String title, String content) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle(title);
//        alert.setContentText(content);
//        alert.showAndWait();
//    }
