package lk.edu.yogurtproduction.yogurtproductionitsolution.model;

import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.StockDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockModel {
//    public boolean saveStock(PckingDto pckingDtos) throws SQLException {
//
//        return   CrudUtil.execute(
//                "INSERT INTO Stock VALUES (?, ?, ?, ?,?,?,?)",
//                pckingDtos.getStID(),
//                pckingDtos.getPac_ID(),
//                pckingDtos.getPac_Desc(),
//                pckingDtos.getQty(),
//                pckingDtos.getPac_Date(),
//                pckingDtos.getExpire_Date(),
//                pckingDtos.getPac_Type()
//
//        );
//    }

    public String getStockId() throws SQLException {

        ResultSet rst = CrudUtil.execute("select Stock_ID from Stock order by Stock_ID desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(3);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("ST%03d", newIdIndex);
        }
        return "ST001";

    }

    public boolean saveStock(ArrayList<StockDto> stockDTOS) throws SQLException {
        for (StockDto stockDTO : stockDTOS) {
            boolean isSaved = savedStock(stockDTO);

            if (!isSaved) {
                return false;
            }
        }
//
//        boolean isMatirealUpdated = materialModel.updatedMatirialReduceQty(cashBookDto);
//        if (isMatirealUpdated) {
//
//      return false;
//
//
//        }
        return true;
    }

    private boolean savedStock(StockDto stockDTO) throws SQLException {
        return   CrudUtil.execute(

                "insert into Stock values (?, ?, ?, ?,?,?,?)",
                stockDTO.getStock_ID(),
                stockDTO.getPac_ID(),
                stockDTO.getProduct_Name(),
                stockDTO.getQty(),
                stockDTO.getManfac_date(),
                stockDTO.getExpire_date(),
                stockDTO.getPack_Type()

        );

    }
}
