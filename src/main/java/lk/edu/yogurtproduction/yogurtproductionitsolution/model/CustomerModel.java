package lk.edu.yogurtproduction.yogurtproductionitsolution.model;

import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.CustomerDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public ArrayList<String> getAllCustomerIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("select cust_id from customer");

        ArrayList<String> customerIds = new ArrayList<>();

        while (rst.next()) {
            customerIds.add(rst.getString(1));
        }

        return customerIds;
    }

    public CustomerDto findById(String selectedCustomerId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from customer where cust_id=?", selectedCustomerId);

        if (rst.next()) {
            return new CustomerDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        }
        return null;
    }
}
