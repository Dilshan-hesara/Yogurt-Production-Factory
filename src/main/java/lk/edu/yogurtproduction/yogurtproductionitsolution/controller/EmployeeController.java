package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.EmployeeDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.TM.EmployeeTM;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.EmployeeModel;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {


    @FXML
    private Button addEmpButt;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnOpenMailSendModel;

    @FXML
    private Button btnAlEmpReport;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button resetButt;

    @FXML
    private TableColumn<EmployeeTM, String> colMail;

    @FXML
    private TableColumn<EmployeeTM, String> col_name;

    @FXML
    private TableColumn<EmployeeTM, String> col_nic;

    @FXML
    private TableColumn<EmployeeTM, String> col_phone; // Change Integer to String

    @FXML
    private TableColumn<EmployeeTM, String> emIdta;

    @FXML
    private TableView<EmployeeTM> emTable;

    EmployeeModel employeeModel = new EmployeeModel();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        emIdta.setCellValueFactory(new PropertyValueFactory<>("empId"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("empName"));
        col_nic.setCellValueFactory(new PropertyValueFactory<>("empNic"));
        colMail.setCellValueFactory(new PropertyValueFactory<>("empEmail"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("empPhone"));

        try {
            loadCustomerTable();
            btnDelete.setDisable(true);
            btnOpenMailSendModel.setDisable(true);
            btnUpdate.setDisable(true);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load employee data").show();
        }

    }
    void loadCustomerTable() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDto> employeeDTOS = employeeModel.getAllEmployees();
        ObservableList<EmployeeTM> employeeTMS = FXCollections.observableArrayList();

        for (EmployeeDto employeeDto : employeeDTOS) {
            EmployeeTM employeeTM = new EmployeeTM(
                    employeeDto.getEmpId(),
                    employeeDto.getEmpName(),
                    employeeDto.getEmpNic(),
                    employeeDto.getEmpEmail(),
                    employeeDto.getEmpPhone()
            );
            employeeTMS.add(employeeTM);
        }

        emTable.setItems(employeeTMS);
    }

    @FXML
    void btnOpenMailSendModelOnAction(ActionEvent event) {

    }

    @FXML
    void buttAddEmp(ActionEvent event) throws IOException {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddEmployee.fxml"));
            Parent load = loader.load();

            AddEmployeeController addEmployeeController = loader.getController();
            addEmployeeController.setEmployeeFormController(this);



            Stage stage = new Stage();
            stage.setScene(new Scene(load));
            stage.setTitle("Add Employee");

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.initOwner(btnUpdate.getScene().getWindow());
            stage.showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load ui..!");
            e.printStackTrace();
        }
    }

    @FXML
    void buttDeleteEmp(ActionEvent event) {

    }


    @FXML
    void buttUpadeEmp(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddEmployee.fxml"));
            Parent load = loader.load();



            Stage stage = new Stage();
            stage.setScene(new Scene(load));
            stage.setTitle("Add Employee");

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.initOwner(btnUpdate.getScene().getWindow());
            stage.showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load ui..!");
            e.printStackTrace();
        }
    }

    @FXML
    void generateAllCustomerReportOnAction(ActionEvent event) {

    }



    public void btntableClick(javafx.scene.input.MouseEvent mouseEvent) {

        addEmpButt.setDisable(true);
        btnDelete.setDisable(false);
        btnOpenMailSendModel.setDisable(false);
        btnUpdate.setDisable(false);
        btnAlEmpReport.setDisable(true);

    }

    @FXML
    void resetButt(ActionEvent event) {

        reset();
    }

    void reset(){
        addEmpButt.setDisable(false);
        btnDelete.setDisable(true);
        btnOpenMailSendModel.setDisable(true);
        btnUpdate.setDisable(true);
        btnAlEmpReport.setDisable(false);


    }
}
