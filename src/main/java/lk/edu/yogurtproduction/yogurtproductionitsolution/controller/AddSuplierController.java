package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.SuplierModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddSuplierController implements Initializable {


    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Label lblSupId;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtPhone;

    SuplierModel suplierModel = new SuplierModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadNextSuplierId();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void loadNextSuplierId() throws SQLException {
        String nextSupId = suplierModel.getNextSuplierId();
        lblSupId.setText(nextSupId);

    }

    @FXML
    void btnSaveEmployeeOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
