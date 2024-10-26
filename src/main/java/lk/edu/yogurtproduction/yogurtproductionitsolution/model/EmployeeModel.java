package lk.edu.yogurtproduction.yogurtproductionitsolution.model;

import lk.edu.yogurtproduction.yogurtproductionitsolution.db.DBConnection;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeModel {

    public String getNextCustomerId() throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select Emp_ID from employee order by Emp_ID desc limit 1";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rst = pst.executeQuery();
        if (rst.next()){
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i+1;

            return String.format("E%03d",newIdIndex);
        }
        return  "EM001";
    }

    public boolean saveEmpoyee(EmployeeDto employeeDto) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "insert into employee values (?,?,?,?,?)";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setObject(1,employeeDto.getEmpId());
        pst.setObject(2,employeeDto.getEmpName());
        pst.setObject(3,employeeDto.getEmpNic());
        pst.setObject(4,employeeDto.getEmpEmail());
        pst.setObject(5,employeeDto.getEmpPhone());
        int result = pst.executeUpdate();
        boolean isSaved = result>0;
        return isSaved;
    }
}
