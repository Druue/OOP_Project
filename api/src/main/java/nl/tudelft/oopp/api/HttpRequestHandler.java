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
    private String host;
    private HttpClient client;
    private ObjectMapper objectMapper;
    public static User user;

    public static void saveUser(User input) {
        user = input;
    }

    /**
     * Creates a new HttpRequestHandler.
     * Used by frontend controllers to send requests,
     * and by the backend to convert models.
     */
    public HttpRequestHandler() {
        this.client = HttpClient.newHttpClient();
        this.host = "http://localhost:8080";
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Creates a new HttpRequestHandler.
     * Used in Unit testing, for mocking the HttpClient.
     * @param client The (mock) HttpClient.
     */
    public HttpRequestHandler(HttpClient client) {
        this.host = "http://localhost:8080";
        this.objectMapper = new ObjectMapper();
        this.client = client;
    }

    /**
     * Sends a POST request with some given parameters.
     *
     * @param path       the path on the server where the request should be sent.
     * @param parameters a map containing all parameters in the request, mapped as 'name,value'.
     * @return An HttpResponse object.
     */
    public <T, E> E post(String path, T parameters, Class<E> responseType) {
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
    public <T, E> E put(String path, T parameters, Class<E> responseType) {
        // Build HTTP request
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder().uri(URI.create(host + "/" + path))
                    .setHeader("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(parameters))).build();
            String r = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            return objectMapper.readValue(r, responseType);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Sends a GET request.
     *
     * @param path the path on the server where the request should be sent.
     * @return An HttpResponse object.
     */
    public <E> E get(String path, Class<E> responseType) {
        // Build HTTP request
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(host + "/" + path))
                .setHeader("Content-Type", "application/json").GET().build();
        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            Object result = objectMapper.readValue(httpResponse.body(), responseType);
            return responseType.cast(result);
        } catch (Exception e) {
            System.out.println("ObjectMapper is a pain in the ass");
        }

        return null;
    }

    /** This method converts an object from one class to another by serializing into JSON
     *      and then deserializing into the target class.
     *
     * @param from The object which is being converted.
     * @param to The {@link Class} in which the object is converted.
     * @param <T> A generic type parameter indicating the class of the inout object.
     * @param <E> A generic type parameter indicating the target class to convert to.
     * @return The converted object after serialization/deserialization, which is
     *      an instance of @param E.
     */
    public <T, E> E convertModel(T from, Class<E> to) {

        try {
            return objectMapper.readValue(objectMapper.writeValueAsString(from), to);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /** This method converts an object from a server model to the same API model and
     * vice-versa.
     *
     * @param from The object which is being converted.
     * @param to The {@link Class} in which the object is converted.
     * @param <T> A generic type parameter indicating the class of the inout object.
     * @param <E> A generic type parameter indicating the target class to convert to.
     * @return The converted object after serialization/deserialization, which is
     *      an instance of @param E.
     */
    public <T, E> E convertBetweenServerAndApi(T from, Class<E> to) {

        return objectMapper.convertValue(from, to);
    }
}
