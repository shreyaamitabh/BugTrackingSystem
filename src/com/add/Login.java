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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		String p1 = "select password from customer where email=? and password=?";
		String p2 = "select name from customer where email=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			dbCon = (Connection) DriverManager.getConnection(url, userName, pass);
			stmt=(PreparedStatement) dbCon.prepareStatement(p2);
			stmt.setString(1, email);
			rs= stmt.executeQuery();
			while(rs.next())
			{
				String uname= rs.getString("name");
				 HttpSession session=request.getSession();
			      session.setAttribute("uname",uname);
				
			}
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
				 RequestDispatcher rd=request.getRequestDispatcher("bugs.jsp");  
			        rd.forward(request, response);  
			}
			else
			{
				 RequestDispatcher rd=request.getRequestDispatcher("Invalid.html");  
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

