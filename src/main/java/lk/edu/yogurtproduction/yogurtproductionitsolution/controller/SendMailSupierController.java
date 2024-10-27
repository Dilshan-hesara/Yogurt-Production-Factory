package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.TM.EmployeeTM;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.TM.SuplierTM;

public class SendMailSupierController {

    @FXML
    private TextArea txtBody;

    @FXML
    private Label txtName;

    @FXML
    private TextField txtSubject;
    private SuplierTM selctSuplier;

    public void sendMailData(SuplierTM selectSup) {
        this.selctSuplier = selectSup;
        txtName.setText(selectSup.getSupEmail());

    }

    @FXML
    void btnsend(ActionEvent event) {

    }


}
