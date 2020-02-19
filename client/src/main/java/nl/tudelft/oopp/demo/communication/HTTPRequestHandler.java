package nl.tudelft.oopp.demo.communication;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HTTPRequestHandler {

    private static HttpClient client = HttpClient.newHttpClient();

    public static HttpResponse<String> post(String URL, String path,  Map<String,String> parameters) {

        // Encode all parameters
        StringBuilder encParams = new StringBuilder();
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if (encParams.length() > 0) {
                encParams.append("&");
            }
            encParams.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            encParams.append("=");
            encParams.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }

        // Build HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL+"/"+path))
                .setHeader("Content-Type","application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(encParams.toString()))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {} // Do nothing.

        return response;
    }

    public static HttpResponse<String> get(String URL, String path) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL+"/"+path))
                .setHeader("Content-Type","application/x-www-form-urlencoded")
                .GET()
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {} // Do nothing.

        return response;
    }
}
