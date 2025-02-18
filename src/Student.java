import java.util.*;

public class Student extends User implements Student_Interface {
	
	private static final long serialVersionUID = 1L;
	
	ArrayList<Course> courses_registered;
	
	public Student(String user, String pass, String first, String last) {
		super(user, pass, first, last);
		courses_registered = new ArrayList<>();
	}
	
	public void view_all_courses(ArrayList<Course> course_list) {
		for(Course c : course_list) {
			c.display_student();
		}
	}
	
	public void view_not_full_courses(ArrayList<Course> course_list) {
		boolean notFullCourseExists = true; // See if any full courses exist
		for(Course c : course_list) {
			if (c.current_students != c.max_students) {
				c.display_admin();
				notFullCourseExists = true;
			}
		}
		if (!notFullCourseExists) {
			System.out.println("No available courses.");
		}
	}
	
	public void register_course(Scanner scanner, ArrayList<Course> course_list) {
		System.out.println("Enter Course Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Course ID:");
        String id = scanner.nextLine(); 
        System.out.println("Enter Section Number:");
        int sect = scanner.nextInt();
        for(Course c : course_list) {
			if (c.course_name.equals(name) && c.course_id.equals(id) && c.course_section_number == sect) { 
				c.add_student(this);
				courses_registered.add(c);
			}
		}
	}
	
	public void withdraw_course(Scanner scanner, ArrayList<Course> course_list) {
		System.out.println("Enter Course Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Course ID:");
        String id = scanner.nextLine(); 
        System.out.println("Enter Section Number:");
        int sect = scanner.nextInt();
        for(Course c : course_list) {
			if (c.course_name.equals(name) && c.course_id.equals(id) && c.course_section_number == sect) { 
				c.remove_student(this);
				this.courses_registered.remove(c);
			}
		}
	}
	
	public void view_my_courses() {
		System.out.println("Courses Registered: ");
		for (Course c : courses_registered) {
			c.display_student();
		}
	}
	
}
