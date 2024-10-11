module lk.edu.yogurtproduction.yogurtproductionitsolution {
    requires javafx.controls;
    requires javafx.fxml;


    opens lk.edu.yogurtproduction.yogurtproductionitsolution.controller to javafx.fxml;
    exports lk.edu.yogurtproduction.yogurtproductionitsolution;
}