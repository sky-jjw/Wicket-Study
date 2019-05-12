package entity;

public class User {
	
	private String userName;
	
	private String passworld;

	public User(String userName, String passworld) {
		super();
		this.userName = userName;
		this.passworld = passworld;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassworld() {
		return passworld;
	}

	public void setPassworld(String passworld) {
		this.passworld = passworld;
	}

}
