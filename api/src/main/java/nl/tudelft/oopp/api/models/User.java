package nl.tudelft.oopp.api.models;

import java.util.Collection;

public class User {


    public Long userId;
    public String name;
    public String username;
    public String email;
    public String password;

    public Collection<Reservation> reservations;
    public String type;

    public User(String name, String username, String email, String password, String type) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public User(Long userId, String name, String username, String email, String password, Collection<Reservation> reservations, String type) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.reservations = reservations;
        this.type = type;
    }

    public Collection<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
