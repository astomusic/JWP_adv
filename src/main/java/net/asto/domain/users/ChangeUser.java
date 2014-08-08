package net.asto.domain.users;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotEmpty;

public class ChangeUser {
	public static Logger logger = Logger.getLogger(ChangeUser.class);

	@NotEmpty @Size(min=4, max=20) 
	private String oldPassword;
	
	@NotEmpty @Size(min=4, max=20) 
	private String newPassword;
	
	@NotEmpty @Size(min=4, max=20)
	private String NewPasswordConfirm;
	
	@AssertTrue
	private boolean passwordCheck;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirm() {
		return NewPasswordConfirm;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		NewPasswordConfirm = newPasswordConfirm;
		this.passwordCheck = this.newPassword.equals(this.NewPasswordConfirm);
	}

	@Override
	public String toString() {
		return "ChangeUser [oldPassword=" + oldPassword + ", newPassword=" + newPassword + ", NewPasswordConfirm=" + NewPasswordConfirm + "]";
	}
	
}
