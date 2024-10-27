package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplierCon {

    @FXML
    private TableView<?> EmpTable;

    @FXML
    private Button addSupButt;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnOpenMailSendModel;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> tbMail;

    @FXML
    private TableColumn<?, ?> tbName;

    @FXML
    private TableColumn<?, ?> tbNic;

    @FXML
    private TableColumn<?, ?> tbPhone;

    @FXML
    private TableColumn<?, ?> tbSupId;

    @FXML
    void btnSupMail(ActionEvent event) {

    }

    @FXML
    void butSupReport(ActionEvent event) {

    }

    @FXML
    void buttAddEmp(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddSuplier.fxml"));
            Parent load = loader.load();


            Stage stage = new Stage();
            stage.setScene(new Scene(load));
            stage.setTitle("Add Suplier");

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.initOwner(btnUpdate.getScene().getWindow());
            stage.showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load ui..!");
            e.printStackTrace();
        }
    }

    @FXML
    void buttDeleteSup(ActionEvent event) {

    }

    @FXML
    void buttUpadeEmp(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpdateSuplier.fxml"));
            Parent load = loader.load();



            Stage stage = new Stage();
            stage.setScene(new Scene(load));
            stage.setTitle("Update Suplier");

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.initOwner(btnUpdate.getScene().getWindow());
            stage.showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load ui..!");
            e.printStackTrace();
        }
    }

}
