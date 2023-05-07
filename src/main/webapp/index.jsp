<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Array"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit App</title>
</head>
<body>
<%
Connection connection=null;
Statement statement=null;
ResultSet resultSet=null;
Class.forName("com.mysql.jdbc.Driver");
String url="jdbc:mysql://localhost:3306/sakila";
String passcode="root";
String username="root";
ArrayList<String>mylist=new ArrayList();
int j=0;
try{
connection=DriverManager.getConnection(url,username,passcode);
statement=connection.createStatement();
resultSet=statement.executeQuery("select * from names");

int i=0;
while(resultSet.next()){
mylist.add(i, resultSet.getString("first_name"));
i++;
}

System .out.print("connection succeded");}
catch(Exception e){
	e.printStackTrace();
}
int melist=mylist.size();
%>
<form action="<%=request.getContextPath()%>/servlet" method="post">

<c:forEach var="i" begin="1" end= "<%=melist%>" step="1">
<% String name="firstname";%>
<input type="text" name="<%=name%>${i}" value="<%=mylist.get(j) %>" >
<%j++; %>
<br/>
</c:forEach>
<input type="submit" value="submit">
</form> <br>

<form action="<%=request.getContextPath()%>/servlet" method="get">
Add Names<input type="text" name="addname" required="required">
<input type="submit" value="add">
</form>


</body>
</html>