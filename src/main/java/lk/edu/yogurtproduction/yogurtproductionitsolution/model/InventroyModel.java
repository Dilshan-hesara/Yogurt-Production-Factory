package lk.edu.yogurtproduction.yogurtproductionitsolution.model;
import java.sql.SQLException;

import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.CashBookDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.PckingDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.ProdtionDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.TM.InventryTM;
import lk.edu.yogurtproduction.yogurtproductionitsolution.util.CrudUtil;

import java.sql.ResultSet;
import java.util.ArrayList;

public class InventroyModel {

    public String getInventroyId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select In_ID from inventory order by In_ID desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(3);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("INV%03d", newIdIndex);
        }
        return "INV001";
    }

    public boolean saveInvetory(CashBookDto cashBookDto) throws SQLException {

        return   CrudUtil.execute(
                "INSERT INTO Inventory VALUES (?, ?, ?, ?, ?)",
                cashBookDto.getInID(),
                cashBookDto.getItemType(),
                cashBookDto.getDesc(),
                cashBookDto.getQty(),
                cashBookDto.getProd_id()

        );

    }

    public boolean redusqtyOnInventroy(ProdtionDto prodtionDto) throws SQLException {


        boolean milkUpdated = CrudUtil.execute("UPDATE inventory i JOIN (SELECT In_ID FROM inventory WHERE Item_Description = 'Milk' AND Qty > 0 LIMIT 1) subquery ON i.In_ID = subquery.In_ID SET i.Qty = i.Qty - ?", prodtionDto.getP_milk());
        boolean gelatinUpdated = CrudUtil.execute("UPDATE inventory i JOIN (SELECT In_ID FROM inventory WHERE Item_Description = 'Gelat' AND Qty > 0 LIMIT 1) subquery ON i.In_ID = subquery.In_ID SET i.Qty = i.Qty - ?", prodtionDto.getP_jeley());
        boolean sugarUpdated = CrudUtil.execute("UPDATE inventory i JOIN (SELECT In_ID FROM inventory WHERE Item_Description = 'Sugar' AND Qty > 0 LIMIT 1) subquery ON i.In_ID = subquery.In_ID SET i.Qty = i.Qty - ?", prodtionDto.getP_suguer());

        if (milkUpdated && gelatinUpdated && sugarUpdated) {
            return true;
        } else {
            return false;
        }
    }


    public boolean saveInvetoryPack(PckingDto pckingDtos) throws SQLException {

        return   CrudUtil.execute(
                "INSERT INTO Inventory VALUES (?, ?, ?, ?, ?)",
                pckingDtos.getInID(),
                pckingDtos.getItemType(),
                pckingDtos.getPac_Desc(),
                pckingDtos.getQty(),
                pckingDtos.getProd_ID()

        );
    }

    public boolean saveUpdated(ProdtionDto prodtionDto) throws SQLException {


        return   CrudUtil.execute(
                "INSERT INTO Inventory VALUES (?, ?, ?, ?, ?)",
                prodtionDto.getInID(),
                prodtionDto.getItemType(),
                prodtionDto.getPro_Name(),
                prodtionDto.getProd_Qty(),
                prodtionDto.getProd_ID()

        );    }

    public boolean saveredusPackedQty(PckingDto pckingDtos) throws SQLException {

        return   CrudUtil.execute(
                "UPDATE inventory set Qty = Qty - ? where Prod_ID = ?",
                pckingDtos.getRedusQty(),
                pckingDtos.getProd_ID()

        );

    }

    public int AvQtyFromSelectProd_ID(String getSelectedProdId) throws SQLException {

        ResultSet rst = CrudUtil.execute(
                "SELECT Qty FROM inventory WHERE Prod_ID = ?",
                getSelectedProdId
        );

        if (rst.next()) {

            return rst.getInt("Qty");
        } else {
            return 0;
        }
    }

    public ArrayList<InventryTM> getAlldata() throws SQLException {

        ResultSet rst = CrudUtil.execute("SELECT In_ID, Item_Type, Item_Description, Qty, Prod_ID FROM inventory");

        ArrayList<InventryTM> inventoryList = new ArrayList<>();

        while (rst.next()) {
            String id = rst.getString("In_ID");
            String itemType = rst.getString("Item_Type");
            String itemDescription = rst.getString("Item_Description");
            String qty = rst.getString("Qty");
            String prodId = rst.getString("Prod_ID");

            InventryTM inventryTM = new InventryTM(id, itemType, itemDescription, qty, prodId);
            inventoryList.add(inventryTM);
        }

        return inventoryList;
    }


}



