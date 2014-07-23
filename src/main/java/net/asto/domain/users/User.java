package net.asto.domain.users;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class User {
	public static Logger logger = Logger.getLogger(User.class);
	
	@NotEmpty @Size(max=50)  @Email 
	private String email;
	
	@NotEmpty @Size(min=4, max=20) 
	private String password;
	
	@NotEmpty @Size(min=4, max=20)
	private String passwordConfirm;
	
	@AssertTrue
	private boolean passwordCheck;

	public User() {
	}
	
	public User(String email, String password, String passwordConfirm) {
		this.email = email;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
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
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
		this.passwordCheck = this.password.equals(this.passwordConfirm);
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((passwordConfirm == null) ? 0 : passwordConfirm.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (passwordConfirm == null) {
			if (other.passwordConfirm != null)
				return false;
		} else if (!passwordConfirm.equals(other.passwordConfirm))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", passwordConfirm=" + passwordConfirm + "]";
	}
	
}
