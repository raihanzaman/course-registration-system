import java.util.ArrayList;

public class Course {
	
	// Variables
	String course_name;
	String course_id;
	int max_students;
	int current_students;
	ArrayList<String> student_list = new ArrayList<String>();
	String course_instructor;
	int course_section_number;
	String course_location;
	
	// Constructor Method
	public Course(String name, String id, int max, int curr, String instructor, int sect, String loc) {
		course_name = name;
		course_id = id;
		max_students = max;
		current_students = curr;
		course_instructor = instructor;
		course_section_number = sect;
		course_location = loc;
	}
	
	public void add_student(String student_name) {
		student_list.add(student_name);
	}
	
	public void remove_student(String student_name) {
		student_list.remove(student_name);
	}
	
	public void display() {
		
	}
	
	public void delete() {
		
	}
	
}
