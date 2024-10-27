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
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.SuplierDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.TM.EmployeeTM;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.TM.SuplierTM;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.SuplierModel;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplierCon implements Initializable {


    @FXML
    private TableView<SuplierTM> supTable;

    @FXML
    private Button addSupButt;

    @FXML
    private Button resetButt;

    @FXML
    private Button allReport;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnOpenMailSendModel;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<EmployeeTM, String> tbMail;

    @FXML
    private TableColumn<EmployeeTM, String> tbName;

    @FXML
    private TableColumn<EmployeeTM, String> tbNic;

    @FXML
    private TableColumn<EmployeeTM, Integer> tbPhone;

    @FXML
    private TableColumn<EmployeeTM, String> tbSupId;

    SuplierModel suplierModel = new SuplierModel();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tbSupId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        tbName.setCellValueFactory(new PropertyValueFactory<>("supName"));
        tbNic.setCellValueFactory(new PropertyValueFactory<>("supNic"));
        tbPhone.setCellValueFactory(new PropertyValueFactory<>("supPhone"));
        tbMail.setCellValueFactory(new PropertyValueFactory<>("supEmail"));

        try {
            loadSuplierTable();
            btnDelete.setDisable(true);
            btnOpenMailSendModel.setDisable(true);
            btnUpdate.setDisable(true);
        }catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load suplir data").show();
        }
    }

    void loadSuplierTable() throws Exception {
        ArrayList<SuplierDto>suplierDTOS =  suplierModel.getAllSuplier();
        ObservableList<SuplierTM> suplierTMS = FXCollections.observableArrayList();

        for(SuplierDto supDto : suplierDTOS) {
            SuplierTM suplierTM = new SuplierTM(
                    supDto.getSupId(),
                    supDto.getSupName(),
                    supDto.getSupNic(),
                    supDto.getSupEmail(),
                    supDto.getSupPhone()
            );
            suplierTMS.add(suplierTM);
        }
        supTable.setItems(suplierTMS);


    }
    @FXML
    void btnSupMail(ActionEvent event) {

    }

    @FXML
    void butSupReport(ActionEvent event) {

    }

    @FXML
    void buttAddSup(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddSuplier.fxml"));
            Parent load = loader.load();

            AddSuplierController addSupCon = loader.getController();
            addSupCon.setSupFormCon(this);

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
    void buttUpadeSup(ActionEvent event) {

        SuplierTM selectSup = supTable.getSelectionModel().getSelectedItem();
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpdateSuplier.fxml"));
            Parent load = loader.load();


            UpdateSuplier updateSupCon = loader.getController();
            updateSupCon.setSuplierData(selectSup);

            UpdateSuplier updateSuplierReloadTable = loader.getController();
            updateSuplierReloadTable.setSupierReloadTable(this);




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

    @FXML
    void tableClick(MouseEvent event) {

    }

    public void tblClik(javafx.scene.input.MouseEvent mouseEvent) {

        addSupButt.setDisable(true);
        btnDelete.setDisable(false);
        btnOpenMailSendModel.setDisable(false);
        btnUpdate.setDisable(false);
        allReport.setDisable(true);

    }

    @FXML
    void resetButt(ActionEvent event) {

        reset();

    }
    void reset(){
        addSupButt.setDisable(false);
        btnDelete.setDisable(true);
        btnOpenMailSendModel.setDisable(true);
        btnUpdate.setDisable(true);
        allReport.setDisable(false);


    }
}
