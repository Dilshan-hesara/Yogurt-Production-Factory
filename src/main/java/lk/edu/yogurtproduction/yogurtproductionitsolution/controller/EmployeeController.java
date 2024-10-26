package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

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
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.TM.EmployeeTM;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.EmployeeModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    private TableView<EmployeeTM> EmpTable;

    @FXML
    private Button addEmpButt;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnOpenMailSendModel;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<EmployeeTM, String> colMail;

    @FXML
    private TableColumn<EmployeeTM, String> col_name;

    @FXML
    private TableColumn<EmployeeTM, String> col_nic;

    @FXML
    private TableColumn<EmployeeTM, Integer> col_phone;

    @FXML
    private TableColumn<EmployeeTM, String> tbEmId;

    @FXML
    private TableColumn<EmployeeTM, String> emIdta;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
     emIdta.setCellValueFactory(new PropertyValueFactory<>("emIdta"));
     col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
     col_nic.setCellValueFactory(new PropertyValueFactory<>("nic"));
     colMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
     col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));



    }


    @FXML
    void btnOpenMailSendModelOnAction(ActionEvent event) {

    }

    @FXML
    void buttAddEmp(ActionEvent event) throws IOException {

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

}
