package nl.tudelft.oopp.demo.communication;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ServerCommunication {

    private static HttpClient client = HttpClient.newHttpClient();

    /**
     * Retrieves a quote from the server.
     * @return the body of a get request to the server.
     * @throws Exception if communication with the server fails.
     */
    public static String getQuote() {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/quote")).build();
        HttpResponse<String> response = null;
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

    public static String ping() {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/ping")).build();
        HttpResponse<String> response = null;
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

    public static String relay(String input) {


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/relay"))
                .setHeader("Content-Type","text/plain;charset=UTF-8")
                .POST(
                        HttpRequest.BodyPublishers.ofByteArray(("input="+input).getBytes(StandardCharsets.UTF_8))
                )
                .build();
        System.out.println(request.headers());
        System.out.println(request.method());
        System.out.println(request.bodyPublisher().toString());
        System.out.println(request.bodyPublisher().get().contentLength());

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (Exception e) {
            e.printStackTrace();
            return "Communication with server failed";
        }

        System.out.println("Status: " + response.statusCode());
        System.out.println("Response: " + response.body());
        return response.body();
    }
}
