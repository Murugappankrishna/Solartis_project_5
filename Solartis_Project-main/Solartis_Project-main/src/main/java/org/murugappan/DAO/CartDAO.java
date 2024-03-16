package org.murugappan.DAO;

import java.sql.ResultSet;

public interface CartDAO {
    void addToCart(Integer userId, Integer productId, Integer quantity);

    void showCart(Integer userId);

    void deleteCart(int userId);


     ResultSet generateBillPDF(int userId);

    String getUserName(Integer userId);

    void updateProductQuantity();

    int checkProductQuantity(Integer quantity, Integer productID);
}
