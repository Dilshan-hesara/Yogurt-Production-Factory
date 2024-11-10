package lk.edu.yogurtproduction.yogurtproductionitsolution.model;

import lk.edu.yogurtproduction.yogurtproductionitsolution.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdtionModel {
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
}
