package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ProdMixController {

    @FXML
    private TextField txtJeliy;

    @FXML
    private TextField txtMilk;

    @FXML
    private TextField txtProdName;

    @FXML
    private TextField txtSugur;


    @FXML
    void btnAddProd(ActionEvent event) {
        String prodName = txtProdName.getText();
        int sugur = Integer.parseInt(txtSugur.getText());
        int jeliy = Integer.parseInt(txtJeliy.getText());
        int milk = Integer.parseInt(txtMilk.getText());


    }

}
