package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.ProdMixDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.ProdMixModel;

import java.sql.SQLException;

public class ProdMixController {

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

}
