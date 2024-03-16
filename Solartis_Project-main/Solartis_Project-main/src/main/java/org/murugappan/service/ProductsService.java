package org.murugappan.service;
import org.murugappan.DAO.ProductsDAO;
import org.murugappan.DAO.ProductsImpl;
import org.murugappan.model.ProductDetails;

import java.util.Scanner;

public class ProductsService {
    ProductDetails details =new ProductDetails();
    ProductsDAO productsImplementation =new ProductsImpl();
    
    Scanner input = new Scanner(System.in);
    public void addProduct(){
    	


        System.out.println("Enter The Product Name");
        details.productDetails.put("ProductName", input.next());
        System.out.println("Enter The Cost  Price");
        details.productDetails.put("CostPrice", input.next());
        System.out.println("Enter The Selling Price");
        details.productDetails.put("SellingPrice", input.next());
        System.out.println("Enter The StockNo");
        details.productDetails.put("Stock", input.next());
        System.out.println("Enter The Product Description");
        details.productDetails.put("Description", input.next());
        System.out.println("Enter The Tax Percent");
        details.productDetails.put("Tax", input.next());
        productsImplementation.addProducts(details.productDetails.get("ProductName"), details.productDetails.get("Description"),Integer.parseInt(details.productDetails.get("CostPrice")),Integer.parseInt(details.productDetails.get("SellingPrice")),Integer.parseInt(details.productDetails.get("Stock")),Integer.parseInt(details.productDetails.get("Tax")));

    }
    public void deleteProduct(){
        System.out.println("Enter The ProductId To Be Deleted");
        int productId= input.nextInt();
        productsImplementation.deleteProduct(productId);
    }
	public void showProducts() {
		System.out.println("Here The List of Available Products In the Inventory!");
		System.out.println();
		productsImplementation.showProducts();
	}
	public void editProducts() {
		System.out.println("Enter the Detail To Be Edited \n1.Edit Product Name\n2.Edit Product Selling Price \n3.Edit Cost Price \n4.Edit Product Description \n5.Edit The Total Stock \n6. Edit The Tax Percent ");		
		String choice=(input.nextLine()).toUpperCase();
		String columnToUpdate = null;
		System.out.println("Enter the product ID");
		int productId= input.nextInt();
		System.out.println("Enter The New Value Which To Be Updated");
		String newValue= input.next();

        switch (choice) {
            case "PRODUCT NAME" -> columnToUpdate = "product_name";
            case "SELLING PRICE" -> columnToUpdate = "selling_price";
            case "COST PRICE" -> columnToUpdate = "cost_price";
            case "STOCK" -> columnToUpdate = "Stock";
            case "PRODUCT DESCRIPTION" -> columnToUpdate = "product_des";
            case "TAX PERCENT" -> columnToUpdate = "Tax_Percent";
            default -> System.out.println("Enter A Valid Choice");
        }
		productsImplementation.editProduct(columnToUpdate,productId,newValue);
	
	}
	
	
}
