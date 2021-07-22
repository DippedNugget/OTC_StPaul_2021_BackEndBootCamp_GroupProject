package application;

//Product Menu
//OTC_StPaul_2021_BackEndBootCamp_Group_Project
//Created by Andrew Cham


import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.ProductDao;
import entity.Product;


public class ProductMenu {
	private ProductDao productDao = new ProductDao();
    private Scanner scanner = new Scanner(System.in);
    private List<String> options = Arrays.asList(
        "Display Products",
        "Display a Product",
        "Add a Product",
        "Update a Product",
        "Delete a Product",
        "Return to Main Menu");

    public void startProductMenu() {
        String selection = "";

        do {
            printMenu();
            selection = scanner.nextLine();

            try {
                if(selection.equals("1")) {
                    getProducts();
                } else if (selection.equals("2")) {
                    getAProduct();
                } else if (selection.equals("3")) {
                    addProduct();
                } else if (selection.equals("4")) {
                    updateProduct();
                } else if (selection.equals("5")) {
                    deleteAProduct();
                } else if (selection.equals("6")) {
                    System.out.println("Thank you for using the Product Interface System");
                }
            } catch (SQLException e) {
                scanner.nextLine();
            }
        } while (!selection.equals("6"));
    }

    private void printMenu() {
        System.out.println("\n----------------------------------- \nWelcome to Product interface \nSelect an option: \n-------------------------");
        for (int i = 0; i < options.size(); i++) {
            System.out.println(i + 1 + ") " + options.get(i));
        }
    }

    private  void getProducts() throws SQLException {
        List<Product> products = productDao.getProducts();
        for(Product product : products) {
            System.out.println(product.getProduct_id() +
            "-Theme: " + product.getTheme() +
            "-Name: " + product.getSet_name() +
            "-Piece Count: " + product.getPiece_count() +
            "-Quantity: " + product.getQuantity());
        }
    }

    private void getAProduct() throws SQLException {
        int product_id = 0;
        System.out.println("Enter Product ID to see Product: ");
        while (true) {
            try {
                product_id = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\n Must Enter a Valid Product ID");
                break;
            }
            try {
                Product product = productDao.getAProduct(product_id);
                System.out.println(product.getProduct_id() + 
                		"-Theme: " + product.getTheme() + 
                		"-Name: " + product.getSet_name() + 
                		"-Piece Count: " + product.getPiece_count() + 
                		"-Quantity: " + product.getQuantity());
                break;
            } catch (SQLException e) {
                System.out.println("\n Invalid Product ID, please review and try again");
                break;
            }
        }
    }

    private void addProduct() throws SQLException {
        System.out.print("Enter Product Theme: ");
        String theme = scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String set_name = scanner.nextLine();
        System.out.print("Enter Piece Count: ");
        int piece_count = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Product Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        productDao.addProduct(theme, set_name, piece_count, quantity);
    }

    private void updateProduct() throws SQLException {
    	int product_id = 0;
		String theme;
		String set_name;
		int piece_count = 0;
		int quantity = 0;
		System.out.print("Enter Product ID to Update: ");
		while (true) {
			try {
				product_id = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("\nMust Enter Valid Product Id");
				break;
			}
					try {
						System.out.println("Update Product Theme: ");
				        theme = scanner.nextLine();
				        System.out.println("Update product Name: ");
				        set_name = scanner.nextLine();
				        System.out.println("Update Product Piece Count: ");
				        piece_count = Integer.parseInt(scanner.nextLine());
				        System.out.println("Update Product Quantity: ");
				        quantity = Integer.parseInt(scanner.nextLine());
				        productDao.updateProduct(theme, set_name, piece_count, quantity, product_id);
							break;
					
					} catch (SQLException e) {
						System.out.println("\nOne or more entries is invalid, please review and try again");
							break;
					}
		}
}
    	
    private void deleteAProduct() throws SQLException {
        int product_id = 0;
    	System.out.println("Enter Product Id to delete: ");
    	while (true) {
    		try {
    			product_id = Integer.parseInt(scanner.nextLine());
    			productDao.deleteProduct(product_id);
    			break;
    		} catch (NumberFormatException e) {
    			System.out.println("Must enter valid product id");
    		}
    	}
    }
}
