package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.CashBookModel;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.MatirialUsageModel;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.StockModel;
import lk.edu.yogurtproduction.yogurtproductionitsolution.util.CrudUtil;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class DashBoadMain implements Initializable {


        @FXML
        private PieChart chartProduction;

        @FXML
        private AreaChart<String, Number> chartStock;

        @FXML
        private Text txtDate;

        @FXML
        private Text txtTime;

        @FXML
        private Text txtUser;

    @FXML
    private Label lblMatUsage;

    @FXML
    private Label lblProd;

    @FXML
    private Label lblbAmount;



    @SneakyThrows
    @Override
        public void initialize(URL location, ResourceBundle resources) {
        
        loadChartData();
        startClock();
        addYogurtStockData();
        try {
            LoadLbl();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void LoadLbl() throws SQLException {
        laodAount();
        laodMatUsage();
        loadprod();
 
    }

    @FXML
    private Label lblProdAv;

    StockModel stockModel = new StockModel();

    private void loadprod() throws SQLException {
        int pr = (int) stockModel.getAllProdAvg();
        String prd = pr +" %";
        lblProdAv.setText(String.valueOf(prd));
    }

    MatirialUsageModel matirialUsageModel = new MatirialUsageModel();
    private void laodMatUsage() throws SQLException {
        Double us = (double) matirialUsageModel.getAllUsageAvg();
        String usa = us+" %";
        lblMatUsage.setText(String.valueOf(usa));

    }

    CashBookModel cashBookModel = new CashBookModel();
    private void laodAount() throws SQLException {
        int am = cashBookModel.getAllPayAmount();
        String Am = "LKR."+am;
        lblbAmount.setText(String.valueOf(Am));

    }

    public void btnEditAcc(ActionEvent actionEvent) {

        //addYogurtStockData()
    }


    public void loadChartData() {

        try {
            String query = "SELECT Item_Type, SUM(Qty) AS TotalQty " +
                    "FROM inventory " +
                    "WHERE Item_Type IN ('Raw', 'UN Packed', 'END Prodt') " +
                    "GROUP BY Item_Type";

            ResultSet rs = CrudUtil.execute(query);

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

            while (rs.next()) {
                String itemType = rs.getString("Item_Type");
                int totalQty = rs.getInt("TotalQty");

                pieChartData.add(new PieChart.Data(itemType + " (" + totalQty + ")", totalQty));
            }

            chartProduction.setData(pieChartData);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
    private SimpleDateFormat daterun = new SimpleDateFormat("MMMM dd, yyyy");

    private void updateDateLabel() {


        String currentTime = dateFormat.format(new Date()).toUpperCase();

        txtTime.setText(currentTime);
        String currentdate = daterun.format(new Date());
        txtDate.setText(currentdate);
    }

    private void startClock() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateDateLabel()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }






    private void addYogurtStockData() {

        try {
            String Qury = "SELECT Manfac_date, SUM(Qty) AS total_qty "
                    + "FROM Stock "
                    + "GROUP BY Manfac_date "
                    + "ORDER BY Manfac_date";

            ResultSet rs = CrudUtil.execute(Qury);

            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis(0, 12, 1);
            AreaChart<String, Number> areaChart = new AreaChart<>(xAxis, yAxis);

            XYChart.Series<String, Number> yogurtStockSeries = new XYChart.Series<>();
        //    yogurtStockSeries.setName("Yogurt Stock by Manufacture Date");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            while (rs.next()) {
                Date manfacDate = rs.getDate("Manfac_date");
                double totalQty = rs.getDouble("total_qty");
                String formattedDate = dateFormat.format(manfacDate);
               yogurtStockSeries.getData().add(new XYChart.Data<>(formattedDate, totalQty));
            }

           areaChart.getData().add(yogurtStockSeries);

            chartStock.getData().clear();
           chartStock.getData().addAll(yogurtStockSeries);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    String userName;

    public void setUserName(String userName) {

        this.userName = userName;
        txtUser.setText(userName);

    }
}




