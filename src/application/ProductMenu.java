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
        List<Product> products = productDao.getProducts();
        for(Product product : products) {
            System.out.println(product.getProduct_id() +
            "-Theme: " + product.getTheme() +
            "-Name: " + product.getSet_name() +
            "-Piece Count: " + product.getPiece_count() +
            "-Quantity: " + product.getQuantity());
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
        System.out.println("Enter Product Id to Update: ");
        int product_id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Product Theme: ");
        String theme = scanner.nextLine();
        System.out.println("Enter product Name: ");
        String set_name = scanner.nextLine();
        System.out.println("Enter Product Piece Count: ");
        int piece_count = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Product Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        productDao.updateProduct(product_id, theme, set_name, piece_count, quantity);
    }

    private void deleteAProduct() throws SQLException {
        System.out.println("Enter Product Id to delete: ");
        int product_id = Integer.parseInt(scanner.nextLine());
        productDao.deleteProduct(product_id);
        
    }

}
