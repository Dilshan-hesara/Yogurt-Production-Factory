package lk.edu.yogurtproduction.yogurtproductionitsolution.model;

import lk.edu.yogurtproduction.yogurtproductionitsolution.db.DBConnection;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.ProdtionDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdtionModel {

    InventroyModel inventoryModel = new InventroyModel();
    public String getNextProdtID() throws SQLException {
        ResultSet rst = CrudUtil.execute("select Prod_ID from production order by Prod_ID desc limit 1");
        if (rst.next()){
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i+1;
            return String.format("P%03d",newIdIndex);
        }
        return  "P001";
    }

    public boolean saveProdt(ProdtionDto prodtionDto) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            boolean isProdtSaved = CrudUtil.execute(
                    "insert into production values (?,?,?,?)",
                    prodtionDto.getProd_ID(),
                    prodtionDto.getPro_Name(),
                    prodtionDto.getProd_Qty(),
                    prodtionDto.getProd_Name()
            );
            if (isProdtSaved) {
                System.out.println("dev");
                boolean isUpdateInverorySaved = inventoryModel.redusqtyOnInventroy(prodtionDto);
               if (isUpdateInverorySaved) {
                   connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }

    }

    public ArrayList<String> getAllProdtIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT Prod_ID FROM production");

        ArrayList<String> ProdtIds = new ArrayList<>();

        while (rst.next()) {
            ProdtIds.add(rst.getString(1));
        }

        return ProdtIds;
    }
}
