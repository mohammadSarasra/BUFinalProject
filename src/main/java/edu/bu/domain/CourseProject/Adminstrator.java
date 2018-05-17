package edu.bu.domain.CourseProject;

public class Adminstrator extends Person {
	private String userName;
	private String password;

	public Adminstrator() {
		super();
	}

	public Adminstrator(String name, int age, Address address, String userName, String password) {
		super(name, age, address);
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Adminstrator [userName=" + userName + ", password=" + password + "]\n";
	}

}
