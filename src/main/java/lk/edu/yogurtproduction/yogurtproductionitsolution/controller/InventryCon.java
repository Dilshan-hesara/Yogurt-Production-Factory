package lk.edu.yogurtproduction.yogurtproductionitsolution.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.InventroyDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.TM.InventryTM;
import lk.edu.yogurtproduction.yogurtproductionitsolution.model.InventroyModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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

    }

}
