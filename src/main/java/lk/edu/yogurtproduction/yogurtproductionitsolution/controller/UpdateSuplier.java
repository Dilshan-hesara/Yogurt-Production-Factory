package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.TM.SuplierTM;

public class UpdateSuplier {

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Label lblSupId;
    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtPhone;

    private SuplierTM suplierTM;

    @FXML
    void btnSaveEmployeeOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    public void setSuplierData(SuplierTM suplierTM) {
        this.suplierTM = suplierTM;

        txtName.setText(suplierTM.getSupName());
        txtNic.setText(suplierTM.getSupNic());
        txtEmail.setText(suplierTM.getSupEmail());
        txtPhone.setText(String.valueOf(suplierTM.getSupPhone()));
        lblSupId.setText(suplierTM.getSupId());

    }
}
