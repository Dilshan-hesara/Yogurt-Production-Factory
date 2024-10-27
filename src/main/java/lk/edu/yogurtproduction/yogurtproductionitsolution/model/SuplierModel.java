package lk.edu.yogurtproduction.yogurtproductionitsolution.model;

import lk.edu.yogurtproduction.yogurtproductionitsolution.db.DBConnection;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.SuplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SuplierModel {
    public String getNextSuplierId() throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select Sup_ID from supplier order by Sup_ID desc limit 1";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rst = pst.executeQuery();

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(2);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;

            return String.format("SU%03d", newIdIndex);
        }

        return "SU001";
    }

    public boolean saveSuplier(SuplierDto suplierDTO) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "insert into  supplier values (?,?,?,?,?)";
        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setObject(1,suplierDTO.getSupId());
        pst.setObject(2,suplierDTO.getSupName());
        pst.setObject(3,suplierDTO.getSupNic());
        pst.setObject(4,suplierDTO.getSupEmail());
        pst.setObject(5,suplierDTO.getSupPhone());
        int result = pst.executeUpdate();
        boolean isSaved = result>0;
        return isSaved;
    }

    public ArrayList<SuplierDto> getAllSuplier() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select * from supplier";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rst = statement.executeQuery();
        ArrayList<SuplierDto> suplierDtos = new ArrayList<>();
        while (rst.next()) {
            SuplierDto suplierDto = new SuplierDto(
                    rst.getString("Sup_ID"),
                    rst.getString("Sup_Name"),
                    rst.getString("Sup_Nic"),
                    rst.getString("Sup_Email"),
                    rst.getInt("Sup_Phone")
            );
            suplierDtos.add(suplierDto);
        }

        return suplierDtos;
    }


}
