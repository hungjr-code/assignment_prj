/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kAwa
 */
public class DBUtil {

    private static final String DB_NAME = "ONLINE_LANGUAGE_CENTER";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "12345";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=" + DB_NAME;
        Connection cn = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
        return cn;
    }
}
