package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.SuplierDto;
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

    private SupplierCon supplierCon;

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
    void btnSaveSupOnAction(ActionEvent event) throws Exception {
        String SupId = lblSupId.getText();
        String empName = txtName.getText();
        String empNic = txtNic.getText();
        String empEmail = txtEmail.getText();
        int empPhone = Integer.parseInt(txtPhone.getText());
        SuplierDto suplierDTO = new SuplierDto(
                SupId,
                empName,
                empNic,
                empEmail,
                empPhone

        );
        boolean isSaved =  suplierModel.saveSuplier(suplierDTO);
        if(isSaved){
            loadNextSuplierId();
            txtName.setText("");
            txtNic.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            supplierCon.loadSuplierTable();
            new Alert(Alert.AlertType.INFORMATION,"Suplier saved...!").show();

        }else{
            new Alert(Alert.AlertType.ERROR,"Fail to save Suplier...!").show();
        }
    }


    public void setSupFormCon(SupplierCon supplierCon) {
        this.supplierCon = supplierCon;
    }
}




