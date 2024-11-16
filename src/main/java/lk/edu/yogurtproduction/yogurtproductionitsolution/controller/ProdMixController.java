package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.ProdMixDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.TM.ProdMixTM;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.ProdMixModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProdMixController implements Initializable {


    @FXML
    private TableColumn<String, ProdMixTM> ColGeliy;

    @FXML
    private TableColumn<String, ProdMixTM> cloProdtName;

    @FXML
    private TableColumn<String, ProdMixTM> colMilk;

    @FXML
    private TableColumn<String, ProdMixTM> colSu;

    @FXML
    private TableView<ProdMixTM> tblprodtMix;

    @FXML
    private TextField txtJeliy;

    @FXML
    private TextField txtMilk;

    @FXML
    private TextField txtProdName;

    @FXML
    private TextField txtSugur;


    ProdMixModel prodMixModel = new ProdMixModel();

    @FXML
    void btnAddProd(ActionEvent event) throws SQLException {
        String prodName = txtProdName.getText();
        int suguer = Integer.parseInt(txtSugur.getText());
        int jeliy = Integer.parseInt(txtJeliy.getText());
        int milk = Integer.parseInt(txtMilk.getText());

        ProdMixDto prodMixDto  = new ProdMixDto(
                prodName,
                suguer,
                jeliy,
                milk
        );

        boolean isSaved = prodMixModel.saveProdtMaix(prodMixDto);
        if (isSaved) {

            new Alert(Alert.AlertType.INFORMATION, " saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save ...!").show();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cloProdtName.setCellValueFactory(new PropertyValueFactory<>("prodName"));
        colMilk.setCellValueFactory(new PropertyValueFactory<>("milk"));
        colSu.setCellValueFactory(new PropertyValueFactory<>("suguer"));
        ColGeliy.setCellValueFactory(new PropertyValueFactory<>("jeliy"));

        try {
            loadTble();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadTble() throws SQLException {
        ArrayList<ProdMixDto> prodMixDTOS = prodMixModel.getAllInventoryData();

        ObservableList<ProdMixTM> prodMixTMS = FXCollections.observableArrayList();


        for (ProdMixDto prodMixDto : prodMixDTOS) {
            ProdMixTM prodMixTM = new ProdMixTM(
                    prodMixDto.getProdName(),
                    prodMixDto.getMilk(),
                    prodMixDto.getSuguer(),
                    prodMixDto.getJeliy()

            );
            prodMixTMS.add(prodMixTM);
        }

        tblprodtMix.setItems(prodMixTMS);

    }


    @FXML
    void btnALLReportResip(ActionEvent event) {

    }

}
