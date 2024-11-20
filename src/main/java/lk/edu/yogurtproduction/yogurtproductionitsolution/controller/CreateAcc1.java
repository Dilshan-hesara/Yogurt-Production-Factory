package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.CreteAccDto;

import java.io.IOException;

public class CreateAcc1 {

    @FXML
    private Button btnSave;

    @FXML
    private AnchorPane nextAcc;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassWord;

    @FXML
    private TextField txtREEnterPassWord;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnSaveAcc(ActionEvent event) throws IOException {

        String username = txtUserName.getText().trim();
        String password = txtPassWord.getText().trim();
        String reEnterPassword = txtREEnterPassWord.getText().trim();
        String email = txtEmail.getText().trim();

        CreteAccDto creteAccDto = new CreteAccDto(
                username,
                password,
                email
        );
        nextAcc.getChildren().clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreateAcc2.fxml"));
        AnchorPane load = loader.load();

        CreateAcc2 senAccDetails = loader.getController();
        senAccDetails.sendAccDetails(creteAccDto);

        Stage stage = (Stage) nextAcc.getScene().getWindow();
        stage.setTitle("Create Account Very Mail");

        nextAcc.getChildren().add(load);

    }

}
