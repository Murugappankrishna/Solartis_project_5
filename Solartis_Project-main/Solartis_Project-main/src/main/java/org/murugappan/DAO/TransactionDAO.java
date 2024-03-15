package org.murugappan.DAO;
import java.math.BigDecimal;
public interface  TransactionDAO {
	void insertData( String modeOfPayment);
	BigDecimal fetchProfitByDate(String date);
}
