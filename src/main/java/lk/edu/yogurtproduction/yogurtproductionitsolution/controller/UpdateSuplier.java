package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.EmployeeDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.SuplierDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.TM.SuplierTM;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.SuplierModel;

import java.sql.SQLException;

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

    SuplierModel suplierModel = new SuplierModel();

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String supId = lblSupId.getText();
        String name = txtName.getText();
        String nic = txtNic.getText();
        String email = txtEmail.getText();
        int phone = Integer.parseInt(txtPhone.getText());

        SuplierDto suplierDto = new SuplierDto(
                supId,
                name,
                nic,
                email,
                phone
        );


        boolean isUpdate = suplierModel.updateSuplier(suplierDto);
        if (isUpdate) {

            new Alert(Alert.AlertType.INFORMATION, "Suplier update...!").show();

        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Suplier...!").show();
        }
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
