package  com.FitnessFirst.model;


public class UserDetail {
private String email;
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
private int id;
public int getId() {
	return id;
}
@Override
public String toString() {
	return "UserDetail [email=" + email + ", id=" + id + ", password=" + password + "]";
}
public void setId(int id) {
	this.id = id;
}

public void setPassword(String password) {
	this.password = password;
}
private String password;
}
