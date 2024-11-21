package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class OrdersController {

    @FXML
    private Button btnAddCustomer;

    @FXML
    private ComboBox<?> cmbCustomerId;

    @FXML
    private ComboBox<?> cmbProd;

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
    void cmbCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void cmbItemOnAction(ActionEvent event) {

    }

}
