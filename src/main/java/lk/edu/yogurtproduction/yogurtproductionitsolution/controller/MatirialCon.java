package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.MatirialDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.TM.MatirialTM;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.TM.SuplierTM;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.MatiralMoadel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class MatirialCon implements Initializable {


    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSavem;

    @FXML
    private Button btnUpdate;

    @FXML
    private Label lblItId;

    @FXML
    private TableColumn<MatirialTM, String> matID;

    @FXML
    private TableColumn<MatirialTM, String> matName;

    @FXML
    private TableColumn<MatirialTM, Integer> matPrice;

    @FXML
    private TableColumn<MatirialTM, Integer> matQty;

    @FXML
    private TableView<MatirialTM> matTable;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQuantity;

    private MatiralMoadel matiralMoadel =  new MatiralMoadel();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        matID.setCellValueFactory(new PropertyValueFactory<>("matId"));
        matName.setCellValueFactory(new PropertyValueFactory<>("matName"));
        matPrice.setCellValueFactory(new PropertyValueFactory<>("matPrice"));
        matQty.setCellValueFactory(new PropertyValueFactory<>("matQty"));
        try {
            loadNextMatId();
            loadTable();
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void loadNextMatId() throws SQLException {
        String nextMatId = matiralMoadel.getNextMatId();
        lblItId.setText(nextMatId);
    }

    private void loadTable() throws SQLException {
        ArrayList<MatirialDto> matirialDTOS = matiralMoadel.getAllMatireal();

        ObservableList<MatirialTM> matirialTMS = FXCollections.observableArrayList();

        for (MatirialDto matirialDto : matirialDTOS) {
            MatirialTM matirialTM = new MatirialTM(
                    matirialDto.getMatId(),

                    matirialDto.getMatName(),

                    matirialDto.getMatQty(),
                    matirialDto.getMatPrice()

            );
            matirialTMS.add(matirialTM);
        }
matTable.setItems(matirialTMS);

    }
    @FXML
    void btnDelete(ActionEvent event) throws SQLException {
        MatirialTM matirialTM = matTable.getSelectionModel().getSelectedItem();

        String matId = matirialTM.getMatId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = matiralMoadel.deleteMatirial (matId);
            if (isDeleted) {
                loadTable();
                new Alert(Alert.AlertType.INFORMATION, "Matirial deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Matirial...!").show();
            }
        }
    }



    @FXML
    void btnSave(ActionEvent event) throws SQLException {
        String matrId = lblItId.getText();
        String name = txtName.getText();
        int qty = Integer.parseInt(txtQuantity.getText());
        int price = Integer.parseInt(txtPrice.getText());


            MatirialDto matirialDto = new MatirialDto(
                    matrId,
                    name,
                    qty,
                    price

            );

            boolean isSaved = matiralMoadel.saveMatirial(matirialDto);
            if (isSaved) {
                loadNextMatId();
                loadTable();

                new Alert(Alert.AlertType.INFORMATION, "Matirial saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save matireal...!").show();
            }
        }


    @FXML
    void btnUpdate(ActionEvent event) {

    }

    @FXML
    void btnReset(ActionEvent event) {
        reset();
    }

    void reset(){
        btnSavem.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);


    }

    public void tbleClick(MouseEvent mouseEvent) {

    btnSavem.setDisable(true);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
    }
}
