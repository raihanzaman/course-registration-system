import java.util.ArrayList;

public class Course implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Variables
	String course_name;
	String course_id;
	int max_students;
	int current_students;
	ArrayList<Student> student_list = new ArrayList<Student>();
	String course_instructor;
	int course_section_number;
	String course_location;
	
	public Course() {
		course_name = "";
		course_id = "";
		max_students = 0;
		current_students = 0;
		course_instructor = "";
		course_section_number = 0;
		course_location = "";
	}
	
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
	
	public void add_student(Student student) {
		student_list.add(student);
	}
	
	public void remove_student(Student student) {
		student_list.remove(student);
	}
	
	public void display_admin() {
		// Display, but from admin POV
		System.out.println("Course name: " + course_name + "\n"
						 + "Course ID: " + course_id + "\n"
						 + "Max_students: " + Integer.toString(max_students) + "\n"
						 + "Current students: " + Integer.toString(current_students) + "\n"
						 + "Course instructor: " + course_instructor + "\n"
						 + "Course section number: " + Integer.toString(course_section_number) + "\n"
						 + "Course location: " + course_location + "\n");
		System.out.println("Student list: \n");
		for (Student student : student_list) {
			System.out.println(student.get_firstname() + student.get_lastname() 
								+ " - User: " + student.get_username());
		}
	}
	
	public void display_student() {
		// display, but from student POV
		System.out.println("Course name: " + course_name + "\n"
						 + "Course ID: " + course_id + "\n"
						 + "Max_students: " + Integer.toString(max_students) + "\n"
						 + "Current students: " + Integer.toString(current_students) + "\n"
						 + "Course instructor: " + course_instructor + "\n"
						 + "Course section number: " + Integer.toString(course_section_number) + "\n"
						 + "Course location: " + course_location + "\n");
	}
	
	public void display_students() {
		System.out.println("Student list: \n");
		for (Student student : student_list) {
			System.out.println(student.get_firstname() + student.get_lastname() 
								+ " - User: " + student.get_username());
		}
	}
	
	public void delete() {
		course_name = "";
		course_id = "";
		max_students = 0;
		current_students = 0;
		student_list = new ArrayList<Student>();
		course_instructor = "";
		course_section_number = 0;
		course_location = "";
	}
	
}
