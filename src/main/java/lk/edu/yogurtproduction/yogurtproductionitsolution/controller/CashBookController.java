package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;


import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CashBookController {

    @FXML
    private Label date;
    @FXML
    private Label date1;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblSupplerName;

    @FXML
    private TextField txtQty;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
    private SimpleDateFormat daterun = new SimpleDateFormat("yyyy-MM-dd");

    private SimpleDateFormat datE = new SimpleDateFormat("yyyy-MM-dd");

    @FXML
    public void initialize() {
        updateDateLabel();
        startClock();
    }
    @FXML
    void btnPlaceIt(ActionEvent event) {

    }



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
