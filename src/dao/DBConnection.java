package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// This is the MySQL Database Connection
// OTC_StPaul_2021_BackEndBootCamp_Group_Project
// Created by Charlee Thao, Andrew Cham, & Adam Fite

public class DBConnection {

	// Saved log in variables
	private final static String URL = "jdbc:mysql://localhost:3306/Lego_Store";
	private final static String USERNAME = "db_username"; // remember to change back or need to use a preference connection
	private final static String PASSWORD = "db_password"; //remember to change back or need to use a preference connection
	
	// Saved connection variables
	private static Connection connection;
	private static DBConnection instance;
	
	//Connection builder
	private DBConnection(Connection connection) {
		DBConnection.connection = connection;
	}

	// Connection Command Code
	public static Connection getConnection() {
		if(instance == null) {
			try {
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				instance = new DBConnection(connection);
				System.out.println("Connection Successful.");
			} catch (SQLException e) {
				System.out.println("SQL ERROR:");
				e.printStackTrace();
			}
		}
		return DBConnection.connection;
	}
}