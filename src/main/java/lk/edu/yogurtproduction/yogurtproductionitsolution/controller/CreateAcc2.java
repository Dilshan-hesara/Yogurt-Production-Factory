package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.CreteAccDto;

import java.io.IOException;

public class CreateAcc2 {

    @FXML
    private AnchorPane nextPage;

    @FXML
    private Label txtMail;

    @FXML
    private Label txtMailSuss;

    @FXML
    private TextField txtOtp;

    @FXML
    private Label txtUser;

    @FXML
    void VeffiMail(ActionEvent event) {
        String username = txtUser.getText();
        String pasword =password;
        String email = txtMail.getText();

    }

    @FXML
    private AnchorPane creatAcc;

    String password ;



    public void sendAccDetails(CreteAccDto creteAccDto) {

        txtUser.setText(creteAccDto.getUsername());
        txtMail.setText(creteAccDto.getEmail());
        password=creteAccDto.getPassword();
    }

    @FXML
    void VeffiMailExit(ActionEvent event) throws IOException {



        creatAcc.getChildren().clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreateAcc1.fxml"));
        AnchorPane load = loader.load();


        Stage stage = (Stage) creatAcc.getScene().getWindow();
        stage.setTitle("Create Account ");

        creatAcc.getChildren().add(load);

    }
}
