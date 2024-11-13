package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.EmployeeDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.PckingDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.ProdtionDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.EmployeeModel;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.PackingModel;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.ProdtionModel;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

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

    @FXML
    private DatePicker expireDate;

    @FXML
    private ComboBox<String> cmbPacType;


    public  void initialize() throws SQLException {
        loadProdtId();
        loadEmpId();
        loadcmbPacType();
    }
PackingModel packingModel = new PackingModel();
    @FXML
    void btnAddStock(ActionEvent event) throws SQLException {

         String Pac_ID = "PAC002";
         String Prod_ID = cmbProdId.getSelectionModel().getSelectedItem();
         String Pac_Type = cmbPacType.getSelectionModel().getSelectedItem();
         String Pac_Desc = desetxt.getText();
         String Pac_Date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
         String Expire_Date = String.valueOf(expireDate.getValue());
         double Qty = Double.parseDouble(qrytxt.getText());
         String Emp_ID = cmbEmpId.getSelectionModel().getSelectedItem();
         double RedusQty = Qty * PacTypes;
         String itemType = "END Prodt";

        PckingDto pckingDtos = new PckingDto(

                Pac_ID,
                Prod_ID,
                Pac_Type,
                Pac_Desc,
                Pac_Date,
                Expire_Date,
                Qty,
                Emp_ID,
                RedusQty,
                itemType
        );
        boolean isSaved = packingModel.savePacking(pckingDtos);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Saved successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Save failed! Please try again.").show();
        }




    }

    @FXML
    void btnPack(ActionEvent event) {
        System.out.println(PacTypes);
        String cmbPacTypeSelected = cmbPacType.getSelectionModel().getSelectedItem();
        System.out.println(cmbPacTypeSelected);
        System.out.println( expireDate.getValue());

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

    int PacTypes = 0;

    @FXML
    void CmbPacTypeOnAction(ActionEvent event) {


        String cmbPacTypeSelected = cmbPacType.getSelectionModel().getSelectedItem();
        if (cmbPacTypeSelected != null) {

            if (cmbPacTypeSelected == "08 PACK SET") {
                System.out.println(cmbPacTypeSelected);
                PacTypes = 8;
            } else if (cmbPacTypeSelected == "12 PACK SET") {
                System.out.println(cmbPacTypeSelected);
                PacTypes = 12;

            } else if (cmbPacTypeSelected == "48 PACK SET") {
                System.out.println(cmbPacTypeSelected);
                PacTypes = 48;
            }
        }

    }
    private void loadcmbPacType() {
        cmbPacType.setItems(FXCollections.observableArrayList("08 PACK SET","12 PACK SET", "48 PACK SET"));
    }



}
