package lk.edu.yogurtproduction.yogurtproductionitsolution.model;

import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.UserDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.util.CrudUtil;

import java.sql.SQLException;

public class UserModel {
    public static boolean createUser(UserDto user) throws SQLException {


        return   CrudUtil.execute(

                "insert into user values (?, ?, ?)",
                user.getUsername(),
                user.getEmail(),
                user.getPassword()


        );

    }
}
