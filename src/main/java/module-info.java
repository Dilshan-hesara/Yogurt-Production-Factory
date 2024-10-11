module lk.edu.yogurtproduction.yogurtproductionitsolution {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens lk.edu.yogurtproduction.yogurtproductionitsolution.controller to javafx.fxml;
    exports lk.edu.yogurtproduction.yogurtproductionitsolution;
}