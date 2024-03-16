package org.murugappan.DAO;
import java.math.BigDecimal;
public interface  TransactionDAO {
	void insertData( String modePayment);
	BigDecimal fetchProfitByDate(String date);
	int fetchTaxByMonth(int month,int year);
}
