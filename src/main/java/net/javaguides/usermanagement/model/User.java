package net.javaguides.usermanagement.model;

public class User {
	private int id;
	private String name;
	private String nic;
	private String email;
	private String address;
	private String comments;
	private String date;
	
	public User(int id, String name, String nic, String email, String address, String comments, String date) {
		super();
		this.id = id;
		this.name = name;
		this.nic = nic;
		this.email = email;
		this.address = address;
		this.comments = comments;
		this.date = date;
	}
	
	public User(String name, String nic, String email, String address, String comments, String date) {
		super();
		this.name = name;
		this.nic = nic;
		this.email = email;
		this.address = address;
		this.comments = comments;
		this.date = date;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNic() {
		return nic;
	}
	public void setNic(String nic) {
		this.nic = nic;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	
}
