package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.MatirialDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.MatiralMoadel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MatirialCon implements Initializable {


    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdateItem;

    @FXML
    private Label lblItId;

    @FXML
    private TableColumn<?, ?> matID;

    @FXML
    private TableColumn<?, ?> matName;

    @FXML
    private TableColumn<?, ?> matPrice;

    @FXML
    private TableColumn<?, ?> matQty;

    @FXML
    private TableView<?> matTable;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQuantity;

    private MatiralMoadel matiralMoadel =  new MatiralMoadel();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadNextMatId();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void loadNextMatId() throws SQLException {
        String nextMatId = matiralMoadel.getNextMatId();
        lblItId.setText(nextMatId);
    }


    @FXML
    void btnDelete(ActionEvent event) {

    }

    @FXML
    void btnReset(ActionEvent event) {

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

                new Alert(Alert.AlertType.INFORMATION, "Matirial saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save matireal...!").show();
            }
        }


    @FXML
    void btnUpdate(ActionEvent event) {

    }

}
