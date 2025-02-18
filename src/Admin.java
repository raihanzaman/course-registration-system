import java.util.*;

public class Admin extends User implements Admin_Interface {
	
	private static final long serialVersionUID = 1L; // isnt used because Admin isn't deserealized
	
	public Admin(String user, String pass, String first, String last) {
		super(user, pass, first, last);
	}
	
	// COURSE MANAGEMENT
	
	public void create_course(Scanner scanner, ArrayList<Course> course_list) {
		// Allows Admin to Create a new Course with input
    	System.out.println("Enter Course Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Course ID:");
        String id = scanner.nextLine(); 
        System.out.println("Enter Max Students in Course (integer):");
        int max = scanner.nextInt();
        scanner.nextLine(); 
        System.out.println("Enter Course Instructor:");
        String instructor = scanner.nextLine();
        System.out.println("Enter Section Number:");
        int sect = scanner.nextInt();
        scanner.nextLine(); 
        System.out.println("Enter Course Location:");
        String loc = scanner.nextLine();
        
		Course c = new Course(name, id, max, 0, instructor, sect, loc);
		course_list.add(c);
		
	}
	
	public void delete_course(Scanner scanner, ArrayList<Course> course_list) {
		// Allows Admin to Delete a new Course with input
		System.out.println("Enter Course Name:");
        String course_name = scanner.nextLine();
        System.out.println("Enter Course ID:");
        String course_id = scanner.nextLine(); 
		for(Course c : course_list) {
			if (c.course_id.equals(course_id) && c.course_name.equals(course_name)) { 
				course_list.remove(c);
				c.delete(); 
			}
		}
	}
	
	public void edit_course(Scanner scanner, ArrayList<Course> course_list) {
		// Allows Admin to Edit specific aspects of a course
    	System.out.println("Enter Course Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Course ID:");
        String id = scanner.nextLine(); 
        for(Course c : course_list) {
			if (c.course_id.equals(id) && c.course_name.equals(name)) { 
				while (true) {
		            System.out.println("\nCourse Management");
		            System.out.println("1. Edit course max students");
		            System.out.println("2. Edit course instructor");
		            System.out.println("3. Edit course section number");
		            System.out.println("4. Edit course location");
		            System.out.println("5. Back to main menu");
		            System.out.print("Enter your choice: ");

		            if (scanner.hasNextInt()) {
		                switch (scanner.nextInt()) {
		                    case 1:
		                    	scanner.nextLine();
		                    	System.out.println("Enter New Max Students (int): ");
		                        int max = scanner.nextInt();
		                        c.max_students = max;
		                        break;
		                    case 2:
		                    	scanner.nextLine();
		                    	System.out.println("Enter New Instructor (int): ");
		                        String instruct = scanner.nextLine();
		                        c.course_instructor = instruct;
		                        break;
		                    case 3:
		                    	scanner.nextLine();
		                    	System.out.println("Enter New Course Section Number (int): ");
		                        int sect = scanner.nextInt();
		                        c.course_section_number = sect;
		                        break;
		                    case 4:
		                    	scanner.nextLine();
		                    	System.out.println("Enter New Location: ");
		                        String loc = scanner.nextLine();
		                        c.course_location = loc;
		                        break;
		                    case 5:
		                        return;
		                    default:
		                        System.out.println("Invalid choice. Please enter a number between 1-5.");
		                }
		            } else {
		                System.out.println("Invalid input. Please enter a number.");
		                scanner.next();
		            }
				}
			}
			else {
				System.out.println("Course Not Found.");
				return;
			}
		}
	}
	
	public void display_course(Scanner scanner, ArrayList<Course> course_list) {
		// display's all courses with admin capabilities (allows to see student list)
		System.out.println("Enter Course ID:");
		String course_id = scanner.nextLine(); 
		for(Course c : course_list) {
			if (c.course_id.equals(course_id)) {
				c.display_admin();
			}
		}
	}
	
	public void register_student(Scanner scanner, ArrayList<Student> s_list) {
		// Allows admin to create a new student and add to the student_list
		System.out.println("Enter Student Username:");
        String user = scanner.nextLine();
        System.out.println("Enter Student Password:");
        String pass = scanner.nextLine(); 
        System.out.println("Enter Student First Name:");
        String first = scanner.nextLine();
        System.out.println("Enter Student Last Name:");
        String last = scanner.nextLine();
		Student s = new Student(user, pass, first, last);
		s_list.add(s);
	}
	
	// REPORTS
	
	public void view_all_courses(ArrayList<Course> course_list) {
		for(Course c : course_list) {
			c.display_admin();
		}
	}
	
	public void view_full_courses(ArrayList<Course> course_list) {
		// view courses that are full
		boolean fullCourseExists = false; // See if any full courses exist
		for(Course c : course_list) {
			if (c.max_students == c.current_students) {
				c.display_admin();
				fullCourseExists = true;
			}
		}
		if (!fullCourseExists) {
			System.out.println("No full courses.");
		}
	}
	
	public void students_in_course(Scanner scanner, ArrayList<Course> course_list) {
		// VIEW all students that are in a specific course
		System.out.println("Enter Course Name:");
        String c_name = scanner.nextLine();
        
        System.out.println("Enter Course ID:");
        String c_id = scanner.nextLine();
        
        System.out.println("Enter Section Number:");
        int sect = scanner.nextInt();
        scanner.nextLine(); 
        
        for (Course c : course_list) {
        	if (c.course_id.equals(c_id) && c.course_name.equals(c_name) && c.course_section_number == sect) {
        		c.display_students();
			}
        	else {
        		System.out.println("Invalid.");
        	}
        }
	}
	
	public void view_student_courses(Scanner scanner, ArrayList<Student> student_list) {
		// View all courses a student is registered in
		System.out.println("Enter Student First Name:");
        String f_name = scanner.nextLine();
        
        System.out.println("Enter Student Last Name:");
        String l_name = scanner.nextLine();
        
        for (Student s : student_list) {
        	if (s.first_name.equals(f_name) && s.last_name.equals(l_name)) {
        		s.view_my_courses();
			}
        	else {
        		System.out.println("Invalid.");
        	}
        }
	}
	
	public void sort_courses(Scanner scanner, ArrayList<Course> course_list) {
		course_list.sort(Comparator.comparingInt(c -> c.current_students));
	}
	
}
