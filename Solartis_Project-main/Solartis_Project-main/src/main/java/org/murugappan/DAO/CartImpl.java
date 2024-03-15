package org.murugappan.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.murugappan.repo.JDBC;
import org.murugappan.service.CreatePDF;
public class CartImpl implements CartDAO {
	  JDBC jdbc=new JDBC();
	  Connection con= jdbc.establishConnection();
	  PreparedStatement preparedStatement;
	  PreparedStatement deleteStatement;
	  PreparedStatement updateQuantity;
	  PreparedStatement fetchQuantity;
		
		double TotalpriceInclusiveOfTax = 0.0;
	@Override
	public void addToCart(Integer userid, Integer productid , Integer quantity ) {
		
		 try {
			 
	        	
	            preparedStatement = con.prepareStatement("INSERT INTO cart (user_id, product_id, quantity, total_amount) VALUES (?, ?, ?, (SELECT selling_price * ? FROM product_details WHERE product_id = ?))");
	            preparedStatement.setInt(1,userid);
	            preparedStatement.setInt(2,productid);
	            preparedStatement.setInt(3,quantity);
	            preparedStatement.setInt(4,quantity);
	            preparedStatement.setInt(5,productid);
	            int rowsInserted = preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	           e.printStackTrace();
	        }
		
	}

public void showCart(Integer userid)  {
	
	try {
		preparedStatement = con.prepareStatement("SELECT" +
				"  u.username, " +
				"  pd.product_name," +
				"  c.quantity," +
				"  pd.selling_price AS unit_price," +
				"  pd.Tax_Percent AS tax_percent," +
				"  (pd.selling_price * c.quantity) AS total_price_before_tax," +
				"  (" +
				"    (pd.selling_price * c.quantity)* pd.Tax_Percent / 100" +
				"  ) AS tax_amount, " +
				"  (" +
				"    (pd.selling_price * c.quantity) + (" +
				"      pd.selling_price * c.quantity * pd.Tax_Percent / 100" +
				"    )" +
				"  ) AS price_inclusive_of_tax " +
				"FROM " +
				"  cart c " +
				"  JOIN users u ON c.user_id = u.user_id " +
				"  JOIN product_details pd ON c.product_id = pd.product_id " +
				"WHERE " +
				"  u.user_id = ?");

		preparedStatement.setInt(1, userid);
		ResultSet rs = preparedStatement.executeQuery();
		System.out.printf("%-15s%-15s%-10s%-15s%-15s%-25s%-15s%-10s\n",
				"Username", "Product Name", "Quantity", "unit_price", "Tax Percent",
				"Total Price Before Tax", "Tax Value", "Price Inclusive of Tax");

		while (rs.next()) {
			String username = rs.getString("username");
			String productName = rs.getString("product_name");
			int quantity = rs.getInt("quantity");
			double priceBeforeTax = rs.getDouble("unit_price");
			int taxPercent = rs.getInt("tax_percent");
			double totalPriceBeforeTax = rs.getDouble("total_price_before_tax");
			double taxAmount = rs.getDouble("tax_amount");
			double priceInclusiveOfTax = rs.getDouble("price_inclusive_of_tax");
		
			
			TotalpriceInclusiveOfTax += priceInclusiveOfTax;

			System.out.printf("%-15s%-15s%-10d%-20.2f%-10d%-25.2f%-15.2f%-10.2f\n",
					username, productName, quantity, priceBeforeTax, taxPercent,
					totalPriceBeforeTax, taxAmount, priceInclusiveOfTax);
			
			

		}
				System.out.println("Your Total Cart Value is "+TotalpriceInclusiveOfTax);
	




		 
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
public ResultSet createPDF(int userid) {
    ResultSet rs = null;
    try {
		preparedStatement = con.prepareStatement("SELECT" +
				"  u.username, " +
				"  pd.product_name," +
				"  c.quantity," +
				"  pd.selling_price AS unit_price," +
				"  pd.Tax_Percent AS tax_percent," +
				"  (pd.selling_price * c.quantity) AS total_price_before_tax," +
				"  (" +
				"    (pd.selling_price * c.quantity)* pd.Tax_Percent / 100" +
				"  ) AS tax_amount, " +
				"  (" +
				"    (pd.selling_price * c.quantity) + (" +
				"      pd.selling_price * c.quantity * pd.Tax_Percent / 100" +
				"    )" +
				"  ) AS price_inclusive_of_tax " +
				"FROM " +
				"  cart c " +
				"  JOIN users u ON c.user_id = u.user_id " +
				"  JOIN product_details pd ON c.product_id = pd.product_id " +
				"WHERE " +
				"  u.user_id = ?");

		preparedStatement.setInt(1, userid);
		System.out.println("hi in cartimpl");
        rs = preparedStatement.executeQuery();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return rs;
}

public void deleteCart(int userId) {
	try {
	 preparedStatement = con.prepareStatement("SELECT cart_id FROM cart WHERE user_id = ?");
     deleteStatement = con.prepareStatement("DELETE FROM cart WHERE cart_id = ?");
	  preparedStatement.setInt(1,userId);
	 ResultSet rs = preparedStatement.executeQuery();
	 while (rs.next()) {
		 int cartId = rs.getInt("cart_id");
		 deleteStatement.setInt(1, cartId);
		 int rowsAffected = deleteStatement.executeUpdate();
		 
	 }
	}
	catch(SQLException e){
		
	}
}

@Override
public String getUserName(Integer userId) {
	String  username = null;
	try {
		 preparedStatement = con.prepareStatement("select username from users where user_id=?");
		 preparedStatement.setInt(1,userId);
		 ResultSet rs = preparedStatement.executeQuery();
		 while (rs.next()) {
			 username =rs.getString(1);
			 
		 }
		}
		catch(SQLException e){
			
		}
	return username;
}

@Override
public void updateQuantity() {
	try {
	    System.out.println("update1");
	    preparedStatement = con.prepareStatement("SELECT product_id FROM cart");
	    updateQuantity = con.prepareStatement("UPDATE product_details SET stock = stock - ? WHERE product_id = ?");
	    fetchQuantity = con.prepareStatement("SELECT quantity FROM cart WHERE product_id = ?");
	    
	    ResultSet rs = preparedStatement.executeQuery();

	    while (rs.next()) {
	        int productId = rs.getInt(1);
	        fetchQuantity.setInt(1, productId);
	        ResultSet rs1 = fetchQuantity.executeQuery();
	        
	        if (rs1.next()) {
	            int quantity = rs1.getInt(1);
	            updateQuantity.setInt(1, quantity);
	            updateQuantity.setInt(2, productId);
	            updateQuantity.executeUpdate();
	        }
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	
	
}

@Override
public int checkQuantity(Integer quantity, Integer productID) {
	try {
	preparedStatement = con.prepareStatement("select Stock from product_details where product_id=?");
	preparedStatement.setInt(1,productID);
	ResultSet rs=preparedStatement.executeQuery();
	rs.next();
	int quantityResult=rs.getInt(1);
	return quantityResult;
	}
	catch(SQLException e) {
		e.printStackTrace();
		return 0;
	}
}




}
