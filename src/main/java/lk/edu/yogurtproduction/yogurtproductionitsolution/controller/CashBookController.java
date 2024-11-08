package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.MatirialDto;
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

    private SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
    private SimpleDateFormat daterun = new SimpleDateFormat("yyyy-MM-dd");

    private SimpleDateFormat datE = new SimpleDateFormat("yyyy-MM-dd");
    SuplierModel suplierModel = new SuplierModel();
    MatiralMoadel matiralModel = new MatiralMoadel();

    @FXML
    public void initialize() throws SQLException {
        updateDateLabel();
        startClock();
        loadSupplierId();
        loadItemId();
        loadPayMethod();

    }
    @FXML
    void btnPlaceIt(ActionEvent event) {

    }
    MatiralMoadel matiralMoadel = new MatiralMoadel();

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
MatirialDto matirialDto = new MatirialDto();

    @FXML
    void cmbItemOnAction(ActionEvent event) throws SQLException {
        String selectID = (String) cmbItemd.getSelectionModel().getSelectedItem();

        MatirialDto matirialDto = matiralModel.findById(selectID);
        if ( matirialDto != null) {

            // FIll item related labels
            lblItemName.setText(matirialDto.getMatName());
            lblItemPrice.setText(String.valueOf(matirialDto.getMatPrice()));
            lblItemQty.setText(String.valueOf(matirialDto.getMatQty()));
        }



    }



    @FXML
    void cmbSupOnAction(ActionEvent event) {
        String selectID = (String) cmbSupId.getSelectionModel().getSelectedItem();


    }


    @FXML
    void cmbPayOnAction(ActionEvent event) {

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


