<%@page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" href="/Users/shreyaamitabh/Downloads/bootstrap-4.4.1-dist/css/bootstrap.min.css" >
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="bugs.css">
<title>Insert title here</title>
</head>
<body>
 		<div class="container">
  <p id="head">All bugs</p>
  
  <table class="table table-striped">
    <thead>
      <tr>
       <th>Details</th>
  			<th>Type</th>  			
  			<th>Priority</th>
  			<th>Files</th>
  			<th>Solution</th>
      </tr>
    </thead>
    <tbody>
 		
<%
Connection dbCon = null;
Statement stmt = null;
ResultSet rs= null;
String url = "jdbc:mysql://localhost:3306/bug_tracking";
String userName = "root";

String pass = "amitabhs";
try{Class.forName("com.mysql.jdbc.Driver");  
dbCon = (Connection) DriverManager.getConnection(url, userName, pass);
	stmt = (Statement) dbCon.createStatement();  
rs = stmt.executeQuery("select * from bugs order by priority ");  
	while(rs.next())
	{			
%>

  		<tr>
  			<td><%=rs.getString("details")%></td>
  			<td><%=rs.getString("type")%></td>
  			<td><%=rs.getString("priority")%></td>
  			<td><%=rs.getString("file")%></td>
  			<td>
  			<% String solution=rs.getString("solution");
  			if(solution==null) { %>
  			<input type="text" placeholder="Update solution here" />
  			<% } else { %> 
  			<input type="text" value=<%= rs.getString("solution") %> />
  			 <% } %> 
  			</td>
		</tr>
		<%
}//while

%>  	
	    </tbody>
  </table>
</div>

	<%
}
catch(Exception e)
{
	e.printStackTrace();
}
 %>  
</body>
</html>