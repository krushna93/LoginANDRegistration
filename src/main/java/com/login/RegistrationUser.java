package com.login;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/regi")
public class RegistrationUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Random random = new Random();

     
        int uniqueNumbers = 1000 + random.nextInt(9000);

        String unique=String.valueOf(uniqueNumbers);
        
        String fullName = request.getParameter("fullname");
        String username = request.getParameter("username");
        String contactNumber = request.getParameter("contact");
        String email = request.getParameter("gmail");
        String password = request.getParameter("password");
        String uniqueNumber = unique;

        try {
           

			Class.forName("oracle.jdbc.driver.OracleDriver");
		   Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","krushna");

          
            String query = "INSERT INTO loginuser (fullname, username, contact, email, password, uniqueNumber) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, contactNumber);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, password);
            preparedStatement.setString(6, uniqueNumber);

         
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                out.println("Registration successful!/n");
                out.println("uniqueId number"+uniqueNumber);
                
            } else {
                out.println("Registration failed. Please try again.");
            }

            preparedStatement.close();
            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
            System.out.println(e);
        }
    }
}
