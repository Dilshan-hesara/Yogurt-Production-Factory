package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class DashBoad {


    @FXML
    private AnchorPane nextPage;

    public void emploButt(ActionEvent actionEvent) {
        navigateTo("/view/EmployeeForm.fxml");

    }

    @FXML
    void PackingButt(ActionEvent event) {
        navigateTo("/view/PackingForm.fxml");

    }



    @FXML
    void invetroyButt(ActionEvent event) {
        navigateTo("/view/InventryForm.fxml");

    }

    @FXML
    void matUsageButt(ActionEvent event) {
        navigateTo("/view/MatirialUsage.fxml");

    }

    @FXML
    void matirialButt(ActionEvent event) {
        navigateTo("/view/MatirialForm.fxml");

    }

    @FXML
    void prodtionButt(ActionEvent event) {
        navigateTo("/view/ProdtionForm.fxml");

    }

    @FXML
    void stockButt(ActionEvent event) {
        navigateTo("/view/StockForm.fxml");

    }

    @FXML
    void supplierButt(ActionEvent event) {
        navigateTo("/view/SupplierForm.fxml");

    }


    @FXML
    void dashBoadButt(ActionEvent event) {
        navigateTo("/view/DahBoadMain.fxml");


    }

    @FXML
    void cashBookButt(ActionEvent event) {
        navigateTo("/view/CashBForm.fxml");

    }




    public void navigateTo(String fxmlPath) {
        try {
            nextPage.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));

//  -------- Loaded anchor edges are bound to the content anchor --------
//      (1) Bind the loaded FXML to all edges of the content anchorPane
            load.prefWidthProperty().bind(nextPage.widthProperty());
            load.prefHeightProperty().bind(nextPage.heightProperty());

//      (2) Bind the loaded FXML to all edges of the AnchorPane
//            AnchorPane.setTopAnchor(load, 0.0);
//            AnchorPane.setRightAnchor(load, 0.0);
//            AnchorPane.setBottomAnchor(load, 0.0);
//            AnchorPane.setLeftAnchor(load, 0.0);

            nextPage.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }

}


