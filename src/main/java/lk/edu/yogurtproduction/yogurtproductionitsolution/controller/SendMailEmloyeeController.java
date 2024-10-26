package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.TM.EmployeeTM;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.regex.Pattern;

public class SendMailEmloyeeController {
    @FXML
    private Label txtName;
    @FXML
    private TextArea txtBody;

    @FXML
    private TextField txtSubject;
    private EmployeeTM selectedEmployee;


    public void sendMailData(EmployeeTM employee){
        this.selectedEmployee = employee;
        txtName.setText(selectedEmployee.getEmpEmail());

    }

    @FXML
    void btnsend(ActionEvent event) {


        String subject = txtSubject.getText();
        String body = txtBody.getText();
        String recipientEmail = selectedEmployee.getEmpEmail();


        }
    }




