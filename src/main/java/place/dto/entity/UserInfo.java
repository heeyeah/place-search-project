package place.dto.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER_INFO")
public class UserInfo {

	@Id
	private String userId;
	
	private String password;
	
	private UserInfo() {
	}
	
	public UserInfo(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", password=" + password + "]";
	}	
}
