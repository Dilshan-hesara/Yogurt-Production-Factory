package lk.edu.yogurtproduction.yogurtproductionitsolution.model;

import lk.edu.yogurtproduction.yogurtproductionitsolution.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MatiralMoadel {


    public String getNextMatId() throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select Mat_ID from material order by Mat_ID desc limit 1";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rst = pst.executeQuery();

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(2); // Updated to substring(2) to remove "EM"
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;

            return String.format("MT%03d", newIdIndex);
        }
        return "MT001";
    }

}
