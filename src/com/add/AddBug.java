package com.add;

import java.io.IOException;

import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/AddBug")
public class AddBug extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBug() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	String name= request.getParameter("Details");
	String email= request.getParameter("Type");
	String password= request.getParameter("Priority");
	Connection dbCon = null;
	PreparedStatement pstmt = null;
	String url = "jdbc:mysql://localhost:3306/bug_tracking";
	String userName = "root";

	String pass = "amitabhs";
	String p1 = "insert into bugs(details, type, priority) "
			+ "values(?,?,?)";
	try {
		Class.forName("com.mysql.jdbc.Driver");
		dbCon = (Connection) DriverManager.getConnection(url, userName, pass);
	
		pstmt = (PreparedStatement) dbCon.prepareStatement(p1);
		System.out.println("here1");
		pstmt.setString(1, name);
		pstmt.setString(2, email);
		pstmt.setString(3, password);
		System.out.println("here2");
		int i=pstmt.executeUpdate();  
		if(i==1)
		{
			 RequestDispatcher rd=request.getRequestDispatcher("bugs.jsp");  
		        rd.forward(request, response);  
		}
		else
		{
			System.out.println("thullu");
			
		}
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally 
	{
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
}
