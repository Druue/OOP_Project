package nl.tudelft.oopp.demo.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    /**
     * A function that handles the login requests.
     * @param requestbody the request that contains the login information.
     * @return a JSONObject response that contains a status and a message.
     */

    @PostMapping(value = "login", consumes = "application/json", produces = "application/json")
    public @ResponseBody String relay(@RequestBody String requestbody) throws JSONException {
        System.out.println("Request received!");

        // Copy the JSON request.
        JSONObject jsonRequest = new JSONObject(requestbody);
        String netID = jsonRequest.getString("NetID");
        String password = jsonRequest.getString("Password");

        JSONObject response = new JSONObject();
        if (netID.equals("123") && password.equals("badpass")) {
            response.put("status","OK");
            response.put("message","Login Successful!");
        } else {
            response.put("status", "ERROR");
            response.put("message", "Invalid username/password combination.");
        }
        return response.toString();
    }
}
