module lk.edu.yogurtproduction.yogurtproductionitsolution {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires static lombok;
    requires java.sql;


    opens lk.edu.yogurtproduction.yogurtproductionitsolution.controller to javafx.fxml;
    exports lk.edu.yogurtproduction.yogurtproductionitsolution;
}