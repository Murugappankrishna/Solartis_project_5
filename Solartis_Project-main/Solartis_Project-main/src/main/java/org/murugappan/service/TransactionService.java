package org.murugappan.service;
import org.murugappan.DAO.TransactionImpl;
import org.murugappan.DAO.TransactionDAO;
import java.util.*;

public class TransactionService {
	TransactionDAO TransactionImpl=new TransactionImpl();
	Scanner input = new Scanner(System.in);
public void showProfitForAMonth() {
	
	System.out.println("Enter The Date To See The Profit Made On That Day");
	String date= input.next();
	System.out.println(TransactionImpl.fetchProfitByDate(date));
	
}
}
