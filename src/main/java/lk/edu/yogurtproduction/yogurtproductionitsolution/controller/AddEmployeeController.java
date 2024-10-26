package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.EmployeeDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.EmployeeModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {


    @FXML
    private Button btnSave;

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

    private EmployeeController employeeFormController;
    EmployeeModel employeeModel = new EmployeeModel();



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            loadNextEmployeeId();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


     void loadNextEmployeeId() throws SQLException {

             String nextEmployeeId = employeeModel.getNextCustomerId();
         lblEmployeeId.setText(nextEmployeeId);


    }

    @FXML
    void btnSaveEmployeeOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String EmployeId = lblEmployeeId.getText();
        String empName = txtName.getText();
        String empNic = txtNic.getText();
        String empEmail = txtEmail.getText();
        String empPhone = txtPhone.getText();
        EmployeeDto customerDTO = new EmployeeDto(
                EmployeId,
                empName,
                empNic,
                empEmail,
                empPhone

        );
        boolean isSaved =  employeeModel.saveEmpoyee(customerDTO);
        if(isSaved){

            loadNextEmployeeId();
            txtName.setText("");
            txtNic.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            new Alert(Alert.AlertType.INFORMATION,"Employee saved...!").show();

                employeeFormController.loadCustomerTable();


        }else{
            new Alert(Alert.AlertType.ERROR,"Fail to save Employee...!").show();
        }
    }



    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }



    public void setEmployeeFormController(EmployeeController employeeController) {

        this.employeeFormController = employeeController;
    }
}
