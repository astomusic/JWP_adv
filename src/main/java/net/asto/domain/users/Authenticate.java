package net.asto.domain.users;

import javax.validation.constraints.Size;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Authenticate {
	public static Logger logger = Logger.getLogger(Authenticate.class);
	
	@NotEmpty @Size(max=50)  @Email 
	private String email;
	
	@NotEmpty @Size(min=4, max=20) 
	private String password;
	
	public Authenticate() {
		
	}
	
	public Authenticate(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
