/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package jdbcoracle;

/**
 *
 * @author aic
 */
// You need to import the java.sql package to use JDBC
import java.sql.*;
import java.util.*;
// We import java.io to be able to read from the command line

import java.io.*;

public class JDBCORACLE {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args)
	  	throws SQLException, IOException
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver"); //Load the Oracle JDBC driver
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Could not load the driver");
		}
		try //connect to oracle and validate the user and password
		{
			String Oracleuser,Oraclepass;
			Oracleuser = readEntry("Oracle username:");
			Oraclepass = readEntry("Oracle Password:");
			String url="jdbc:oracle:thin:@fedora2.uscupstate.edu:1521:xe";
			Connection conn = DriverManager.getConnection(url,Oracleuser,Oraclepass);//connect oracle
			
                        Statement st=conn.createStatement();
//                        st.executeUpdate("delete from login");
//                        st.executeUpdate("insert into login values('user1','123456')");
//                        st.executeUpdate("insert into login values('user2','123456')");
//
//                        ResultSet rs=st.executeQuery("select * from login");
//                        String user="";
//                        String password="";
//                        while (rs.next())
//                        {
//                           user=rs.getString(1);
//                           password=rs.getString(2);
//                           System.out.println("ID: " + user + "   password: " +password);
//                        }
//                        rs.close();
//                        st.close();
//                        conn.close();		
		
		
		Scanner scan = new Scanner(System.in);
		SQLMethods sql = new SQLMethods();
		boolean quit = false;
		do{
			sql.printMenu();
			int response = scan.nextInt();
			
			switch(response){
			case 1:
				sql.addCourse(st);
				break;
			case 2:
				sql.deleteCourse(st);
				break;
			case 3:
				sql.addStudent(st);
				break;
			case 4:
				sql.deleteStudent(st);
				break;
			case 5:
				sql.RegisterCourse(st);
				break;
			case 6:
				sql.dropCourse(st);
				break;
			case 7:
				sql.checkRegistration(st);
				break;
			case 8:
				quit = true;
                st.close();
				conn.close();	
				System.out.println("Quiting... GoodBye.");
				break;
			default: 
				System.err.println("Invalid Input. Try again...");
			}
		}while(!quit);
		
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());			
		}
	}
	
	 // Utility function to read a line from standard input
   static String readEntry(String prompt)
   {
      try
      {
         StringBuffer buffer = new StringBuffer();
         System.out.print(prompt);
         System.out.flush();
         int c = System.in.read();
         while (c != '\n' && c != -1)
         {
            buffer.append((char)c);
            c = System.in.read();
         }
         return buffer.toString().trim();
      }
      catch(IOException e)
      {
         return "";
      }
   }

    
    
}

