package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.InventroyDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.MatirialUsageDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.ProdMixDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.ProdtionDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.InventroyModel;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.MatirialUsageModel;
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


    @FXML
    private Label jeliy;

    @FXML
    private Label lblMilk;

    @FXML
    private Label lblsuguer;

    @FXML
    private Label lblMilkAV;


    @FXML
    private Label lblgeliyAV;


    @FXML
    private Label lblsuguerAV;


ProdtionModel model = new ProdtionModel();
ProdMixModel prodMix = new ProdMixModel();
    public void initialize() throws SQLException {

        loadnextProdID();
        loadProdName();
        loadNextInventryId();
       loadNextmatirialUsageId();
        loadAvelbItem();
    }
    private int DBAVMilk;
    private int DBAVSuguer;
    private int DBAVGelitin;

    private void loadAvelbItem() {
        try {
            ArrayList<String> avbleItem = inventroyModel.getAllAVItems();

            lblgeliyAV.setText(avbleItem.get(0));
            lblMilkAV.setText(avbleItem.get(1));
            lblsuguerAV.setText(avbleItem.get(2));

            DBAVMilk = Integer.parseInt(lblMilkAV.getText());
            DBAVSuguer = Integer.parseInt(lblsuguerAV.getText());
            DBAVGelitin = Integer.parseInt(lblgeliyAV.getText());


        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load available items.").show();
        }

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

        ArrayList<InventroyDto> inventroyDTOS = new ArrayList<>();
        ArrayList<ProdMixDto> prodMixDTOS = new ArrayList<>();
        ArrayList<MatirialUsageDto> matirialUsageDTOS = new ArrayList<>();



        String Prod_ID =     lblProdID.getText();
        String Prod_Name = cmbProdt.getSelectionModel().getSelectedItem();
        String Pro_Name =  txtProdtName.getText();
        double Prod_Qty = Integer.parseInt(txtQty.getText());
         int P_milk = (int) (milk * Prod_Qty);
         int p_suguer = (int) (suguer * Prod_Qty);
         int p_jeley = (int) (jeley * Prod_Qty);
         String prodName = "prodtionResipi";

         String MatUs_ID = mtID;
         String Mat_Milk = String.valueOf(P_milk);
         String Mat_Suguer = String.valueOf(p_suguer);
         String Mat_Gelatin = String.valueOf(p_jeley);
        if (DBAVMilk < P_milk) {
            new Alert(Alert.AlertType.ERROR, "Not enough Milk available!").show();
            return;
        }

        if (DBAVSuguer < p_suguer) {
            new Alert(Alert.AlertType.ERROR, "Not enough Sugar available!").show();
            return;
        }

        if (DBAVGelitin < p_jeley) {
            new Alert(Alert.AlertType.ERROR, "Not enough Gelatin available!").show();
            return;
        }


        MatirialUsageDto matirialUsageDTO  = new MatirialUsageDto(

                     MatUs_ID,
                     Prod_ID,
                     Mat_Milk,
                     Mat_Suguer,
                     Mat_Gelatin


        );
        matirialUsageDTOS.add(matirialUsageDTO);

         ProdMixDto prodMixDTO  = new ProdMixDto(
                  prodName,
                 P_milk,
                 p_suguer,
                 p_jeley


        );
        prodMixDTOS.add(prodMixDTO);


         String InID = invID;
        String itemType = "UN Packed";
        String itemDescription =Pro_Name;
        String prodId = Prod_ID;
        String Qty = String.valueOf(Prod_Qty);

        InventroyDto inventroyDTO  = new InventroyDto(
                InID,
                itemType,
                itemDescription,
                Qty,
                prodId


        );
        inventroyDTOS.add(inventroyDTO);

        ProdtionDto prodtionDto = new ProdtionDto(
                Prod_ID,
                Pro_Name,
                Prod_Qty,
                Prod_Name,
                inventroyDTOS,
                prodMixDTOS,
                matirialUsageDTOS

        );
        boolean isSaved = model.saveProdt(prodtionDto);

        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, " saved..!").show();


        } else {
            new Alert(Alert.AlertType.ERROR, " fail..!").show();
        }


    }

    String invID;
    InventroyModel inventroyModel = new InventroyModel();

    public void loadNextInventryId() throws SQLException {
        String nextInventryId = inventroyModel.getInventroyId();
        invID = nextInventryId;
        System.out.println(nextInventryId);
    }

    @FXML
    void testBtn(ActionEvent event) throws SQLException {

        loadNextmatirialUsageId();
    }
    MatirialUsageModel matirialUsageModel = new MatirialUsageModel();
    String mtID;
    public void loadNextmatirialUsageId() throws SQLException {
        String matirialUsageId = matirialUsageModel.getmatirialUsageId();
        mtID = matirialUsageId;
        System.out.println(matirialUsageId);
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
