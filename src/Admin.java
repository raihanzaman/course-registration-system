import java.util.ArrayList;

public class Admin extends User {
	public Admin(String user, String pass, String first, String last) {
		super(user, pass, first, last);
	}
	
	// COURSE MANAGEMENT
	
	public void create_course(ArrayList<Course> course_list, String name, String id, 
			int max, int curr, String instructor, int sect, String loc) {
		
		Course c = new Course(name, id, max, curr, instructor, sect, loc);
		course_list.add(c);
		
	}
	
	public void delete_course(ArrayList<Course> course_list, String course_name, String course_id) { // are we deleting by id, name, both?
		for(Course c : course_list) {
			if (c.course_id.equals(course_id) && c.course_name.equals(course_name)) { //review
				course_list.remove(c);
			}
		}
	}
	
	public void edit_course() {
		
	}
	
	public void display_course(String course_id) {
		System.out.println("Displaying Course Information:");
	}
	
	public void register_student(ArrayList<Student> s_list, String user, String pass, String first, String last) {
		Student s = new Student(user, pass, first, last);
		s_list.add(s);
	}
	
	// REPORTS
	
	public void view_all_courses(ArrayList<Course> course_list) { // add bool for if full or not?
		for(Course c : course_list) {
			System.out.println(c.course_name); // temporary
			System.out.println(c.student_list); //continue
		}
	}
	
	public void students_in_course(Course c) {
		System.out.println("Displaying Course Information:");
	}
	
}
