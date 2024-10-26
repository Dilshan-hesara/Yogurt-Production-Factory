package lk.edu.yogurtproduction.yogurtproductionitsolution.db;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class DBConnection {
        private static DBConnection instance;

        @Getter
        private final Connection connection;

        private DBConnection() throws SQLException {
            String URL = "jdbc:mysql://localhost:3306/yougurtprodution";
            String USER = "root";
            String PASSWORD = "18091479";
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }

        public static DBConnection getInstance() throws SQLException {
            return instance == null ? instance = new DBConnection() : instance;
        }
}
