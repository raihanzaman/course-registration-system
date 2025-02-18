import java.util.*;

public abstract class User implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	String first_name;
	String last_name;
	
	public User() {
		username = "";
		password = "";
		first_name = "";
		last_name = "";
	}
	
	public User(String user, String pass, String first, String last) {
		username = user;
		password = pass;
		first_name = first;
		last_name = last;
	}
	
	public void view_all_courses(ArrayList<Course> course_list) {
		for(Course c : course_list) {
			System.out.println(c.course_name);
		}
	}
	
	public String get_username() {
		return username;
	}
	public String get_password() {
		return password;
	}
	public String get_firstname() {
		return first_name;
	}
	public String get_lastname() {
		return last_name;
	}
	
}
