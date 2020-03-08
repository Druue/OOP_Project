package nl.tudelft.oopp.demo.models;

/**
 * This is a generic class for mapping client requests after a successful login.
 *
 * @param <T> The type of information the client sends.
 */
public class ClientRequest<T> {

    private String netID;
    private String role;
    private T body;


    /** Create a new ClientRequest object with the given user NetID and role and the
     * object the user is sending.
     * @param netID The user NetID that is received.
     * @param role The user role that is received.
     * @param body The information the user is sending.
     */
    public ClientRequest(String netID, String role, T body) {
        this.netID = netID;
        this.role = role;
        this.body = body;
    }


    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNetID() {
        return netID;
    }

    public void setNetID(String netID) {
        this.netID = netID;
    }
}
