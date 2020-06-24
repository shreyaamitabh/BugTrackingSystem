<%@page import="java.sql.*"  %>
<%@page import="java.io.*"  %>
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
<%
HttpSession session1=request.getSession(false);
String myName=(String)session1.getAttribute("uname");
PrintWriter pwriter = response.getWriter();
pwriter.println((String)session1.getAttribute("uname"));
%>
<nav class="navbar navbar-expand-lg bg-dark navbar-dark fixed-top">
  <a class="navbar-brand" href="index.html">Home</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarText">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#">Contact us <span class="sr-only">(current)</span></a>
      </li>
    </ul>
    <span class="navbar-text">
     Welcome <%=  myName %>
    </span>
  </div>
</nav>
 		<div class="container">
 
  <button id="add" type= button" class="btn btn-info"><a style="color:white;" href="AddBug.html">Add new bug</a></button>
  <table class="table table-striped">
    <thead>
      <tr>
       <th>Details</th>
  			<th>Type</th>  			
  			<th>Priority</th>
  			<th>File</th>
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
rs = stmt.executeQuery("select * from bugs");  
	while(rs.next())
	{			
%>

  		<tr>
  			<td><%=rs.getString("details")%></td>
  			<td><%=rs.getString("type")%></td>
  			<td><%=rs.getString("priority")%></td>
  			<td><%=rs.getString("file")%></td>
  			<td><%=rs.getString("solution")%></td>
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