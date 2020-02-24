package nl.tudelft.oopp.demo.communication;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpRequestHandler {

    private static HttpClient client = HttpClient.newHttpClient();

    /**
     * Sends a POST request with some given parameters.
     * @param url the URL to send the POST request to.
     * @param path the path on the server where the request should be sent.
     * @param parameters a map containing all parameters in the request, mapped as 'name,value'.
     * @return An HttpResponse object.
     */
    public static <T> HttpResponse<String> post(String url, String path, Map<String,T> parameters) {

        // Encode all parameters
        StringBuilder encParams = new StringBuilder();
        for (Map.Entry<String, T> entry : parameters.entrySet()) {
            if (encParams.length() > 0) {
                encParams.append("&");
            }
            encParams.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            encParams.append("=");
            encParams.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }

        // Build HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/" + path))
                .setHeader("Content-Type","application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(encParams.toString()))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception ignored) {
            // Do nothing.
        }

        return response;
    }

    /**
     * Sends a GET request.
     * @param url the URL to send the GET request to.
     * @param path the path on the server where the request should be sent.
     * @return An HttpResponse object.
     */
    public static HttpResponse<String> get(String url, String path) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/" + path))
                .setHeader("Content-Type","application/x-www-form-urlencoded")
                .GET()
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception ignored) {
            // Do nothing.
        }

        return response;
    }
}
