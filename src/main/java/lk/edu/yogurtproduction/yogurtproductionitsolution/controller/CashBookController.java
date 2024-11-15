package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import javafx.event.ActionEvent;

import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.CashBookDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.MatirialDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.SuplierDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.TM.CashBookTM;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.CashBookModel;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.InventroyModel;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.MatiralMoadel;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.SuplierModel;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CashBookController {


    @FXML
    private ComboBox<String> cmbItemd;

    @FXML
    private ComboBox<String> cmbPay;

    @FXML
    private ComboBox<String> cmbSupId;

    @FXML
    private Label date;
    @FXML
    private Label date1;


    @FXML
    private Label lblPayAmount;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblSupplerName;

    @FXML
    private Label lblItemQty;
    @FXML
    private  Label lblItemPrice;
    @FXML
    private TextField txtQty;

    @FXML
    private TableColumn<String, CashBookTM> colAmount;

    @FXML
    private TableColumn<String, CashBookTM> colCBno;

    @FXML
    private TableColumn<String, CashBookTM> colDate;

    @FXML
    private TableColumn<String, CashBookTM> colDese;

    @FXML
    private TableColumn<String, CashBookTM> colPrice;


    @FXML
    private TableColumn<String, CashBookTM> colQty;

    @FXML
    private TableColumn<String, CashBookTM> colSupId;

    @FXML
    private TableView<CashBookTM> tblCshBook;



    private SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
    private SimpleDateFormat daterun = new SimpleDateFormat("yyyy-MM-dd");
    private final ObservableList<CashBookTM> CashBookTMS = FXCollections.observableArrayList();

    private SimpleDateFormat datE = new SimpleDateFormat("yyyy-MM-dd");
    SuplierModel suplierModel = new SuplierModel();
    MatiralMoadel matiralModel = new MatiralMoadel();

    @FXML
    public void initialize() throws SQLException {
        setCellValues();
        updateDateLabel();
        startClock();
        loadSupplierId();
        loadItemId();
        getAllAmount();
        loadNextCBNOId();
        LoadTabel();



    }
    @FXML
    private Label lblCBN;

    private void loadNextCBNOId() throws SQLException {
        String nextCBNOId = cashBookModel.getNextCBNId();
        lblCBN.setText(nextCBNOId);
        System.out.println(nextCBNOId);
    }

    private void setCellValues() {

        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colCBno.setCellValueFactory(new PropertyValueFactory<>("CBNo"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDese.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colSupId.setCellValueFactory(new PropertyValueFactory<>("SupId"));

    }
    public void LoadTabel() throws SQLException {
        ArrayList<CashBookDto> cashBookDtos = cashBookModel.getAllCustomers();

        ObservableList<CashBookTM> cashBookTMS = FXCollections.observableArrayList();

        for (CashBookDto cashBookDto : cashBookDtos) {
            CashBookTM cashBookTM = new CashBookTM(
                    cashBookDto.getCBNo(),
                    cashBookDto.getSupId(),
                    cashBookDto.getDate(),
                    cashBookDto.getDesc(),
                    cashBookDto.getQty(),
                    cashBookDto.getAmount()

            );
            cashBookTMS.add(cashBookTM);
        }
        tblCshBook.setItems(cashBookTMS);

    }
    @FXML
    private TextField txtCashBookID;


    CashBookModel cashBookModel = new CashBookModel();

    @FXML
    void btnPlaceIt(ActionEvent event) throws SQLException {
        loadNextInventryId();
        loadNextCBNOId();

        String selectedItemId = cmbItemd.getValue();


        if (selectedItemId == null) {
            new Alert(Alert.AlertType.ERROR, "Please select item..!").show();
            return;
        }

        String seltctedSupId = cmbSupId.getValue();
        if (seltctedSupId == null) {
            new Alert(Alert.AlertType.ERROR, "Please select supId..!").show();
            return;
        }


        String qtyV = txtQty.getText().trim();
        String avQText = lblItemQty.getText().trim();
        String qtyPattern = "^[0-9]+$";

        if (qtyV.isEmpty() || avQText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "quantity cannot be empty..!").show();
            return;
        }

        if (!qtyV.matches(qtyPattern)) {
            new Alert(Alert.AlertType.ERROR, "Please enter a valid numeric quantity..!").show();
            return;
        }

        try {
            int qtyr = Integer.parseInt(qtyV);
            int avQ = Integer.parseInt(avQText);

            if (qtyr == 0) {
                new Alert(Alert.AlertType.ERROR, "quantity cannot be zero..!").show();
                return;
            }

            if (avQ < qtyr) {
                new Alert(Alert.AlertType.ERROR, "Not enough items..!").show();
                return;
            }


        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred while processing the quantity. Please check the input..!").show();
        }


        double price = Double.parseDouble(lblItemPrice.getText());

        String CBNo = lblCBN.getText();
        String SupId = cmbSupId.getSelectionModel().getSelectedItem();
        String desc = lblItemName.getText();
        int qty = Integer.parseInt(qtyV);
        double amount = price * qty;
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String matID = cmbItemd.getSelectionModel().getSelectedItem();
        String inID = invID;
        String itemType = "Raw";
        String Prod_id =  "----";



        CashBookDto cashBookDtos = new CashBookDto(
                CBNo,
                SupId,
                date,
                desc,
                qty,
                amount,
                matID,
                inID,
                itemType,
                Prod_id
        );

        boolean isSaved = cashBookModel.saveResept(cashBookDtos);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Saved successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Save failed! Please try again.").show();
        }
    }
    @FXML
    void amount(ActionEvent event) throws SQLException {
        loadNextCBNOId();
    }
    public void getAllAmount() throws SQLException {
        int am = cashBookModel.getAllPayAmount();
        System.out.println(am);
        lblPayAmount.setText(String.valueOf(am));
    }

    InventroyModel inventroyModel = new InventroyModel();
    String invID;

    public void loadNextInventryId() throws SQLException {
        String nextInventryId = inventroyModel.getInventroyId();
        invID = nextInventryId;
        System.out.println(nextInventryId);
    }




    private void loadSupplierId() throws SQLException {
        ArrayList<String> supplierIds = suplierModel.getAllSupIds();
        ObservableList<String> observableList = FXCollections.observableArrayList(supplierIds);
        cmbSupId.setItems(observableList);
    }

    private void loadItemId() throws SQLException {
        ArrayList<String>  ItemIds = matiralModel.getAllItemIds();
        ObservableList<String> observableList = FXCollections.observableArrayList(ItemIds);
        cmbItemd.setItems(observableList);
    }



    @FXML
    void cmbItemOnAction(ActionEvent event) throws SQLException {
        String selectID = (String) cmbItemd.getSelectionModel().getSelectedItem();

        MatirialDto matirialDto = matiralModel.findById(selectID);
        if (matirialDto != null) {

            lblItemName.setText(matirialDto.getMatName());
            lblItemPrice.setText(String.valueOf(matirialDto.getMatPrice()));
            lblItemQty.setText(String.valueOf(matirialDto.getMatQty()));


        }
    }

    @FXML
    void cmbSupOnAction(ActionEvent event) throws SQLException {
        String selectID = (String) cmbSupId.getSelectionModel().getSelectedItem();

        SuplierDto suplierDto = suplierModel.findById(selectID);
        if (suplierDto != null) {
            lblSupplerName.setText(suplierDto.getSupName());
        }

    }








    // date manage
    private void updateDateLabel() {
        String currentTime = dateFormat.format(new Date());
        date.setText(currentTime);
        String currentdate = daterun.format(new Date());
        date1.setText(currentdate);
    }

    private void startClock() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateDateLabel()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    void dat(ActionEvent event) {
        System.out.println(datE.format(new Date()));
    }

}


