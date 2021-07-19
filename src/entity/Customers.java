package entity;

//Customers Entity
//OTC_StPaul_2021_BackEndBootCamp_Group_Project
//Created by Charlee Thao

public class Customers {

	private int customer_id;
	private String full_name;
	private String address;
	private String phone;
	private String email;
	private String join_date;
	
	public Customers(int customer_id, String full_name, String address, String phone, String email, String join_date) {
		this.setCustomer_id(customer_id);
		this.setFull_name(full_name);
		this.setAddress(address);
		this.setPhone(phone);
		this.setEmail(email);
		this.setJoin_date(join_date);
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJoin_date() {
		return join_date;
	}

	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
	
}
