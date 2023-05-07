package com.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public servlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    Connection conn=null;
   ResultSet rset=null;
   Statement stmnt;
    		String url="jdbc:mysql://localhost:3306/sakila";
    		String username="root";
    		String passcode="root";
    		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String values=request.getParameter("addname");
		
		//String query="INSERT INTO `names` (`id`, `first_name`) VALUES (''" +","+values +" );";
		ArrayList<String>listme=new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url,username,passcode);
			stmnt=conn.createStatement();
			String query="select * from names";
			rset=stmnt.executeQuery(query);
			int i=0;
			while(rset.next()) {
				listme.add(i, rset.getString("first_name"));
				i++;	
			}
		int id=listme.size()+1;
			stmnt=conn.createStatement();
			stmnt.execute("INSERT INTO `names` (`id`, `first_name`) VALUES ("+"'"+id +"'" +","+"'"+values+"'" +" );");
//			int i=0;
//			while(rset.next()) {
//				++i;
//			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
//	PrintWriter writer=response.getWriter();
//	for (int i=0;i<9;i++) {
//		String num=Integer.toString(i);
//		String temp1="firstname";
//		String temp2=temp1.concat(num);
//		String temp=request.getParameter(temp2);
//		writer.print(temp +"<br>");
//		System.out.println(temp);
//	writer.print(request.getParameter("firstname1")+" <br>");	
//	writer.print(request.getParameter("firstname2")+" <br>");	
//	writer.print(request.getParameter("firstname3")+" <br>");	
//	writer.print(request.getParameter("firstname4")+" <br>");	
//	writer.print(request.getParameter("firstname5")+" <br>");	
//	writer.print(request.getParameter("firstname6")+" <br>");	
//	writer.print(request.getParameter("firstname7")+" <br>");	
//	
// } 
	response.sendRedirect("message.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer=response.getWriter();
		//String values=request.getParameter("addname");
		//ArrayList<String>listme=new ArrayList();
		try {
			conn=DriverManager.getConnection(url,username,passcode);
			stmnt=conn.createStatement();
			String query="select * from names";
			rset=stmnt.executeQuery(query);
			int i=0;
			while(rset.next()) {
				//listme.add(i, rset.getString("first_name"));
				i++;
				String num=Integer.toString(i);
				String temp1="firstname";
				String temp2=temp1.concat(num);
				String temp=request.getParameter(temp2);
				stmnt=conn.createStatement();
				String query2="UPDATE `names` SET `first_name` = '"+temp+"'"+" WHERE (`id` = "+"'"+i+"');";
				stmnt.execute(query2);
				writer.print(temp +"<br>");
				System.out.println(temp);
				
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
}
}
