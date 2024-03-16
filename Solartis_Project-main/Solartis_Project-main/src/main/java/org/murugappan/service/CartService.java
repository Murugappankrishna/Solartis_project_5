package org.murugappan.service;

import java.util.Scanner;

import org.murugappan.model.Cart;


import org.murugappan.DAO.*;

public class CartService {
	Cart cart= new Cart();
	Scanner input =new Scanner(System.in);
	CartDAO cartImplementation = new CartImpl();
	TransactionDAO transactionImplementation =new TransactionImpl();
	CreatePDF createPDF =new CreatePDF();
	MailService mailService=new MailService();
	
	public void addToCart() {
		
		int flag;
		String isBilling;
		String modePayment;
		System.out.println("Enter The Products To Be Added TO The Cart");	
		System.out.println("Enter The User ID ");
		cart.userCart.put("UserID", input.nextInt());
		do {
		System.out.println("Enter THe Product ID To Be Added In The Cart");
		cart.userCart.put("ProductID", input.nextInt());
		System.out.println("Enter The Quantify Needed");
		cart.userCart.put("Quantity", input.nextInt());
		int quantityResult= cartImplementation.checkProductQuantity(cart.userCart.get("Quantity"),cart.userCart.get("ProductID"));
		if(cart.userCart.get("Quantity")>quantityResult) {
			System.out.println("We Are Sorry..! We Are Running Out Of Stock");
		}
		else {
		cartImplementation.addToCart(cart.userCart.get("UserID"),cart.userCart.get("ProductID"),cart.userCart.get("Quantity"));
		}
		System.out.print("Do Want More Products To Be added Enter 1 Else 2");
		flag= input.nextInt();
		}while(flag==1);
		System.out.println("Your Products Has Been Added");
		cartImplementation.showCart(cart.userCart.get("UserID"));
		System.out.println("DO Want To Proceed To Billing Section Enter YES else NO");
		isBilling= input.next().toUpperCase();
		if(isBilling.equals("YES")) {
			System.out.println("Say Your Choice of Payment Mode");
			modePayment= input.next().toUpperCase();
			String userName= cartImplementation.getUserName(cart.userCart.get("UserID"));
			createPDF.createInvoice(cart.userCart.get("UserID"), modePayment,userName);
			cartImplementation.updateProductQuantity();
			
			mailService.sendMail();
			transactionImplementation.insertData(modePayment);
			cartImplementation.deleteCart(cart.userCart.get("UserID"));
		
		}
		
		
	}
	
	

}
