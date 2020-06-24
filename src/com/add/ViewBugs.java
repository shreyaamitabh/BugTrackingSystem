package com.add;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class ViewBugs
 */
@WebServlet("/ViewBugs")
public class ViewBugs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBugs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 PrintWriter out = res.getWriter();  
         res.setContentType("text/html");  
         out.println("<html><body>");  
         Connection dbCon = null;
         Statement stmt = null;
 		ResultSet rs= null;
 		String url = "jdbc:mysql://localhost:3306/bug_tracking";
 		String userName = "root";
 		
 		String pass = "amitabhs";
         try 
         {  
             Class.forName("com.mysql.jdbc.Driver");  
             dbCon = (Connection) DriverManager.getConnection(url, userName, pass);
             	stmt = (Statement) dbCon.createStatement();  
              rs = stmt.executeQuery("select * from customer");  
             out.println("<table border=1>");  
             out.println("<tr><th>Details</th><th>Type</th><th>Priority</th><tr>");  
             while (rs.next()) 
             {  
                 String name = rs.getString("name");  
                 String email = rs.getString("email");  
                 String phone = rs.getString("phone");
                 String occu = rs.getString("occupation");
                 out.println("<tr><td>" + name + "</td><td>" + email + "</td><td>" + occu + "</td></tr>");   
             }  
             out.println("</table>");  
             out.println("</html></body>");  
             dbCon.close();  
            }
         catch(Exception e)
         {
        	 e.printStackTrace();
         }
         finally {
        	 
         }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
