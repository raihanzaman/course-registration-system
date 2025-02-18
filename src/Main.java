import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {
	
	public static ArrayList<Course> course_list = new ArrayList<>();
	public static ArrayList<Student> student_list = new ArrayList<>();
	static ArrayList<String[]> info = new ArrayList<>();
	
	// INITIALIZE THE ADMIN TO RUN THE PROGRAM
	static Admin admin = new Admin("Admin", "Admin001", "John", "Doe");
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		// CHECKS IF THE CSV HAS BEEN SERIALIZED ALREADY, IF NOT, READ THE CONTENTS
		File c_file = new File("CourseList.ser");
        if (c_file.exists()) {
            course_list = deserialize_course();
        } else {
        	csv_reader(scanner); // Extract course data from csv file.
        }
        
        // CHECK IF STUDENT LIST HAS BEEN SERIALIZED BEFORE
        File s_file = new File("StudentList.ser");
        if (s_file.exists()) {
            student_list = deserialize_student();
        }
        
		while (true) {
			System.out.println("1. Admin \n2. Student \n3. Exit");
			System.out.println("Enter your choice: ");
			if (scanner.hasNextInt()) {
				switch (scanner.nextInt()) {
				case 1:
					AdminLogin(scanner);
					AdminHome(scanner);
					break;
				case 2:
					Student s = StudentLogin(scanner);
					if (s != null) {
						StudentHome(scanner, s);
						break;
					}
					else {
						break;
					}
				case 3:
					System.out.println("Exiting Program...");
					serialize();
					scanner.close();
					return;
				default: 
					System.out.println("Invalid choice. Please enter 1, 2, or 3.");
					scanner.nextLine();
				}
			}
			else {
				System.out.println("Invalid input. Please enter a number.");
				scanner.nextLine();
			}
		} 
	}
	
	public static void csv_reader(Scanner scanner) {
		/*
		 This method is used to extract data from the MyUniversityCourses.csv file
		 and create courses to then add to the course_list of the program.
		 */
		String filePath = "MyUniversityCourses.csv"; 
	    
	    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
            	if (row == 0) {
            		// Ignore first row, we don't need the headers
            		row++;
            		continue;
            	}
            	String[] words = line.split(",");
                info.add(words);
                row++;
            }
            for (String[] course : info) {
            	// Extract the data from the row
            	String name = course[0];
            	String id = course[1];
            	int max = Integer.parseInt(course[2]);
            	int current = Integer.parseInt(course[3]);
            	String instructor = course[5];
            	int sect = Integer.parseInt(course[6]);
            	String loc = course[7];
            	//Create the course and add to course_list
    	    	Course c = new Course(name, id, max, current, instructor, sect, loc);
    	    	course_list.add(c);
    	    }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
	}
	
	private static void AdminLogin(Scanner scanner) {
		/*
		 * This is the login menu for the Admin. If you select 1, you have to login
		 * as an Admin. 
		 */
		String username, password;
		boolean loggedin = false;
	    while (true) {
	    	scanner.nextLine();
	        System.out.println("Enter Admin Username:");
	        username = scanner.nextLine(); // Get username input
	        
	        System.out.println("Enter Admin Password:");
	        password = scanner.nextLine(); // Get password input
	        
	        // Checks if entered user and pass matches the Admin credentials we initalized
	        if (admin.get_username().equals(username) && admin.get_password().equals(password)) {
	            System.out.println("Login successful!");
	            loggedin = true;
	            return; // Exit the program
	        } 
	        if (!loggedin) {
	        	System.out.println("Invalid credentials. Enter 1 to try again, or 2 to exit.");
	        	if (scanner.hasNextInt()) {
					switch (scanner.nextInt()) {
					case 1:
						break;
					case 2:
						return;
					default: 
						System.out.println("Invalid choice. Please enter 1 or 2.");
						scanner.nextLine();
					}
				}
				else {
					System.out.println("Invalid input. Please enter a number.");
					scanner.nextLine();
				}
	        }
	    }
	}
	
	private static Student StudentLogin(Scanner scanner) {
		/*
		 * This is the login menu for the Student. If you select 2, you have to login
		 * as a Student. 
		 */
		String username, password;
		boolean loggedin = false;
	    while (true) {
	    	scanner.nextLine();
	        System.out.println("Enter Student Username:");
	        username = scanner.nextLine(); // Get username input
	        System.out.println("Enter Student Password:");
	        password = scanner.nextLine(); // Get password input
	        
	        // Goes through students in student_list
	        for (Student s : student_list) {
		        if (s.get_username().equals(username) && s.get_password().equals(password)) {
		            System.out.println("Login successful!");
		            loggedin = true;
		            return s; // Exit the program
		        } 
		    }
	        if (!loggedin) {
	        	System.out.println("Invalid credentials. Enter 1 to try again, or 2 to exit.");
	        	if (scanner.hasNextInt()) {
					switch (scanner.nextInt()) {
					case 1:
						break;
					case 2:
						return null;
					default: 
						System.out.println("Invalid choice. Please enter 1 or 2.");
						scanner.nextLine();
					}
				}
				else {
					System.out.println("Invalid input. Please enter a number.");
					scanner.nextLine(); 
				}
	        }
	    }
	}
	
	public static void AdminHome(Scanner scanner) {
		/*
		 * This is the Admin home menu. When you login successfully, you get
		 * to choose whether you want to check out the course management options,
		 * or the reports options. You can exit back to the menu where you select if
		 * you are an admin or a student.
		 */
        while (true) {
            System.out.println("\nAdmin Home");
            System.out.println("1. Course Management");
            System.out.println("2. Reports");
            System.out.println("3. Log out");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                switch (scanner.nextInt()) {
                    case 1:
                    	scanner.nextLine();
                        AdminCourseManagement(scanner);
                        break;
                    case 2:
                    	scanner.nextLine();
                        AdminReports(scanner);
                        break;
                    case 3:
                        System.out.println("Logging off Admin Home...");
                        return; // Exit method
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
	}
	
	public static void AdminCourseManagement(Scanner scanner) {
		while (true) {
            System.out.println("\nCourse Management");
            System.out.println("1. Create a new course");
            System.out.println("2. Delete a course");
            System.out.println("3. Edit a course");
            System.out.println("4. Display course information (by course ID)");
            System.out.println("5. Register a student");
            System.out.println("6. Back to main menu");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                switch (scanner.nextInt()) {
                    case 1:
                        // Admin creates a new course
                    	scanner.nextLine();
                    	admin.create_course(scanner, course_list);
                        break;
                    case 2:
                        // Delete a course
                    	scanner.nextLine();
                    	admin.delete_course(scanner, course_list);
                        break;
                    case 3:
                        // Edit a course
                    	scanner.nextLine();
                    	admin.edit_course(scanner, course_list);
                        break;
                    case 4:
                        // Display course information
                    	scanner.nextLine();
                    	admin.display_course(scanner, course_list);
                        break;
                    case 5:
                        // Register a student
                    	scanner.nextLine();
                    	admin.register_student(scanner, student_list);
                        break;
                    case 6:
                        return; // Go back to the main menu
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1-6.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
	}
	
	public static void AdminReports(Scanner scanner) {
		while (true) {
            System.out.println("\nReports");
            System.out.println("1. View all courses");
            System.out.println("2. View full courses");
            System.out.println("3. View students in a specific course");
            System.out.println("4. View courses for a given student");
            System.out.println("5. Sort courses by student count");
            System.out.println("6. Back to main menu");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                switch (scanner.nextInt()) {
                    case 1:
                        // View all courses
                    	admin.view_all_courses(course_list);
                        break;
                    case 2:
                        // View full courses
                    	admin.view_full_courses(course_list);
                        break;
                    case 3:
                        // View students in a course
                    	scanner.nextLine();
                    	admin.students_in_course(scanner, course_list);
                        break;
                    case 4:
                        // View a student's courses
                    	scanner.nextLine();
                    	admin.view_student_courses(scanner, student_list);
                        break;
                    case 5:
                        // Sort courses
                    	scanner.nextLine();
                    	admin.sort_courses(scanner, course_list);
                        break;
                    case 6:
                        return; // Go back to the main menu
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1-6.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
	}
	
	public static void StudentHome(Scanner scanner, Student s) {
		/*
		 * This is the Student home menu. When you login successfully, you
		 * can check out the course management options. You can exit back to 
		 * the menu where you select if you are an admin or a student.
		 */
		while (true) {
            System.out.println("\nStudent Home");
            System.out.println("1. Course Management");
            System.out.println("2. Log off Student");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                switch (scanner.nextInt()) {
                    case 1:
                    	scanner.nextLine();
                        StudentCourseManagement(scanner, s);
                        break;
                    case 2:
                        System.out.println("Logging off Student Home...");
                        return; // Exit method
                    default:
                        System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
	}
	
	public static void StudentCourseManagement(Scanner scanner, Student s) {
		while (true) {
            System.out.println("\nCourse Management");
            System.out.println("1. View all courses");
            System.out.println("2. View all courses that are not full");
            System.out.println("3. Register in a course");
            System.out.println("4. Withdraw from a course");
            System.out.println("5. View courses you are registered in");
            System.out.println("6. Back to main menu");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                switch (scanner.nextInt()) {
                    case 1:
                        s.view_all_courses(course_list);
                        break;
                    case 2:
                        s.view_not_full_courses(course_list);
                        break;
                    case 3:
                        scanner.nextLine();
                        s.register_course(scanner, course_list);
                        break;
                    case 4:
                    	scanner.nextLine();
                        s.withdraw_course(scanner, course_list);
                        break;
                    case 5:
                        s.view_my_courses();
                        break;
                    case 6:
                        return; // Go back to the main menu
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1-6.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
	}
	
	public static void serialize() {
		try {
			//FileOutput Stream writes data to a file
			FileOutputStream fos_course = new FileOutputStream("CourseList.ser");
			FileOutputStream fos_student = new FileOutputStream("StudentList.ser");
			
			//ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oos_course = new ObjectOutputStream(fos_course);
			ObjectOutputStream oos_student = new ObjectOutputStream(fos_student);
			
			//Writes the specific object to the OOS
			oos_course.writeObject(course_list);
			oos_student.writeObject(student_list);
			
			//Close both streams
			oos_course.close();
			fos_course.close();
			oos_student.close();
			fos_student.close();
			System.out.println("Serialization complete");
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public static ArrayList<Course> deserialize_course() {
		 try{
			  //FileInputSystem receives bytes from a file
		      FileInputStream fis_course = new FileInputStream("CourseList.ser");
		      
		      //ObjectInputStream does the deserialization-- it reconstructs the data into an object
		      ObjectInputStream ois_course = new ObjectInputStream(fis_course);
		      
		      //Cast as Course Arraylist. readObject will take the object from ObjectInputStream
		      @SuppressWarnings("unchecked")
			  ArrayList<Course> d_course = (ArrayList<Course>)ois_course.readObject();
		      ois_course.close();
		      fis_course.close();
		      return d_course;
		    }
		 catch(IOException | ClassNotFoundException e) {
		       e.printStackTrace();
		    }
		 	return new ArrayList<Course>();
	}
	
	public static ArrayList<Student> deserialize_student() {
		 try{
			  //FileInputSystem receives bytes from a file
		      FileInputStream fis_student = new FileInputStream("StudentList.ser");
		      
		      //ObjectInputStream does the deserialization-- it reconstructs the data into an object
		      ObjectInputStream ois_student = new ObjectInputStream(fis_student);

		      //Cast as Student ArrayList. readObject will take the object from ObjectInputStream
			  @SuppressWarnings("unchecked")
			  ArrayList<Student> d_student = (ArrayList<Student>)ois_student.readObject();
		      ois_student.close();
		      fis_student.close();
		      return d_student;
		    }
		    catch(IOException | ClassNotFoundException e) {
		       e.printStackTrace();
		    }
		 	return new ArrayList<Student>();
	}

}
