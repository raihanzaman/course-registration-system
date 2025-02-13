import java.util.ArrayList;

public class User {
	
	private String username;
	private String password;
	String first_name;
	String last_name;
	
	
	public User(String user, String pass, String first, String last) {
		username = user;
		password = pass;
		first_name = first;
		last_name = last;
	}
	
	public void view_all_courses(ArrayList<Course> course_list) { // add bool for if full or not?
		for(Course c : course_list) {
			System.out.println(c.course_name); // temporary
		}
	}
	
}
