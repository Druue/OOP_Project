package nl.tudelft.oopp.api.models;

public class ClientRequest<T> {

    private String username;
    private UserKind role;
    private T body;

    public ClientRequest() {
    }

    /** Create a new client request object to be sent to the server.
     * @param username The username of the user.
     * @param role The role of the user.
     * @param body The generic body containing different information
     *             for different types of requests.
     */
    public ClientRequest(String username, UserKind role, T body) {
        this.username = username;
        this.role = role;
        this.body = body;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserKind getRole() {
        return role;
    }

    public void setRole(UserKind role) {
        this.role = role;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
