package lk.edu.yogurtproduction.yogurtproductionitsolution.model;

import lk.edu.yogurtproduction.yogurtproductionitsolution.db.DBConnection;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.PckingDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.util.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class PackingModel {
    public boolean savePacking(PckingDto pckingDtos) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            boolean isRecpSaved = CrudUtil.execute(
                    "INSERT INTO packing VALUES (?, ?, ?, ?, ?, ?, ?,?)",
                    pckingDtos.getPac_ID(),
                    pckingDtos.getProd_ID(),
                    pckingDtos.getPac_Type(),
                    pckingDtos.getPac_Desc(),
                    pckingDtos.getPac_Date(),
                    pckingDtos.getExpire_Date(),
                    pckingDtos.getQty(),
                    pckingDtos.getEmp_ID()
            );

            if (isRecpSaved) {
                System.out.println("savesd");
                connection.commit();
                return true;


            }

            connection.rollback();
            return false;
        } catch(SQLException e){
            connection.rollback();
            e.printStackTrace();
            return false;
        } finally{
            connection.setAutoCommit(true);
        }

    }
}
