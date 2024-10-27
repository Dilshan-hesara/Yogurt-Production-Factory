package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    void btnSave(ActionEvent event) {

    }

    @FXML
    void btnUpdate(ActionEvent event) {

    }

}
