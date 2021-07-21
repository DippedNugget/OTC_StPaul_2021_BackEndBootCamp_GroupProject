package application;

//Main Menu Code
//OTC_StPaul_2021_BackEndBootCamp_Group_Project
//Created by Charlee Thao, Andrew Cham, & Adam Fite

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainMenu {

		CustomersMenu customersMenu = new CustomersMenu();  
		ProductMenu productMenu = new ProductMenu();
		SalesMenu salesMenu = new SalesMenu();
		private Scanner scanner = new Scanner(System.in);
		private List<String> options = Arrays.asList(
				"Customers Interface",
				"Inventory Interface",
				"Sales Interface",
				"EXIT");
		
		public void startMainMenu() {
			String selection = "";
			
			do {
				printMenu();
				selection = scanner.nextLine();
				
				try {
					if (selection.equals("1")) {
						customersMenu.startCustomersMenu();
					} else if (selection.equals("2")) {
						productMenu.startProductMenu();
					} else if (selection.equals("3")) {
						salesMenu.startSalesMenu();
					} else if (selection.equals("4")) {
						System.out.println("Thank You For Using the Lego Store Interface!");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				System.out.println("Press ENTER to continue ...");
				scanner.nextLine();
			} while (!selection.equals("4"));
		}
		
		
		
		private void printMenu() {
			System.out.println("\n---------------------------------------- \nWelcome to the LEGO Store Interface Menu \nSelect an Option Number: \n----------------------------------------");
			for (int i = 0; i < options.size(); i++) {
				System.out.println(i + 1 + ") " + options.get(i));
			}
		}
	}
