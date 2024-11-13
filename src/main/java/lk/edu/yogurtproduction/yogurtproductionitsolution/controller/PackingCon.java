package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.EmployeeDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.ProdtionDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.EmployeeModel;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.ProdtionModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class PackingCon {

    @FXML
    private ComboBox<String> cmbEmpId;

    @FXML
    private ComboBox<String> cmbProdId;

    @FXML
    private TextField desetxt;

    @FXML
    private TextField qrytxt;

    @FXML
    private Label lblEmpName;

    @FXML
    private Label lblProdQty;

    @FXML
    private Label lblProdtName;


    public  void initialize() throws SQLException {
        loadProdtId();
        loadEmpId();

    }

    @FXML
    void btnAddStock(ActionEvent event) {

    }

    @FXML
    void btnPack(ActionEvent event) {

    }


    @FXML
    void cmbEmpOnAction(ActionEvent event) throws SQLException {
        String cmbEmpSelected = cmbEmpId.getSelectionModel().getSelectedItem();
        EmployeeDto employeeDto = employeeModel.findByID(cmbEmpSelected);
        if (employeeDto != null) {
            lblEmpName.setText(employeeDto.getEmpName());
        }

    }

    @FXML
    void cmbProdOnAction(ActionEvent event) throws SQLException {
        String cmbProdSelected = cmbProdId.getSelectionModel().getSelectedItem();
        System.out.println(cmbProdSelected);
        ProdtionDto prodtionDto = prodtionModel.findById(cmbProdSelected);
        if (prodtionDto != null) {
            lblProdtName.setText(prodtionDto.getPro_Name());
            lblProdQty.setText(String.valueOf(prodtionDto.getProd_Qty()));

        }

    }

    EmployeeModel employeeModel = new EmployeeModel();
    ProdtionModel prodtionModel = new ProdtionModel();

    private void loadEmpId() throws SQLException {
            ArrayList<String> empIds = employeeModel.getAllEmpIds();
            ObservableList<String> observableList = FXCollections.observableArrayList(empIds);
            cmbEmpId.setItems(observableList);
        }

    private void loadProdtId() throws SQLException {
        ArrayList<String> prodIds = prodtionModel.getAllProdtIds();
        ObservableList<String> observableList = FXCollections.observableArrayList(prodIds);
        cmbProdId.setItems(observableList);
    }

}
