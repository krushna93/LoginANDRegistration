package com.login;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/LoginServlet")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

       
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
 		   Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","krushna");

           
            Statement statement = connection.createStatement();

          
            String sql = "SELECT * FROM loginuser WHERE (username = ? OR uniqueNumber = ?) AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
              
                out.println("<h1>Welcome,         " + username + "!</h1>");
            } else {
              
                out.println("<h1>Login failed. Invalid username or password.</h1>");
            }

          
            resultSet.close();
            preparedStatement.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
          
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
        } catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
    }
}
