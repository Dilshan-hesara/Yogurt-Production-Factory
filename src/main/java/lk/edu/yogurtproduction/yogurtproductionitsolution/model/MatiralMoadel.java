package lk.edu.yogurtproduction.yogurtproductionitsolution.model;

import lk.edu.yogurtproduction.yogurtproductionitsolution.db.DBConnection;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.MatirialDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MatiralMoadel {


    public String getNextMatId() throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select Mat_ID from material order by Mat_ID desc limit 1";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rst = pst.executeQuery();

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(2);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;

            return String.format("MT%03d", newIdIndex);
        }
        return "MT001";
    }

    public boolean saveMatirial(MatirialDto matirialDto) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "insert into material values (?,?,?,?)";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setObject(1,matirialDto.getMatId());
        pst.setObject(2,matirialDto.getMatName());
        pst.setObject(3,matirialDto.getMatQty());
        pst.setObject(4,matirialDto.getMatPrice());

        int result = pst.executeUpdate();
        boolean isSaved = result>0;
        return isSaved;

    }

    public ArrayList<MatirialDto> getAllMatireal() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select * from material";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rst = statement.executeQuery();

        ArrayList<MatirialDto>  matirialDtos = new ArrayList<>();

        while (rst.next()) {
            MatirialDto matirialDto = new MatirialDto(
                    rst.getString("Mat_ID"),
                    rst.getString("Mat_Name"),
                    rst.getInt("Qty"),
                    rst.getInt("Price")
            );
            matirialDtos.add(matirialDto);

        }
        return matirialDtos;


    }

    public boolean deleteMatirial(String matId) throws SQLException {

        return  CrudUtil.execute("delete from material where Mat_ID=?", matId);



    }

    public boolean updateMatirial(MatirialDto matirialDto) throws SQLException {

        return CrudUtil.execute(
                "update material set Mat_Name=?, Qty=?, Price=? where Mat_ID=?",
                matirialDto.getMatId(),
                matirialDto.getMatName(),
                matirialDto.getMatQty(),
                matirialDto.getMatPrice()

        );
    }

    public MatirialDto findById(String selectID) throws SQLException {

        ResultSet rst = CrudUtil.execute("select * from material where Mat_ID=?", selectID);

        if (rst.next()) {
            return new MatirialDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4)
            );
        }
        return null;
    }

    public ArrayList<String> getAllItemIds() throws SQLException {


        ResultSet rst = CrudUtil.execute("SELECT Mat_ID FROM material");

        // Create an ArrayList to store the item IDs
        ArrayList<String> itemIds = new ArrayList<>();

        // Iterate through the result set and add each item ID to the list
        while (rst.next()) {
            itemIds.add(rst.getString(1));
        }

        // Return the list of item IDs
        return itemIds;
    }
}
