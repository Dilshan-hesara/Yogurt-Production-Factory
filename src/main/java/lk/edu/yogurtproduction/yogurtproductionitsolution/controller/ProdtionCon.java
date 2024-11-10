package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.edu.yogurtproduction.yogurtproductionitsolution.db.DBConnection;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.MatirialDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.ProdMixDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.ProdtionDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.ProdMixModel;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.ProdtionModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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


    @FXML
    private Label jeliy;

    @FXML
    private Label lblMilk;

    @FXML
    private Label lblsuguer;

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
    private TextField txtProdtName;
    @FXML
    void btnAddPro(ActionEvent event) throws SQLException {

        String Prod_ID =     lblProdID.getText();
        String Prod_Name = cmbProdt.getSelectionModel().getSelectedItem();
        String Pro_Name =  txtProdtName.getText();
        double Prod_Qty = Integer.parseInt(txtQty.getText());
         int P_milk = milk;
         int p_suguer = suguer;
         int p_jeley = jeley;

        ProdtionDto prodtionDto = new ProdtionDto(
                Prod_ID,
                Pro_Name,
                Prod_Qty,
                Prod_Name,
                P_milk,
                p_suguer,
                p_jeley

        );
        boolean isSaved = model.saveProdt(prodtionDto);

        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, " saved..!").show();


        } else {
            new Alert(Alert.AlertType.ERROR, " fail..!").show();
        }


    }
    ProdMixModel prodMixModel = new ProdMixModel();
    private int milk ;
    private int suguer;
    private int jeley;

    @FXML
    void cmbProdtOnAction(ActionEvent event) throws SQLException {

        String selectProd = cmbProdt.getSelectionModel().getSelectedItem();
        ProdMixDto prodMixDto = prodMixModel.findbyname(selectProd);

        if (prodMixDto != null) {

            lblMilk.setText(String.valueOf(prodMixDto.getMilk()));
            lblsuguer.setText(String.valueOf(prodMixDto.getSuguer()));
            jeliy.setText(String.valueOf(prodMixDto.getJeliy()));

            milk = prodMixDto.getMilk();
            jeley = prodMixDto.getJeliy();
            suguer = prodMixDto.getSuguer();

            System.out.println(milk + " " + suguer + " " + jeley);

        }
    }






}
