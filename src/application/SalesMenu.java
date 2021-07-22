package application;

//Sales SubMenu
//OTC_StPaul_2021_BackEndBootCamp_Group_Project
//Created by Adam Fite

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.SalesDao;
import entity.Sales;

public class SalesMenu {
	
	private SalesDao salesDao = new SalesDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Show All Previous Sales",
			"Show A Single Sale",
			"Input a Customers Purchase",
			"Delete A Customers Purchase",
			"Update A Sales Quantity",
			"Update A Sales Price",
			"Update A Sales Product",
			"Return to Main Menu");
	
	public void startSalesMenu() throws InterruptedException, IOException {
		String selection = "";
	
		do {
			printMenu();
			selection = scanner.nextLine();
			try {
				if (selection.equals("1")) {
						getSales();
					} else if (selection.equals("2")) {
						getSingleSale();
					} else if (selection.equals("3")) {
						addSale();
					} else if (selection.equals("4")) {
						removeSale();
					} else if (selection.equals("5")) {
						updateQuantity();
					} else if (selection.equals("6")) {
						updatePrice();
					} else if (selection.equals("7")) {
						updateProduct();
					} else if (selection.equals("8")) {
						System.out.println("Thank You for using the Sales Interface");
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} while (!selection.equals("8"));
	}
	
	private void printMenu() {
		System.out.println("\n-------------------------------------------- \nWelcome to the Sales Interface \nSelect an Option Number: \n--------------------------------------------");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ") " + options.get(i));
		}
	}
	
	private void getSales() throws SQLException {
		List<Sales> sales = salesDao.getSales();
		for (Sales sale : sales) {
			System.out.println(sale.getSale_id() + " - Quantity: " + sale.getQuantity() + 
					" - Price Per Unit: " + sale.getPrice() + 
					" - Product by ID: " + sale.getProduct_id() + 
					" - Customer by ID: " + sale.getSale_id());
		}
	}
	
	private void getSingleSale() throws SQLException {
		int sale_id = 0;
		System.out.print("Enter Sale Id to Find");
		while (true) {
			try {
				sale_id = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("The Sale Id Enter is Invalid");
			break;
		} try {
			Sales sale = salesDao.getSingleSale(sale_id);
			System.out.println(sale.getSale_id() + " - Quantity: " + sale.getQuantity() + 
					" - Price Per Unit: " + sale.getPrice() + 
					" - Product by ID: " + sale.getProduct_id() + 
					" - Customer by ID: " + sale.getSale_id());
				break;
		} catch (SQLException e) {
			System.out.println("One or more entries did not populate");
			break;
		}
	}
}	
	private void addSale() throws SQLException {
		while (true) {
			try {
				System.out.print("Enter Quantity of units sold:");
				int quantity = Integer.parseInt(scanner.nextLine());
				System.out.print("Enter the Price per unit:");
				double price = Double.parseDouble(scanner.nextLine());
				System.out.print("Enter the Product Id Sold:");
				int product_id = Integer.parseInt(scanner.nextLine());
				System.out.print("Enter Customers ID:");
				int customer_id = Integer.parseInt(scanner.nextLine());
				salesDao.addSale(quantity, price, product_id, customer_id);
				break;
			} catch (SQLException e) {
					System.out.println("\nThe Customer or Product ID Is Not Accurate, Please Verify and Retry");
					break;
			}
		}
	}
	
	private void removeSale() throws SQLException {
		int sale_id;
		System.out.print("Enter Sale ID to be removed:");
		while (true) {
			try {
				sale_id = Integer.parseInt(scanner.nextLine());
				salesDao.removeSale(sale_id);
				break;
			} catch (NumberFormatException e) {
				System.out.println("\nMust Enter a Valid Sale Id");
				break;
			}
		}
	}
	
	private void updateQuantity() throws SQLException {
		int sale_id = 0;
		int quantity = 0;
		System.out.print("Enter the Sale ID to update the Quantity: ");
		while (true) {
			try {
				sale_id = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("\nMust Enter a Valid Sale Id");
				break;
			}	
				System.out.print("Enter New Quantity sold: ");
				quantity = Integer.parseInt(scanner.nextLine());
				salesDao.updateQuantity(quantity, sale_id);
			}
		}
		
	private void updatePrice() throws SQLException {
		int sale_id = 0;
		double price;
		System.out.print("Enter the Sale ID to update the Quantity: ");
		while (true) {
			try {
				sale_id = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("\nMust Enter a Valid Sale Id");
				break;
			}	 	
				System.out.print("Enter New Price per unit: ");
				price = Double.parseDouble(scanner.nextLine());
				salesDao.updatePrice(price, sale_id);
			}
		}
	
	private void updateProduct() throws SQLException {
		int sale_id = 0;
		int product_id = 0;
		System.out.print("Enter the Sale ID to update the Quantity: ");
		while (true) {
			try {
				sale_id = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("\nMust Enter a Valid Sale Id");
				break;
			}			
				System.out.print("Enter New Product ID for units sold: ");
				try {
					product_id = Integer.parseInt(scanner.nextLine());
					salesDao.updatePrice(product_id, sale_id);
				} catch (SQLException e) {
					System.out.println("Product ID Does Not Exist, Please Verify and Try Again");
					break;
				}
	 		}
		}
}