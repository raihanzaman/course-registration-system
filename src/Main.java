import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {
	
	public static ArrayList<Course> course_list = new ArrayList<>();
	public static ArrayList<Student> student_list = new ArrayList<>();
	Admin a;
	
	static ArrayList<String[]> info = new ArrayList<>();

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
	    
		String filePath = "MyUniversityCourses.csv"; 
	    
	    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
            	if (row == 0) {
            		continue;
            	}
            	String[] words = line.split(",");
                info.add(words);
                row++;
            }
            for (String[] course : info) {
    	    	Course c = new Course(course[0], course[1], Integer.parseInt(course[2]), Integer.parseInt(course[3]), course[5], Integer.parseInt(course[6]), course[7]);
    	    	course_list.add(c);
    	    }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } 
	    
		input.close();
	}

}
