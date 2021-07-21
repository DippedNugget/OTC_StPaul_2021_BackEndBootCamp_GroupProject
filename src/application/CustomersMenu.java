package application;

//Customers SubMenu
//OTC_StPaul_2021_BackEndBootCamp_Group_Project
//Created by Charlee Thao

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.CustomersDao;
import entity.Customers;

public class CustomersMenu {

	private CustomersDao customersDao = new CustomersDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Show Customers", 
			"Show a Customer", 
			"Add a New Customer",
			"Update an Exsisting Customer",
			"Delete a Customer",
			"Return to Main Menu"); 
	
	
	public void startCustomersMenu() throws InterruptedException, IOException {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			
			try { 
				if (selection.equals("1")) {
					getCustomers();
				} else if (selection.equals("2")) {
					getSingleCustomer();
				} else if (selection.equals("3")) {
					addCustomer();
				} else if (selection.equals("4")) {
					updateCustomer();
				} else if (selection.equals("5")) {
					removeCustomer();
				}  else if (selection.equals("6")) {
					System.out.println("Thank You for using the Customer Interface");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			
		} while(!selection.equals("6"));
	}
	
	private void printMenu() {
		System.out.println("\n-------------------------------------------- \nWelcome to the Customer Interface \nSelect an Option Number: \n--------------------------------------------");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ") " + options.get(i));
		}
	}
	
	private void getCustomers() throws SQLException {
		List<Customers> customers = customersDao.getCustomers();
		for (Customers customer : customers) {
			System.out.println(customer.getCustomer_id() + 
					" - Full Name: " + customer.getFull_name() + 
					" - Address: " + customer.getAddress() + 
					" - Phone Number: " + customer.getPhone() +
					" - Email: " + customer.getEmail());
		}
	}
	
	private void getSingleCustomer() throws SQLException {
		int customer_id = 0;
		System.out.print("Enter Customer ID to see customer: ");
		while (true) {
			try {
				customer_id = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("\nMust Enter a Valid Customer Id");
				break;
			}
				try {
					Customers customer = customersDao.getSingleCustomer(customer_id);
					System.out.println(customer.getCustomer_id() + 
							" - Full Name: " + customer.getFull_name() + 
							" - Address: " + customer.getAddress() + 
							" - Phone Number: " + customer.getPhone() +
							" - Email: " + customer.getEmail());
						break;
					
				} catch (SQLException e) {
				System.out.println("\nInvalid Customer Id, please review and try again");
				break;

			}
		}
	}
	
	private void addCustomer() throws SQLException {
		System.out.print("Enter New Customer information: ");
		System.out.print("\nEnter Customer's Full Name: ");
		String full_name = scanner.nextLine();
		System.out.print("Enter Customer's Address: ");
		String address = scanner.nextLine();
		System.out.print("Enter Customer's Phone: ");
		String phone = scanner.nextLine();
		System.out.print("Enter Customer's Email: ");
		String email = scanner.nextLine();
		customersDao.addCustomer(full_name, address, phone, email);
		}
	
	private void updateCustomer() throws SQLException {
		int customer_id = 0;
		String full_name;
		String address;
		String phone;
		String email;
		System.out.print("Enter Customer ID to Update: ");
		while (true) {
			try {
				customer_id = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("\nMust Enter Valid Customer Id");
				break;
			}
					try {
						System.out.print("Update the Customer's Full Name: ");
						full_name = scanner.nextLine();
						System.out.print("Update the Customer's Address: ");
						address = scanner.nextLine();
						System.out.print("Update the Customer's Phone: ");
						phone = scanner.nextLine();
						System.out.print("Update the Customer's Email: ");
						email = scanner.nextLine();
						customersDao.updateCustomer(full_name, address, phone, email, customer_id);
							break;
					
					} catch (SQLException e) {
						System.out.println("\nOne or more entries is invalid, please review and try again");
							break;
					}
		}
}

	
	private void removeCustomer() throws SQLException {
		int customer_id;
		System.out.print("Enter Customer ID to be removed: ");
		while (true) {
			try {
				customer_id = Integer.parseInt(scanner.nextLine());
				customersDao.removeCustomer(customer_id);
				break;
			} catch (NumberFormatException e) {
				System.out.println("\n Must Enter a Valid Customer Id");
				break;
			}
		}
	}
}
	

