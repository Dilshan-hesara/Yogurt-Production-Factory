package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.UserModel;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LogingPage{




    @FXML
    private AnchorPane logpage;

    @FXML
    private CheckBox showPasswordCheckBox;

    @FXML
    private PasswordField txtPass;

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtVisiblePass; // TextField for visible password

    @FXML
    private Button btnTogglePassword; // Button to toggle password visibility

    private boolean isPasswordVisible = false; // To track the visibility state of the password

    private final UserModel userModel = new UserModel();


    @FXML
    void testBtn(ActionEvent event) {
            if (isPasswordVisible) {
                // Hide password (move text from TextField to PasswordField)
                txtPass.setText(txtVisiblePass.getText());
                txtPass.setVisible(true); // Show PasswordField
                txtVisiblePass.setVisible(false); // Hide the TextField for plain text password
                btnTogglePassword.setText("Show Password");
                isPasswordVisible = false;
            } else {
                // Show password (move text from PasswordField to TextField)
                txtVisiblePass.setText(txtPass.getText());
                txtVisiblePass.setVisible(true); // Show the TextField for plain text password
                txtPass.setVisible(false); // Hide PasswordField
                btnTogglePassword.setText("Hide Password");
                isPasswordVisible = true;
            }

    }


    @FXML
    void dashBoadButt(ActionEvent event) throws IOException, SQLException {
        String username = txtUser.getText().trim();
        String password = txtPass.getText().trim();



        if (userModel.isValidUsername(username)) {
            if (userModel.isValidUser(username, password)) {
                logpage.getChildren().clear();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashBoad.fxml"));
                AnchorPane dashboardPane = loader.load();

                DashBoad dashController = loader.getController();
                dashController.setUserName(username);

                logpage.getChildren().add(dashboardPane);

            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid username or password").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Username does not exist").show();
        }
    }




    @FXML
    void showi(MouseEvent event) {}
//        // Toggle password visibility
//        if (isPasswordVisible) {
//            // Hide password
//            txtPass.setText(txtVisiblePass.getText());
//            txtPass.setVisible(true);
//            txtVisiblePass.setVisible(false);
//            btnTogglePassword.setText("Show Password");
//            isPasswordVisible = false;
//        } else {
//            // Show password
//            txtVisiblePass.setText(txtPass.getText());
//            txtVisiblePass.setVisible(true);
//            txtPass.setVisible(false);
//            btnTogglePassword.setText("Hide Password");
//            isPasswordVisible = true;
//        }
//    }

    @FXML
    void showPass(ActionEvent event) {

    }




    @FXML
    void backButton(ActionEvent event) throws IOException {

        logpage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/WelcomePage.fxml"));
        logpage.getChildren().add(load);
    }

    @FXML
    private Button btncreateAcc;
    @FXML
    private Label restPssword;

    @FXML
    private AnchorPane nextAcc;


    @FXML
    void CreatAcc(ActionEvent event) throws IOException {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreateAcc.fxml"));

            // FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserAccPageFrom.fxml"));
            Parent load = loader.load();


            Stage stage = new Stage();
            stage.setScene(new Scene(load));
            stage.setTitle("Create Account");

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.initOwner(btncreateAcc.getScene().getWindow());
            stage.showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load ui..!");
            e.printStackTrace();
        }

    }


    public void resetPassword(MouseEvent mouseEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RestPassWord.fxml"));
            Parent load = loader.load();


            Stage stage = new Stage();
            stage.setScene(new Scene(load));
            stage.setTitle("Rest Password");

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.initOwner(restPssword.getScene().getWindow());
            stage.showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load ui..!");
            e.printStackTrace();
        }
    }

//    @FXML
//    void testBtn(ActionEvent event) {
//        try {
//
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MailVerfy.fxml"));
//            Parent load = loader.load();
//
//
//            Stage stage = new Stage();
//            stage.setScene(new Scene(load));
//            stage.setTitle("Rest Password");
//
//            stage.initModality(Modality.APPLICATION_MODAL);
//
//            stage.initOwner(restPssword.getScene().getWindow());
//            stage.showAndWait();
//        } catch (IOException e) {
//            new Alert(Alert.AlertType.ERROR, "Fail to load ui..!");
//            e.printStackTrace();
//        }
//    }


    @FXML
    private Label fogetPassword;

    @FXML
    void fogetPassword(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FogetPassWord.fxml"));
        Parent load = loader.load();


        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setTitle("Foget Password");

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.initOwner(restPssword.getScene().getWindow());
        stage.showAndWait();

    }


}
