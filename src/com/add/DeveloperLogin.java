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

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class DeveloperLogin
 */
@WebServlet("/DeveloperLogin")
public class DeveloperLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeveloperLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email= request.getParameter("Email");
		String password= request.getParameter("Password");
		Connection dbCon = null;
		PreparedStatement stmt = null;
		ResultSet rs= null;
		String url = "jdbc:mysql://localhost:3306/bug_tracking";
		String userName = "root";
		
		String pass = "amitabhs";
		String p1 = "select password from developer where email=? and password=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			dbCon = (Connection) DriverManager.getConnection(url, userName, pass);
			stmt=(PreparedStatement) dbCon.prepareStatement(p1);
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs=stmt.executeQuery();
			int done = 0;
			while(rs.next())
			{
				done = 1;
			}
			if(done==1)
			{
				 RequestDispatcher rd=request.getRequestDispatcher("DeveloperBugs.jsp");  
			        rd.forward(request, response);  
			}
			else
			{
			System.out.println("Error");
			}
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			
		}
		
	}


}
