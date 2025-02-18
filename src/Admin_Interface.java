import java.util.*;

public interface Admin_Interface {
    // Course Management
    void create_course(Scanner scanner, ArrayList<Course> course_list);
    void delete_course(Scanner scanner, ArrayList<Course> course_list);
    void edit_course(Scanner scanner, ArrayList<Course> course_list);
    void display_course(Scanner scanner, ArrayList<Course> course_list);
    void register_student(Scanner scanner, ArrayList<Student> s_list);
    
    // Reports
    void view_all_courses(ArrayList<Course> course_list);
    void view_full_courses(ArrayList<Course> course_list);
    void students_in_course(Scanner scanner, ArrayList<Course> course_list);
    void view_student_courses(Scanner scanner, ArrayList<Student> student_list);
    void sort_courses(Scanner scanner, ArrayList<Course> course_list);
}
