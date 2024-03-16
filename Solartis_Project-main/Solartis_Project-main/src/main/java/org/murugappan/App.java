package org.murugappan;

import org.murugappan.service.*;
import java.math.BigDecimal;


import java.util.Scanner;


public class App
{
    public static void main(String[] args) {
    UserDetailsService ucs= new UserDetailsService();
    ProductsService ps=new ProductsService();
    CartService cs=new CartService();
    TransactionService ts=new TransactionService();
    
//    Scanner ip=new Scanner(System.in);
//    String userFlag=null;
//        while(true) {
//        System.out.println("Enter 1 To Register As A New User \nEnter 2 To Login As Existing User");
//        int isNewUserOrExistingUser = ip.nextInt();
//        if (isNewUserOrExistingUser == 1) {
//          ucs.createUser();
//        }
//        else if (isNewUserOrExistingUser == 2) {
//           userFlag= (ucs.fetchRole().toUpperCase());
//           System.out.println(userFlag);
//           if(userFlag.equals("MANAGER")) {
//        	int managerFunction;
//           	System.out.println("Welcome Manager!\nHave A Nice Day");
//           	System.out.println("Enter 1 To See All Products In The Inventory\nEnter 2 To Add A Product\nEnter 3 To Edit A Product\nEnter 4 To Remove A Product\nEnter 5 To Add Products TO Cart");
//           	managerFunction=ip.nextInt();
//           	if(managerFunction==1)
//           	  ps.showProducts();
//           	else if(managerFunction==2)
//           		ps.addProduct();
//           	else if(managerFunction==3)
//           		ps.editProducts();
//           	else if(managerFunction==4)
//           		ps.deleteProduct();
//           	else if(managerFunction==5)
//           		cs.addToCart();
//
//           }
//           else if(userFlag.equals("SALESPERSON")) {
//           	System.out.println("Welcome SalesPerson!\nHave A Nice Day");
//           	System.out.println("Enter 1 To Add A Product\nEnter 2 To Edit A Product\nEnter 3 To Remove A Product\nEnter 4 To Add Products TO Cart");
//           	int salespersonFunction;
//           	salespersonFunction=ip.nextInt();
//           	if(salespersonFunction==1)
//           		ps.addProduct();
//           	else if(salespersonFunction==2)
//           		ps.editProducts();
//           	else if(salespersonFunction==3)
//           		ps.deleteProduct();
//           	else if(salespersonFunction==4)
//           		cs.addToCart();
//      }
//
//        } else {
//            System.out.println("Enter A Valid Option");
//        }
//
//        }

    
    // ts.showProfitForAMonth();
		ts.showTaxForMonth();
       
    }
}
