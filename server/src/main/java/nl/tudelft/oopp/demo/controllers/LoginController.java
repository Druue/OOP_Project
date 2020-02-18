package nl.tudelft.oopp.demo.controllers;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.DataInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.*;

@Controller
public class LoginController {
    /*
    * GET endpoint to validate authentication of a user
    * The class has one method sendLoginPage() that sends the login.fxml page through
    * */

    @GetMapping("login")
    @ResponseBody
    public String validateAuthentication(ServerHttpRequest request , ServerHttpResponse response) {
        // Connect to the database and establish
        // whether a user with that NetID and password exists
        // 1) The user exists - send response "Authentication Successful"
        // 2) There is no such password or NetID - send response "Authentication Failed"
        String answer = null;

        try {

            ObjectInputStream input = new ObjectInputStream( request.getBody());
            String receivedInfo = (String) input.readObject();
            String[] details = receivedInfo.split(";");
            String NetID = details[0];
            String password = details[1];
            String database = "jdbc:postgresql://localhost:5432/users";

            try(Connection con =  DriverManager.getConnection(database , "postgres" , "Mitashki");
                Statement userQuery = con.createStatement();
                ResultSet databaseResponse =  userQuery.executeQuery("SELECT * FROM users " +
                                                                         "WHERE NetID = " + NetID + " AND password = " + password);) {

                if(databaseResponse.getFetchSize() == 0) answer = "Authentication Failed";
                else answer = "Authentication Successful";

            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
                // This part should never happen if the code is correctly written
            }
        }
        catch (Exception e) {
            System.out.println(e);
            // This part should never happen if the code is written correctly
        }

        return answer;

    }


}
