package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.EmployeeDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.TM.EmployeeTM;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.EmployeeModel;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateEmployeeController implements Initializable {


    @FXML
    private Button btnUpdate;

    @FXML
    private Label lblEmployeeId;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtPhone;

    private EmployeeTM selectedEmployee;

    EmployeeModel employeeModel = new EmployeeModel();
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
    public void setEmployeeData(EmployeeTM employee) {
        this.selectedEmployee = employee;
        txtName.setText(selectedEmployee.getEmpName());
        txtNic.setText(selectedEmployee.getEmpNic());
        txtEmail.setText(selectedEmployee.getEmpEmail());
        txtPhone.setText(selectedEmployee.getEmpPhone());
        lblEmployeeId.setText(selectedEmployee.getEmpId());

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {




        }

    }


