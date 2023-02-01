package com.ty;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String url="jdbc:mysql://localhost:3306/Login";
		String uname="root";
		String pass="root";
		
		try {
			PrintWriter out=response.getWriter();
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,uname,pass);
			String n=request.getParameter("username");
			String p=request.getParameter("password");
			PreparedStatement psmt=con.prepareStatement("select uname from Login where unam=? and password=?");
			psmt.setString(1, n);
			psmt.setString(2, p);
			ResultSet rs=psmt.executeQuery();
			if(rs.next()) {
				RequestDispatcher rd=request.getRequestDispatcher("Welcome.jsp");
				rd.forward(request, response);
			}else {
				out.print("<font color=red size=18> Login Failed!!<br>");
				out.print ("<a href=Login.jsp>Try Again!! </a>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
