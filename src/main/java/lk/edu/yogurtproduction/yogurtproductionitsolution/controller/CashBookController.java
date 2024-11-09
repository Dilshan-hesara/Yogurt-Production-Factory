package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import javafx.event.ActionEvent;

import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.CashBookDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.MatirialDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.SuplierDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.TM.CashBookTM;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.CashBookModel;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.MatiralMoadel;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.SuplierModel;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CashBookController {


    @FXML
    private ComboBox<String> cmbItemd;

    @FXML
    private ComboBox<String> cmbPay;

    @FXML
    private ComboBox<String> cmbSupId;

    @FXML
    private Label date;
    @FXML
    private Label date1;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblSupplerName;

    @FXML
    private Label lblItemQty;
    @FXML
    private  Label lblItemPrice;
    @FXML
    private TextField txtQty;

    @FXML
    private TableColumn<String, CashBookTM> colAmount;

    @FXML
    private TableColumn<String, CashBookTM> colCBNO;

    @FXML
    private TableColumn<String, CashBookTM> colDate;

    @FXML
    private TableColumn<String, CashBookTM> colDese;

    @FXML
    private TableColumn<String, CashBookTM> colPrice;

    @FXML
    private TableColumn<String, CashBookTM> colPyMethod;

    @FXML
    private TableColumn<String, CashBookTM> colQty;

    @FXML
    private TableColumn<String, CashBookTM> colSupId;
    @FXML
    private TableView<CashBookTM> tblCart;

    @FXML
    private ObservableList<CashBookTM> cashBookTMS = FXCollections.observableArrayList();

    private SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
    private SimpleDateFormat daterun = new SimpleDateFormat("yyyy-MM-dd");
    private final ObservableList<CashBookTM> CashBookTMS = FXCollections.observableArrayList();

    private SimpleDateFormat datE = new SimpleDateFormat("yyyy-MM-dd");
    SuplierModel suplierModel = new SuplierModel();
    MatiralMoadel matiralModel = new MatiralMoadel();

    @FXML
    public void initialize() throws SQLException {
        setCellValues();
        updateDateLabel();
        startClock();
        loadSupplierId();
        loadItemId();



    }

    private void setCellValues() {

        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colCBNO.setCellValueFactory(new PropertyValueFactory<>("CBNo"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDese.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colSupId.setCellValueFactory(new PropertyValueFactory<>("SupId"));

    }

    @FXML
    private TextField txtCashBookID;


    @FXML
    void btnAdd(ActionEvent event) {

        String cashBookID = txtCashBookID.getText();
        String supId = cmbSupId.getSelectionModel().getSelectedItem();
        int qty = Integer.parseInt(txtQty.getText());

        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        String desc = lblItemName.getText();

        double price = Double.parseDouble(lblItemPrice.getText());

        double amount = price * qty;

        CashBookTM cashBookTM = new CashBookTM(
                cashBookID,
                supId,
                currentDate,
                desc,
                qty,
                amount,
                price
        );

        cashBookTMS.add(cashBookTM);

        tblCart.setItems(cashBookTMS);
        tblCart.refresh();
    }

    CashBookModel cashBookModel = new CashBookModel();

    @FXML
    void btnPlaceIt(ActionEvent event) {
        double price = Double.parseDouble(lblItemPrice.getText());

        String CBNo = txtCashBookID.getText();
      String SupId = cmbSupId.getSelectionModel().getSelectedItem();
      String desc = lblItemName.getText();
      int qty = Integer.parseInt(txtQty.getText());
      double amount = price * qty;
      String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
      String matID = cmbItemd.getSelectionModel().getSelectedItem();
      String inID = "IN23";

        CashBookDto cashBookDto = new CashBookDto(
         CBNo,
         SupId,
         date,
         desc,
         qty,
         amount,
         matID,
         inID


        );

        Boolean isSaved = cashBookModel.saveResept(cashBookDto);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "  saved..!").show();

        }else {
            new Alert(Alert.AlertType.INFORMATION, " saved..! fail ").show();

        }








    }





    private void loadSupplierId() throws SQLException {
        ArrayList<String> supplierIds = suplierModel.getAllSupIds();
        ObservableList<String> observableList = FXCollections.observableArrayList(supplierIds);
        cmbSupId.setItems(observableList);
    }

    private void loadItemId() throws SQLException {
        ArrayList<String>  ItemIds = matiralModel.getAllItemIds();
        ObservableList<String> observableList = FXCollections.observableArrayList(ItemIds);
        cmbItemd.setItems(observableList);
    }

    private void loadPayMethod() {
        ObservableList<String> paymentMethods = FXCollections.observableArrayList("Cash", "Bank");
        cmbPay.setItems(paymentMethods);
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) throws SQLException {
        String selectID = (String) cmbItemd.getSelectionModel().getSelectedItem();

        MatirialDto matirialDto = matiralModel.findById(selectID);
        if (matirialDto != null) {

            // FIll item related labels
            lblItemName.setText(matirialDto.getMatName());
            lblItemPrice.setText(String.valueOf(matirialDto.getMatPrice()));
            lblItemQty.setText(String.valueOf(matirialDto.getMatQty()));
        }
    }

    @FXML
    void cmbSupOnAction(ActionEvent event) throws SQLException {
        String selectID = (String) cmbSupId.getSelectionModel().getSelectedItem();

        SuplierDto suplierDto = suplierModel.findById(selectID);
        if (suplierDto != null) {
            lblSupplerName.setText(suplierDto.getSupName());
        }

    }







    // date manage
    private void updateDateLabel() {
        String currentTime = dateFormat.format(new Date());
        date.setText(currentTime);
        String currentdate = daterun.format(new Date());
        date1.setText(currentdate);
    }

    private void startClock() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateDateLabel()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    void dat(ActionEvent event) {
        System.out.println(datE.format(new Date()));
    }

}


