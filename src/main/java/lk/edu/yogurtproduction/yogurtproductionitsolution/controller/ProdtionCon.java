package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.ProdMixModel;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.ProdtionModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProdtionCon {

    @FXML
    private Button btnAddProdt;

    @FXML
    private ComboBox<String> cmbProdt;

    @FXML
    private Label lblProdID;

    @FXML
    private TextField txtQty;
ProdtionModel model = new ProdtionModel();
ProdMixModel prodMix = new ProdMixModel();
    public void initialize() throws SQLException {

        loadnextProdID();
        loadProdName();
    }

    private void loadnextProdID() throws SQLException {
        String nextProdtID = model.getNextProdtID();
        lblProdID.setText(nextProdtID);
        System.out.println(nextProdtID);


    }
    private void loadProdName() throws SQLException {
        ArrayList<String> prodName = prodMix.getAllProdName();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(prodName);
        cmbProdt.setItems(observableList);
    }


    @FXML
    void btnAddPro(ActionEvent event) {

    }

    @FXML
    void cmbProdtOnAction(ActionEvent event) {

    }



}
