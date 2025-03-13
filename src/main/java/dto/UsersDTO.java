package dto;

public class UsersDTO {
	private int id;
	private String username;
	private String password;
	private boolean isAdmin;
	private String lastName;
	private String firstName;
	
	public UsersDTO() {
		
	}
	
	public UsersDTO(int id, String username, String password, boolean isAdmin, String lastName, String firstName) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
		this.lastName = lastName;
		this.firstName = firstName;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
