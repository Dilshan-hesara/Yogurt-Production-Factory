package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.ProdtionModel;

import java.sql.SQLException;

public class ProdtionCon {

    @FXML
    private Button btnAddProdt;

    @FXML
    private ComboBox<?> cmbProdt;

    @FXML
    private Label lblProdID;

    @FXML
    private TextField txtQty;
ProdtionModel model = new ProdtionModel();
    public void initialize() throws SQLException {

        loadnextProdID();
    }

    private void loadnextProdID() throws SQLException {
        String nextProdtID = model.getNextProdtID();
        lblProdID.setText(nextProdtID);
        System.out.println(nextProdtID);


    }

    @FXML
    void btnAddPro(ActionEvent event) {

    }

    @FXML
    void cmbProdtOnAction(ActionEvent event) {

    }



}
