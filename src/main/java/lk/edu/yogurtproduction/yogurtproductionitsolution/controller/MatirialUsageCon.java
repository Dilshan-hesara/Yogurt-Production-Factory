package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.MatirialUsageDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.TM.MatirialUsageTM;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.MatirialUsageModel;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class MatirialUsageCon {

    @FXML
    private TableColumn<String, MatirialUsageTM> cloMatID;

    @FXML
    private TableColumn<String, MatirialUsageTM> cloMilk;

    @FXML
    private TableColumn<String, MatirialUsageTM> colGela;

    @FXML
    private TableColumn<String, MatirialUsageTM> colProdID;

    @FXML
    private TableColumn<String, MatirialUsageTM> colSu;

    @FXML
    private TableView<MatirialUsageTM> tblMatUsage;


    public void initialize(){
        cloMatID.setCellValueFactory(new PropertyValueFactory<>("MatUs_ID"));
        colProdID.setCellValueFactory(new PropertyValueFactory<>("Prod_ID"));
        cloMilk.setCellValueFactory(new PropertyValueFactory<>("Mat_Milk"));
        colSu.setCellValueFactory(new PropertyValueFactory<>("Mat_Suguer"));
        colGela.setCellValueFactory(new PropertyValueFactory<>("Mat_Gelatin"));

        try {
            loadTble();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    MatirialUsageModel matirialUsageModel = new MatirialUsageModel();
    private void loadTble() throws SQLException {

        ArrayList<MatirialUsageDto> matirialUsageDTOS = matirialUsageModel.getAllMatUsageData();

        ObservableList<MatirialUsageTM> matirialUsageTMS = FXCollections.observableArrayList();


        for (MatirialUsageDto matirialUsageDto : matirialUsageDTOS) {
            MatirialUsageTM matirialUsageTM = new MatirialUsageTM(
                    matirialUsageDto.getMatUs_ID(),
                    matirialUsageDto.getProd_ID(),
                    matirialUsageDto.getMat_Milk(),
                    matirialUsageDto.getMat_Suguer(),
                    matirialUsageDto.getMat_Gelatin()

            );
            matirialUsageTMS.add(matirialUsageTM);
        }

        tblMatUsage.setItems(matirialUsageTMS);


    }

    public void btnALLReportIN(javafx.event.ActionEvent actionEvent) {
    }
}
