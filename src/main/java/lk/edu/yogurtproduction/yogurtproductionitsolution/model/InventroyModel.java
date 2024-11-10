package lk.edu.yogurtproduction.yogurtproductionitsolution.model;
import  java.sql.Connection;
import java.sql.SQLException;
import lk.edu.yogurtproduction.yogurtproductionitsolution.db.DBConnection;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.CashBookDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.ProdtionDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                "INSERT INTO Inventory VALUES (?, ?, ?, ?)",
                cashBookDto.getInID(),
                cashBookDto.getItemType(),
                cashBookDto.getDesc(),
                cashBookDto.getQty()

        );

    }

    public boolean redusqtyOnInventroy(ProdtionDto prodtionDto) throws SQLException {

        boolean milkUpdated = CrudUtil.execute("UPDATE inventory SET Qty = Qty - ? WHERE Item_Description = ?", prodtionDto.getP_milk(), "Milk");
        boolean gelatinUpdated = CrudUtil.execute("UPDATE inventory SET Qty = Qty - ? WHERE Item_Description = ?", prodtionDto.getP_jeley(), "Gelatin");
        boolean sugarUpdated = CrudUtil.execute("UPDATE inventory SET Qty = Qty - ? WHERE Item_Description = ?", prodtionDto.getP_suguer(), "Sugar");

        if (milkUpdated && gelatinUpdated && sugarUpdated) {
            return true;
        } else {
            return false;
        }
    }




    }



