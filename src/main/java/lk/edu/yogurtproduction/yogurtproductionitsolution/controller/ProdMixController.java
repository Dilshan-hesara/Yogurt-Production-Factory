package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.ProdMixDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.TM.ProdMixTM;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.ProdMixModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
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

    @FXML
    private Button addrecBtn;

    @FXML
    private Button deleteButt;

    @FXML
    private Button recipBtn;

    @FXML
    private Button resetButt;

    @FXML
    private Button updateBtn;

    ProdMixModel prodMixModel = new ProdMixModel();

    @FXML
    void btnAddProd(ActionEvent event) throws SQLException {
        String prodName = txtProdName.getText() + "-"+"Recipe";
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
            loadTble();

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

        deleteButt.setDisable(true);
        updateBtn.setDisable(true);
        resetButt.setDisable(true);

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
    public void btnUpdateRecip(ActionEvent actionEvent) {

        ProdMixTM selectedRecipe = tblprodtMix.getSelectionModel().getSelectedItem();
        if (selectedRecipe == null) {
            new Alert(Alert.AlertType.ERROR, "Please select a recipe to update!").show();
            return;
        }

        try {

            String currentProdName = txtProdName.getText().trim();
            if (!selectedRecipe.getProdName().equals(currentProdName)) {
                new Alert(Alert.AlertType.WARNING, "Recipe name cannot be changed!").show();
                txtProdName.setText(selectedRecipe.getProdName());
                return;
            }


            String milkQtyText = txtMilk.getText().trim();
            String suguerQtyText = txtSugur.getText().trim();
            String jeliyQtyText = txtJeliy.getText().trim();

            String qtyPattern = "^[0-9]+$";
            if (!milkQtyText.matches(qtyPattern) || !suguerQtyText.matches(qtyPattern) || !jeliyQtyText.matches(qtyPattern)) {
                new Alert(Alert.AlertType.ERROR, "Please enter valid numeric quantities!").show();
                return;
            }

            int milkQty = Integer.parseInt(milkQtyText);
            int suguerQty = Integer.parseInt(suguerQtyText);
            int jeliyQty = Integer.parseInt(jeliyQtyText);

            boolean isUpdated = prodMixModel.updateQuantities(
                    selectedRecipe.getProdName(),
                    milkQty,
                    suguerQty,
                    jeliyQty
            );

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Recipe updated successfully!").show();
                loadTble();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update recipe!").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred while updating the recipe!").show();
        }
    }

    public void tblClik(MouseEvent mouseEvent) {
        ProdMixTM prodMixTM = tblprodtMix.getSelectionModel().getSelectedItem();
        if (prodMixTM != null) {
            txtProdName.setText(prodMixTM.getProdName());
            txtMilk.setText(String.valueOf(prodMixTM.getMilk()));
            txtSugur.setText(String.valueOf(prodMixTM.getSuguer()));
            txtJeliy.setText(String.valueOf(prodMixTM.getJeliy()));

            recipBtn.setDisable(true);
            addrecBtn.setDisable(true);

            deleteButt.setDisable(false);
            updateBtn.setDisable(false);
            resetButt.setDisable(false);
        }
    }

    @FXML
    void deleteButt(ActionEvent event) {
        ProdMixTM selectedRecipe = tblprodtMix.getSelectionModel().getSelectedItem();

        if (selectedRecipe == null) {
            new Alert(Alert.AlertType.ERROR, "Please select a recipe to delete!").show();
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete the selected recipe?", ButtonType.YES, ButtonType.NO);
        confirmationAlert.setTitle("Confirm Deletion");
        confirmationAlert.setHeaderText("Delete Recipe");

        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            try {

                boolean isUsed = prodMixModel.isRecipeUsedInProductions(selectedRecipe.getProdName());

                if (isUsed) {
                    new Alert(Alert.AlertType.ERROR,
                            "This recipe is used in productions and cannot be deleted.").show();
                    return;
                }

                boolean isDeleted = prodMixModel.deleteRecipe(selectedRecipe.getProdName());

                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Recipe deleted successfully!").show();
                    loadTble();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete recipe!").show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred while deleting the recipe!").show();
            }
        }
    }




    @FXML
    void resetButt(ActionEvent event) {

        addrecBtn.setDisable(false);
        recipBtn.setDisable(false);
        updateBtn.setDisable(true);
        deleteButt.setDisable(true);
    }

}
