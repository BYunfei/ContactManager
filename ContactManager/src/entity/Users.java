package entity;

public class Users {
	private String id;
	private String username;
	private String password;
	private boolean isAdmin;
	
	public Users(){
		
	}
	
	public Users(String username, String password) {
		id = null;
		this.username = username;
		this.password = password;
		isAdmin = false;
	}

	public Users(String id, String username, String password, boolean isAdmin) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public Users(String id,String username,String password,int isAdmin){
		this.id = id;
		this.username = username;
		this.password = password;
		if(isAdmin==1)
			this.isAdmin = true;
		else
			this.isAdmin = false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public String[] getAtrributes(){
		String isAdmin = this.isAdmin?"ÊÇ":"·ñ";
		String[] atrr = {this.id,this.username,this.password,isAdmin};
		return atrr;
	}
	
}
