package day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Entry {

    public static void main(String[] args) {
        Connection dbConnection = null;
        Statement stSelect = null;
        ResultSet result = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(dbURL, username, pwd);
            System.out.println("connection successfull!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}