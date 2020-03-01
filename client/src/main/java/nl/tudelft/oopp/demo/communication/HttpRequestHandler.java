package nl.tudelft.oopp.demo.communication;

import java.net.URI;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class HttpRequestHandler {

    private static HttpClient client = HttpClient.newHttpClient();

    /**
     * Sends a POST request with some given parameters.
     * @param url the URL to send the POST request to.
     * @param path the path on the server where the request should be sent.
     * @param parameters a map containing all parameters in the request, mapped as 'name,value'.
     * @return An HttpResponse object.
     */
    public static HttpResponse<String> post(String url, String path, JSONObject parameters) {
        // Build HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/" + path))
                .setHeader("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(parameters.toString()))
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception ignored) {
            // Do nothing.
        }

        return null;
    }

    /**
     * Sends a GET request.
     * @param url the URL to send the GET request to.
     * @param path the path on the server where the request should be sent.
     * @return An HttpResponse object.
     */
    public static HttpResponse<String> get(String url, String path) {
        // Build HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/" + path))
                .setHeader("Content-Type","application/json")
                .GET()
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception ignored) {
            // Do nothing.
        }

        return null;
    }
}
