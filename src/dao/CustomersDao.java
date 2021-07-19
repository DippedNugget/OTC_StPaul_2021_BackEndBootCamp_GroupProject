package dao;

//Customers Dao
//OTC_StPaul_2021_BackEndBootCamp_Group_Project
//Created by Charlee Thao

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Customers;

public class CustomersDao {
	
	private Connection connection;
	private final String GET_CUSTOMERS_QUERY = "SELECT * FROM customers";
	private final String GET_CUSTOMER_BY_ID_QUERY = "SELECT * FROM customers WHERE customer_id = ?";
	private final String CREATE_NEW_CUSTOMER_QUERY = "INSERT INTO customers(full_name, address, phone, email) values (?, ?, ?, ?)";
	private final String UPDATE_CUSTOMER_QUERY = "UPDATE customers SET full_name = ?, address = ?, phone = ?, email = ? WHERE customer_id = ?";
	private final String DELETE_CUSTOMER_BY_ID_QUERY = "DELETE FROM customers WHERE customer_id = ?";
	
	public CustomersDao() {
		connection = DBConnection.getConnection();
	}
	
	public List<Customers> getCustomers() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_CUSTOMERS_QUERY).executeQuery();
		List<Customers> customers = new ArrayList<Customers>();
		
		while (rs.next()) {
			customers.add(populateCustomer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
		}
		return customers;
	}
	
	public Customers getSingleCustomer(int customer_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_CUSTOMER_BY_ID_QUERY);
		ps.setInt(1, customer_id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateCustomer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
}
	
	public void addCustomer(String full_name, String address, String phone, String email) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_CUSTOMER_QUERY);
		ps.setString(1, full_name);
		ps.setString(2, address);
		ps.setString(3, phone);
		ps.setString(4, email);
		ps.executeUpdate();
	}
	
	public void updateCustomer(String full_name, String address, String phone, String email, int customer_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_CUSTOMER_QUERY);
		ps.setString(1, full_name);
		ps.setString(2, address);
		ps.setString(3,phone);
		ps.setString(4, email);
		ps.setInt(5, customer_id);
		ps.executeUpdate();
	}
	
	public void removeCustomer(int customer_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_CUSTOMER_BY_ID_QUERY);
		ps.setInt(1, customer_id);
		ps.executeUpdate();
	}
	
	private Customers populateCustomer(int customer_id, String full_name, String address, String phone, String email, String join_date) throws SQLException {
		return new Customers(customer_id, full_name, address, phone, email, join_date);
	}
	
}

