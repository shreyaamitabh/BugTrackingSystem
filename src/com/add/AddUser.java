package com.add;

import java.io.IOException;

import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	String name= request.getParameter("Name");
	String email= request.getParameter("Email");
	String password= request.getParameter("Password");
	String phone= request.getParameter("Telephone");
	String occupation= request.getParameter("Occupation");
	Connection dbCon = null;
	ResultSet rs= null;
	PreparedStatement pstmt = null;
	String url = "jdbc:mysql://localhost:3306/bug_tracking";
	String userName = "root";
	String p2 = "select name from customer where email=?";
	String pass = "amitabhs";
	String p1 = "insert into customer(name, email, password, phone, occupation) "
			+ "values(?,?,?,?,?)";
	try {
		Class.forName("com.mysql.jdbc.Driver");
		dbCon = (Connection) DriverManager.getConnection(url, userName, pass);
		pstmt = (PreparedStatement) dbCon.prepareStatement(p2);
		pstmt.setString(1, email);
		rs=pstmt.executeQuery();
		while(rs.next())
		{
			RequestDispatcher rd=request.getRequestDispatcher("PleaseLogin.html");  
	        rd.forward(request, response);  
		}
		pstmt = (PreparedStatement) dbCon.prepareStatement(p1);
		System.out.println("here1");
		pstmt.setString(1, name);
		pstmt.setString(2, email);
		pstmt.setString(3, password);
		pstmt.setString(4, phone);
		pstmt.setString(5, occupation);
		int i=pstmt.executeUpdate();  
		if(i==1)
		{
			HttpSession session=request.getSession();
		      session.setAttribute("uname",name);
			RequestDispatcher rd=request.getRequestDispatcher("bugs.jsp");  
	        rd.forward(request, response);  
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
