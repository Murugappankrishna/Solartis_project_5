package org.murugappan.DAO;

import org.murugappan.repo.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCredentialsImpl implements UserCredentials {
    JDBC jdbc = new JDBC();
    Connection connection = jdbc.establishConnection();


    @Override
    public void createUserCredentials(String name, String password, String roll) {
        PreparedStatement insertStatement;
        try {

            insertStatement = connection.prepareStatement("INSERT INTO user_Credentials (username, password, role) VALUES (?,?,?)");
            insertStatement.setString(1, name);
            insertStatement.setString(2, password);
            insertStatement.setString(3, roll);
            int rowsInserted = insertStatement.executeUpdate();

            if (rowsInserted >= 1) {
                System.out.println("User Created Successfully1");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public String fetchRole(String name, String password) {
        PreparedStatement selectStatement = null;
        try {

            selectStatement = connection.prepareStatement("select role from user_Credentials where username= ? AND password = ?");

            selectStatement.setString(1, name);
            selectStatement.setString(1, name);
            selectStatement.setString(2, password);
            ResultSet rs = selectStatement.executeQuery();
            rs.next();


            return rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }
}
