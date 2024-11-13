package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.EmployeeModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class PackingCon {

    @FXML
    private ComboBox<String> cmbEmpId;

    @FXML
    private ComboBox<?> cmbProdId;

    @FXML
    private TextField desetxt;

    @FXML
    private TextField qrytxt;

    public  void initialize() throws SQLException {
        loadEmpId();
    }

    @FXML
    void btnAddStock(ActionEvent event) {

    }

    @FXML
    void btnPack(ActionEvent event) {

    }

    @FXML
    void cmbEmpOnAction(ActionEvent event) {

    }

    @FXML
    void cmbProdOnAction(ActionEvent event) {

    }

    EmployeeModel employeeModel = new EmployeeModel();

private void loadEmpId() throws SQLException {
        ArrayList<String> empIds = employeeModel.getAllEmpIds();
        ObservableList<String> observableList = FXCollections.observableArrayList(empIds);
        cmbEmpId.setItems(observableList);
    }

}
