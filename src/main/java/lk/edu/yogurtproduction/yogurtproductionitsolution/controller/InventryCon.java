package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.edu.yogurtproduction.yogurtproductionitsolution.db.DBConnection;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.InventroyDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.TM.InventryTM;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.InventroyModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class InventryCon implements Initializable {

    @FXML
    private TableColumn<String, InventryTM> colDesc;

    @FXML
    private TableColumn<String, InventryTM> colInID;

    @FXML
    private TableColumn<String, InventryTM> colQty;

    @FXML
    private TableColumn<String, InventryTM> colType;

    @FXML
    private TableView<InventryTM> tblInventroy;


    public void initialize(URL location, ResourceBundle resources) {
        colInID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("itemType"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        try {
            loadTble();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    InventroyModel invModel = new InventroyModel();
    private void loadTble() throws SQLException {
        ArrayList<InventroyDto> inventryDTOS = invModel.getAllInventoryData();

        ObservableList<InventryTM> inventryTMS = FXCollections.observableArrayList();


        for (InventroyDto inventroyDto : inventryDTOS) {
            InventryTM inventryTM = new InventryTM(
                    inventroyDto.getId(),

                    inventroyDto.getItemType(),

                    inventroyDto.getItemDescription(),
                    inventroyDto.getQty(),
                    inventroyDto.getProdId()
            );
            inventryTMS.add(inventryTM);
        }

        tblInventroy.setItems(inventryTMS);

    }




    @FXML
    void btnALLReportIN(ActionEvent event) throws SQLException {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            Map<String, Object> parameters = new HashMap<>();


            JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/report/inventory.jrxml"));

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
                    connection
            );


            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load report..!");
            e.printStackTrace();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Data empty..!");
            e.printStackTrace();
        }
    }

}
