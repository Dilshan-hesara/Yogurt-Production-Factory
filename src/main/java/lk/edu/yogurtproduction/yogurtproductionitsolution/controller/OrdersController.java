package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.CustomerDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.CustomerModel;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.InventroyModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrdersController implements Initializable {

    @FXML
    private Button btnAddCustomer;

    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private ComboBox<String> cmbProd;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colIProdId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblIProdtPrice;

    @FXML
    private Label lblOderID;

    @FXML
    private Label lblProdName;

    @FXML
    private Label lblProdtQty;

    @FXML
    private Label orderDate;

    @FXML
    private TableView<?> tblCart;

    @FXML
    private TextField txtAddToCartQty;

    @FXML
    void btnAddCustomer(ActionEvent event) {

    }

    @FXML
    void btnAddToCart(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrder(ActionEvent event) {

    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) throws SQLException {
        String selectedCustomerId = (String) cmbCustomerId.getSelectionModel().getSelectedItem();
        CustomerDto customerDTO = customerModel.findById(selectedCustomerId);

        if (customerDTO != null) {

            lblCustomerName.setText(customerDTO.getName());
        }
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            loadProdtId();
            loadCustomerIds();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    InventroyModel invModel = new InventroyModel();

    private void loadProdtId() throws SQLException {
        ArrayList<String> itemIds = invModel.getAllProdIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(itemIds);
        cmbProd.setItems(observableList);
    }


    /**
     * Load all customer IDs into the customer ComboBox.
     */
    CustomerModel customerModel = new CustomerModel();

    private void loadCustomerIds() throws SQLException {
        ArrayList<String> customerIds = customerModel.getAllCustomerIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(customerIds);
        cmbCustomerId.setItems(observableList);
    }

}
