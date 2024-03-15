package org.murugappan.DAO;
import java.sql.ResultSet;
public interface CartDAO {
 void addToCart(Integer userId, Integer productId, Integer quantity);

 void showCart(Integer userId);
 void deleteCart(int userId);
 public ResultSet createPDF(int userId);

 String getUserName(Integer userId);

 void updateQuantity();

 int checkQuantity(Integer quantity,Integer productID);
}
