package nl.tudelft.oopp.demo.communication;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

public class ServerCommunication {

    private static HttpClient client = HttpClient.newHttpClient();
    private static String host = "http://localhost:8080";

    /**
     * Retrieves a quote from the server.
     * @return the body of a get request to the server.
     */
    public static String getQuote() {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/quote")).build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return "Communication with server failed";
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return response.body();
    }

    /**
     * Sends the server a simple GET request.
     * @return the body of a get request of the server.
     */
    public static String ping() {
        // Send GET request to host at path /ping
        HttpResponse<String> response = HttpRequestHandler.get(host,"ping");
        return response.body();
    }

    /** Sends the server a simple POST request.
     * @param input the input parameter to be relayed back.
     * @return "Your input: " plus the body of the POST request.
     */
    public static JSONObject relay(String input) throws JSONException {
        JSONObject parameters = new JSONObject();
        parameters.put("input",input);
        // Send POST request to host at path /relay
        HttpResponse<String> response = HttpRequestHandler.post(host, "relay", parameters);
        return new JSONObject(response.body());
    }

    public static JSONObject login(JSONObject parameters) throws JSONException {
        HttpResponse<String> response = HttpRequestHandler.post(host, "login", parameters);
        return new JSONObject(response.body());
    }

    public static JSONObject register(JSONObject parameters) throws JSONException {
        HttpResponse<String> response = HttpRequestHandler.post(host, "register", parameters);
        return new JSONObject(response.body());
    }
}
