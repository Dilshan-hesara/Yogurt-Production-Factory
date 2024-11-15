package lk.edu.yogurtproduction.yogurtproductionitsolution.model;

import lk.edu.yogurtproduction.yogurtproductionitsolution.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MatirialUsageModel {
    public String getmatirialUsageId() throws SQLException {

            ResultSet rst = CrudUtil.execute("select MatUs_ID from material_usage order by MatUs_ID desc limit 1");
            if (rst.next()) {
                String lastId = rst.getString(1);
                String substring = lastId.substring(3);
                int i = Integer.parseInt(substring);
                int newIdIndex = i + 1;
                return String.format("MATU%03d", newIdIndex);
            }
            return "MATU001";
        }
    }
