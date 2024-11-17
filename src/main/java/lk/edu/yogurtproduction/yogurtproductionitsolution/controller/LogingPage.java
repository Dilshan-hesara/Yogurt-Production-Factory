package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    @FXML
    private Button btncreateAcc;
    @FXML
    private Label restPssword;


    @FXML
    void CreatAcc(ActionEvent event) throws IOException {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreateAcc.fxml"));
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
}
