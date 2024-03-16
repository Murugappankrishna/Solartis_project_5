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
public void  showTaxForMonth(){
	int month;
	int year;
	System.out.println("Enter The Month In The Format Of MM To See The Tax Paid For The Month");
	month= input.nextInt();
	System.out.println("Enter The Year In The Format Of YYYY");
	year=input.nextInt();
	System.out.println("The Total Pax For The Month is "+TransactionImpl.fetchTaxByMonth(month,year));
}
}
