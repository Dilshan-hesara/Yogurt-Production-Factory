package lk.edu.yogurtproduction.yogurtproductionitsolution.model;

import lk.edu.yogurtproduction.yogurtproductionitsolution.db.DBConnection;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.EmployeeDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.TM.EmployeeTM;
import lk.edu.yogurtproduction.yogurtproductionitsolution.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeModel {

    public String getNextCustomerId() throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT Emp_ID FROM employee ORDER BY Emp_ID DESC LIMIT 1";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rst = pst.executeQuery();

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(2); // Updated to substring(2) to remove "EM"
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;

            return String.format("EM%03d", newIdIndex);
        }

// Return EM001 if no IDs are found
        return "EM001";


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


    public ArrayList<EmployeeDto> getAllEmployees() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select * from employee";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rst = statement.executeQuery();

        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();

        while (rst.next()) {
            EmployeeDto employeeDto = new EmployeeDto(
                    rst.getString("Emp_ID"),
                    rst.getString("Emp_Name"),
                    rst.getString("Emp_Nic"),
                    rst.getString("Emp_Email"),
                    rst.getString("Emp_Phone")
            );
            employeeDtos.add(employeeDto);
        }

        return employeeDtos;
    }



    public boolean updateCustomer(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException{
        String sql = "UPDATE employee SET Emp_Name = ?, Emp_Nic = ?, Emp_Email = ?, Emp_Phone = ? WHERE Emp_ID = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql); {

            statement.setString(1, employeeDto.getEmpName());
            statement.setString(2, employeeDto.getEmpNic());
            statement.setString(3, employeeDto.getEmpEmail());
            statement.setString(4, employeeDto.getEmpPhone());
            statement.setString(5, employeeDto.getEmpId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;


        }

    }





    public boolean deleteCustomer(String empId) throws SQLException {
        return CrudUtil.execute("delete from employee where Emp_ID=?", empId);

    }

    public ArrayList<String> getAllEmpIds() throws SQLException {

        ResultSet rst = CrudUtil.execute("SELECT Emp_ID FROM Employee");

        ArrayList<String> EmpIds = new ArrayList<>();

        while (rst.next()) {
            EmpIds.add(rst.getString(1));
        }

        return EmpIds;
    }
}
