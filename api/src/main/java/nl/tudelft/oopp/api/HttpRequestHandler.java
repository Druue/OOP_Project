package nl.tudelft.oopp.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import nl.tudelft.oopp.api.models.User;

public class HttpRequestHandler {
    private static final String host = "http://localhost:8080";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Gson gson = new GsonBuilder()
            .serializeNulls()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static User user;

    public static void saveUser(User input) {
        user = input;
    }

    /**
     * Sends a POST request with some given parameters.
     *
     * @param path       the path on the server where the request should be sent.
     * @param parameters a map containing all parameters in the request, mapped as 'name,value'.
     * @return An HttpResponse object.
     */
    public static <T, E> E post(String path, T parameters, Class<E> responseType) {
        // Build HTTP request
        HttpRequest request = null;

        try {
            String s = objectMapper.writeValueAsString(parameters);
            request = HttpRequest.newBuilder().uri(URI.create(host + "/" + path))
                    .setHeader("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(parameters))).build();
            String r = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            return objectMapper.readValue(r, responseType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Sends a PUT request with some given parameters.
     *
     * @param path       the path on the server where the request should be sent.
     * @param parameters a map containing all parameters in the request, mapped as 'name,value'.
     * @return An HttpResponse object.
     */
    public static <T, E> E put(String path, T parameters, Class<E> responseType) {
        // Build HTTP request
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder().uri(URI.create(host + "/" + path))
                    .setHeader("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(parameters))).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return objectMapper.readValue(client.send(request, HttpResponse.BodyHandlers.ofString()).body(),
                    responseType);
        } catch (Exception ignored) {
            // Do nothing.
        }

        return null;
    }

    /**
     * Sends a GET request.
     *
     * @param path the path on the server where the request should be sent.
     * @return An HttpResponse object.
     */
    public static <E> E get(String path, Class<E> responseType) {
        // Build HTTP request
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(host + "/" + path))
                .setHeader("Content-Type", "application/json").GET().build();
        try {
            return objectMapper.readValue(client.send(request, HttpResponse.BodyHandlers.ofString()).body(),
                    responseType);
        } catch (Exception ignored) {
            // Do nothing.
        }

        return null;
    }

    public static <T, E> E convertModel(T from, Class<E> to) {

        try {
            return objectMapper.readValue(objectMapper.writeValueAsString(from), to);
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
    }
}
