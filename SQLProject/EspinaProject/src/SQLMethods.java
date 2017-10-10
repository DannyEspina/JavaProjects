
import java.sql.*;
import java.util.*;
public class SQLMethods {

	private Scanner scan;
	public SQLMethods() {
		scan = new Scanner(System.in);
	}
	public void printMenu() {
		System.out.println("*********************************************************************");
		System.out.println("*** 								  ***");
		System.out.println("*** 		Welcome to Online Registration System 		  ***");
		System.out.println("*** 								  ***");
		System.out.println("*********************************************************************");
		System.out.println("			1. Add a course");
		System.out.println("			2. Delete a course");
		System.out.println("			3. Add a student");
		System.out.println("			4. Delete a student");
		System.out.println("			5. Register a course");
		System.out.println("			6. Drop a course");
		System.out.println("			7. Check student registration");
		System.out.println("			8. Quit");
	}
	public void addCourse(Statement st){
		
		String code, title, answer= " ";
		do {
		System.out.print("Course Code: ");
		code = scan.nextLine();
		System.out.print("Course Title: ");
		title = scan.nextLine();
		
		try {
			st.executeUpdate("insert into Course values('"+ code + "','" + title +"')");
			System.out.println("Course added.");
		} catch(Exception e)
		{
			System.err.println(e.getMessage());			
		}
		System.out.println();
		System.out.println("Want to add another Course?(y/n)");
		answer = scan.nextLine();
		}while(answer.equalsIgnoreCase("y"));
	
	}
	public void deleteCourse(Statement st){
	
		String code, answer= " ";
		ResultSet rs;
		do {
		System.out.print("Please enter the Code: ");
		code = scan.nextLine();
		
		try {
			rs = st.executeQuery("select code from Course where code ='" +code+"'");
			if(rs.next()==false)
			{
				System.err.println("Error: Unkown course code.");
			}
			else{
				
			st.executeUpdate("delete from Registered where code = '"+code+"'");
			st.executeUpdate("delete from Course where code ='" +code+"'");
			System.out.println("Course deleted.");
			rs.close();
			}
		}catch(Exception e)
		{
			System.err.println(e.getMessage());			
		}
		System.out.println();
		System.out.println("Want to delete another Course?(y/n)");
		answer = scan.nextLine();
		}while(answer.equalsIgnoreCase("y"));
		
	}
	public void addStudent(Statement st) {
	
		String ssn, name, address, major, answer =" ";
		do {
		System.out.print("Student ID: ");
		ssn = scan.nextLine();
		
		System.out.print("Student's name: ");
		name = scan.nextLine();
		System.out.print("Student's address: ");
		address = scan.nextLine();
		
		System.out.print("Student's major: ");
		major = scan.nextLine();
		
		try {
			st.executeUpdate("insert into Student values('"+ssn+"','"+name+"','"+address+"','"+major+"')");
			System.out.println("Student added.");
		} catch(Exception e)
		{
			System.err.println(e.getMessage());			
		}
		System.out.println();
		System.out.println("Want to add another Student?(y/n)");
		answer = scan.nextLine();
		}while(answer.equalsIgnoreCase("y"));
	}
	public void deleteStudent(Statement st) 
	{
		String ssn, answer =" ";
		ResultSet rs;
		do{
		System.out.print("Please enter Student SSN: ");
		ssn= scan.nextLine();
		
		try {
		rs = st.executeQuery("select ssn from Student where ssn = '"+ssn+"'");
		if(rs.next()==false)
		{
			System.err.println("Error: Unkown student ssn.");
		}
		else{
		st.executeUpdate("delete from Registered where ssn = '"+ssn+"'");
		st.executeUpdate("delete from Student where ssn ='" +ssn+"'");
		System.out.println("Student deleted.");
		rs.close();
		}
		}catch(Exception e)
		{
			System.err.println(e.getMessage());			
		}
		System.out.println();
		System.out.println("Want to delete another Student? (y/n)");
		answer = scan.nextLine();
		}while(answer.equalsIgnoreCase("y"));
	}
	public void RegisterCourse(Statement st) {
	
		String ssn, code, year, semester, answer =" ";
		ResultSet rs;
		System.out.print("Student SSN:");
		ssn= scan.nextLine();
		
		do {
		System.out.print("Course Code:");
		code= scan.nextLine();
		
		System.out.print("Year:");
		year= scan.nextLine();
		
		System.out.print("Semester:");
		semester= scan.nextLine();

		
		try {
		
			rs = st.executeQuery("select code from Course where code ='" +code+"'");
			if(rs.next() == false)
			{
				System.err.println("Error: Unkown course code.");
			}
			rs = st.executeQuery("select ssn from Student where ssn = '"+ssn+"'");
			if(rs.next() == false)
			{
				System.err.println("Error: Unkown student ssn.");
			}
			rs = st.executeQuery("select * from Registered r, Student s where r.code = '"+code+"' and s.ssn = r.ssn and "
				+ "year = '"+year+"' and semester = '"+semester+"'");
			if (rs.next() == true)
			{
				System.err.println("Error: Course already registered.");
			}
			else
			{
				st.executeUpdate("insert into Registered values('"+ssn+"','"+code+"','"+year+"','"+semester+"','-')");
				System.out.println("Course registered.");
				rs.close();
			}
			
			}catch(Exception e)
			{
				System.err.println(e.getMessage());			
			}
		System.out.println();
		System.out.println("Want to register another Course? (y/n)");
		answer = scan.nextLine();
		}while(answer.equalsIgnoreCase("y"));
		
	}
	public void dropCourse(Statement st) {
	
		String code, ssn, year, semester, answer = " ";
		ResultSet rs;
		System.out.print("Student ssn: ");
		ssn = scan.nextLine();
		do {
		System.out.print("Course code: ");
		code = scan.nextLine();
		
		System.out.print("Year: ");
		year = scan.nextLine();
		
		System.out.print("Semester: ");
		semester = scan.nextLine();

		try {
			rs = st.executeQuery("select code from Course where code = '"+code+"'");
			
			if(rs.next() == false)
			{
				System.err.println("Error: Unkown course code.");
			}
			rs = st.executeQuery("select ssn from Student where ssn = '"+ssn+"'");
			if(rs.next() == false)
			{
				System.err.println("Error: Unkown student ssn.");
			}
			rs = st.executeQuery("select * from Registered where year = '"+year+"' and semester ='"+semester+"'");
			if(rs.next() == false)
			{
				System.err.println("Error: Year or semester for course is wrong.");
			}
			else
			{
				st.executeUpdate("delete from Registered where code ='"+code+"' and ssn ='"+ssn+"' and year = '"+year+"' and semester = '"+semester+"'");
				System.out.println("Course dropped.");
				rs.close();
			}
		}catch(Exception e)
		{
			System.err.println(e.getMessage());		
		}
		System.out.println();
		System.out.println("Want to drop another Course?(y/n)");
		answer = scan.nextLine();
		}while(answer.equalsIgnoreCase("y"));
		
	}
	public void checkRegistration(Statement st){
		String ssn, code, semester, year, title, answer = " ";
		ResultSet rs;
		do
		{
		System.out.print("Student SSN:");
		ssn = scan.nextLine();
		try {
			rs = st.executeQuery("select * from Registered r, Course c where r.ssn ='"+ssn+"' and c.code = r.code ");
			if(rs.next()==false)
			{
				System.err.println("Error: You're not regiestered in any classes.");
			}
			else
			{
				System.out.println("Student ID |   Course Code   |     Course Title    | Semester   | Year ");
				do
				{
					ssn = rs.getString("ssn");
					code = rs.getString("code");
					title = rs.getString("title");
					semester = rs.getString("semester");
					year = rs.getString("year");
					
					
					System.out.println(ssn +"   |    " +code +"   | "+title+" | "+semester+" | "+year+" ");
					
				}while(rs.next());
				System.out.println();
				
					System.out.println("Want to check another student registration? (y/n)");
					answer=scan.nextLine();
					rs.close();
				
			}
		}catch(Exception e)
		{
			System.err.println(e.getMessage());	
		}
		}while(answer.equalsIgnoreCase("y"));
		
	}
	
}
