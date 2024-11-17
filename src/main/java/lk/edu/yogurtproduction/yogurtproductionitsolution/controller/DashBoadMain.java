package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lk.edu.yogurtproduction.yogurtproductionitsolution.util.CrudUtil;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class DashBoadMain implements Initializable {


        @FXML
        private PieChart chartProduction;

        @FXML
        private AreaChart<String, Number> chartTeaStock;

        @FXML
        private Text txtDate;

        @FXML
        private Text txtTime;

        @FXML
        private Text txtUser;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            startClock();
            addYogurtStockData();

        }


    private SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a"); // Time format (12-hour with AM/PM)
    private SimpleDateFormat daterun = new SimpleDateFormat("MMMM dd, yyyy");
    // date manage
    private void updateDateLabel() {


        String currentTime = dateFormat.format(new Date()).toUpperCase();  // Ensure AM/PM are uppercase

        txtTime.setText(currentTime);
        String currentdate = daterun.format(new Date());
        txtDate.setText(currentdate);
    }

    private void startClock() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateDateLabel()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }



    public void testbtn(ActionEvent actionEvent) {

       //addYogurtStockData();
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
            yogurtStockSeries.setName("Yogurt Stock by Manufacture Date");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            while (rs.next()) {
                Date manfacDate = rs.getDate("Manfac_date");
                double totalQty = rs.getDouble("total_qty");
                String formattedDate = dateFormat.format(manfacDate);
               yogurtStockSeries.getData().add(new XYChart.Data<>(formattedDate, totalQty));
            }

           areaChart.getData().add(yogurtStockSeries);

            chartTeaStock.getData().clear();
           chartTeaStock.getData().addAll(yogurtStockSeries);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}




