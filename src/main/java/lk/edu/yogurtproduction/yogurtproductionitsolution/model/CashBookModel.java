package lk.edu.yogurtproduction.yogurtproductionitsolution.model;

import lk.edu.yogurtproduction.yogurtproductionitsolution.db.DBConnection;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.CashBookDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class CashBookModel {

    InventroyModel inventoryModel = new InventroyModel();
    MatiralMoadel materialModel = new MatiralMoadel();

    public Boolean saveResept(CashBookDto cashBookDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            boolean isRecpSaved = CrudUtil.execute(
                    "INSERT INTO Cash_Book VALUES (?, ?, ?, ?, ?, ?, ?)",
                    cashBookDto.getCBNo(),
                    cashBookDto.getSupId(),
                    cashBookDto.getMatID(),
                    cashBookDto.getDesc(),
                    cashBookDto.getQty(),
                    cashBookDto.getAmount(),
                    cashBookDto.getDate()
            );

            if (isRecpSaved) {
                 boolean isInventroyUpdated = inventoryModel.saveInvetory(cashBookDto);
                  if (isInventroyUpdated) {

                      boolean isMatirealUpdated = materialModel.updatedMatirialReduceQty(cashBookDto);
                      if (isMatirealUpdated) {
                          connection.commit();
                          return true;
                      }

                  }
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


