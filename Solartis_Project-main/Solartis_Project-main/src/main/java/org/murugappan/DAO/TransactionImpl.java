package org.murugappan.DAO;

import org.murugappan.repo.JDBC;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionImpl implements TransactionDAO {
    JDBC jdbc = new JDBC();
    Connection connection = jdbc.establishConnection();


    @Override
    public void insertData(String modePayment) {
        try {
            String Query = "INSERT INTO transactionDB (userID, username, productID, productName, quantity, costPrice, sellingPrice, profit, totalPriceExcludingTax, taxPercent, totalCostIncludingTax, taxCost, transactionDate, modeOfPayment) " + "SELECT " + "    c.user_id AS userID, " + "    u.username AS username, " + "    c.product_id AS productID, " + "    pd.product_name AS productName, " + "    c.quantity AS quantity, " + "    pd.cost_price AS costPrice, " + "    pd.selling_price AS sellingPrice, " + "    (pd.selling_price - pd.cost_price) * c.quantity AS profit, " + "    pd.selling_price * c.quantity AS totalPriceExcludingTax, " + "    pd.Tax_Percent  AS taxPercent, " + "    (pd.selling_price * c.quantity) + ((pd.selling_price * c.quantity) * (pd.Tax_Percent / 100)) AS totalCostIncludingTax, " + "    (pd.selling_price * c.quantity) * (pd.Tax_Percent  / 100) AS taxCost, " + "    CURDATE() AS transactionDate, " + "    ? AS modeOfPayment " + "FROM " + "    cart c " + "JOIN " + "    users u ON c.user_id = u.user_id " + "JOIN " + "    product_details pd ON c.product_id = pd.product_id;";


            PreparedStatement insertQuery = connection.prepareStatement(Query);
            insertQuery.setString(1, modePayment);
            int rowsAffected = insertQuery.executeUpdate();
            if (rowsAffected > 1) {
                System.out.println("Note For the Manager and Admin TransactionTable Updated For This Transactions");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public BigDecimal fetchProfitByDate(String date) {
        try {
            PreparedStatement selectQuery = connection.prepareStatement("select sum(profit) from transactionDB where transactionDate =?");
            selectQuery.setString(1, date);
            ResultSet rs = selectQuery.executeQuery();
            rs.next();
            return rs.getBigDecimal(1);


        } catch (SQLException e) {
            e.printStackTrace();

        }
        return BigDecimal.valueOf(0.0);
    }

    @Override
    public int fetchTaxByMonth(int month, int year) {
        try {
            PreparedStatement selectQuery = connection.prepareStatement("SELECT SUM(taxCost) AS totalTaxPaid FROM transactionDB WHERE MONTH(transactionDate) = ? AND YEAR(transactionDate) = ?");
            selectQuery.setInt(2, year);
          selectQuery.setInt(1, month);
            ResultSet rs = selectQuery.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
