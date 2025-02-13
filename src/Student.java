import java.util.ArrayList;

public class Student extends User {
	
	ArrayList<Course> courses_registered = new ArrayList<>();
	
	public Student(String user, String pass, String first, String last) {
		super(user, pass, first, last);
	}
	
	public void view_not_full_courses(ArrayList<Course> course_list) {
		for(Course c : course_list) {
			if (c.current_students != c.max_students) {
				System.out.println(c.course_name); // temporary
			}
		}
	}
	
	public void register_course(String course_name, int course_section) {
		
	}
	
	public void withdraw_course() {
		
	}
	
	public void view_my_courses() {
		for (Course c : courses_registered) {
			System.out.println(c.course_name);
		}
	}
	
}
