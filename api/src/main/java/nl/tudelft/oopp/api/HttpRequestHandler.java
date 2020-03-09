package nl.tudelft.oopp.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.tudelft.oopp.api.models.User;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpRequestHandler {
    private static final String host = "http://localhost:8080";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    public static User user;

    public static void saveUser(User input) {
        user = input;
    }

    /**
     * Sends a POST request with some given parameters.
     * @param path the path on the server where the request should be sent.
     * @param parameters a map containing all parameters in the request, mapped as 'name,value'.
     * @return An HttpResponse object.
     */
    public static <T, E> E post(String path, T parameters, Class<E> responseType) {
        // Build HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(host + "/" + path))
                .setHeader("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(parameters)))
                .build();
        try {
            return gson.fromJson(client.send(request, HttpResponse.BodyHandlers.ofString()).body(), responseType);
        } catch (Exception ignored) {
            // Do nothing.
        }

        return null;
    }

    /**
     * Sends a PUT request with some given parameters.
     * @param path the path on the server where the request should be sent.
     * @param parameters a map containing all parameters in the request, mapped as 'name,value'.
     * @return An HttpResponse object.
     */
    public static <T, E> E put(String path, T parameters, Class<E> responseType) {
        // Build HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(host + "/" + path))
                .setHeader("Content-Type","application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(parameters)))
                .build();
        try {
            return gson.fromJson(client.send(request, HttpResponse.BodyHandlers.ofString()).body(), responseType);
        } catch (Exception ignored) {
            // Do nothing.
        }

        return null;
    }

    /**
     * Sends a GET request.
     * @param path the path on the server where the request should be sent.
     * @return An HttpResponse object.
     */
    public static <E> E get(String path, Class<E> responseType) {
        // Build HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(host + "/" + path))
                .setHeader("Content-Type","application/json")
                .GET()
                .build();
        try {
            return gson.fromJson(client.send(request, HttpResponse.BodyHandlers.ofString()).body(), responseType);
        } catch (Exception ignored) {
            // Do nothing.
        }

        return null;
    }

    public static <T, E> E convertToApiModel(T input, Class<E> outputType) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.fromJson(gson.toJson(input), outputType);
    }
}
