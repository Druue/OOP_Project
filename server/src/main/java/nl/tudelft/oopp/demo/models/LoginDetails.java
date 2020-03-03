package nl.tudelft.oopp.demo.models;


// This class is only used when a user logs in.
// The body of the login request is mapped to the fields of this class
// through the @ResponseBody annotation
// The information received is as follows:
// 1) The NetID of the user
// 2) The provided password in a hashed form, so as not to be stolen by a malicious user

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class LoginDetails {

    private String netID;
    private String password;


    /**
     * An object containing a user's login information. Only used during the login process.
     * @param netID the user's NetID.
     * @param password the user's password.
     */
    public LoginDetails(String netID, String password) {

        this.netID = netID;
        this.password = password;
    }

    public String getNetID() {
        return netID;
    }

    public void setNetID(String netID) {
        netID = netID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Entity
    @Table(name = "Users")
    public static class Users {

        @Column(name = "id")
        private long id;
        @Id
        @Column(name = "name")
        private String name;

        @Column(name = "dateTime")
        private double dateTime;

        @Column(name = "hasFoodCourt")
        private boolean hasFoodCourt;

        @Column(name = "availableTimeSlots")
        private double availableTimeSlots;

        @Column(name = "imageID")
        private long imageID;


        /**
         * Create a new Building instance.
         *
         * @param id Unique identifier as to be used in the database.
         * @param name of the building stored in the database
         * @param the date and time in which the building has been booked
         * @param if the building has a foodcourt or not.
         * @param the amount of available time slots in the building
         * @param the image id that will be shown of the building
         */

        public Users() {

        }
        public Users(long id, String name, long dateTime, boolean hasFoodCourt, double availableTimeSlots, long imageID){
            this.id = id;
            this.name = name;
            this.dateTime = dateTime;
            this.hasFoodCourt = hasFoodCourt;
            this.availableTimeSlots = availableTimeSlots;
            this.imageID = imageID;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getDateTime() {
            return dateTime;
        }

        public void setDateTime(double dateTime) {
            this.dateTime = dateTime;
        }

        public boolean isHasFoodCourt() {
            return hasFoodCourt;
        }

        public void setHasFoodCourt(boolean hasFoodCourt) {
            this.hasFoodCourt = hasFoodCourt;
        }

        public double getAvailableTimeSlots() {
            return availableTimeSlots;
        }

        public void setAvailableTimeSlots(double availableTimeSlots) {
            this.availableTimeSlots = availableTimeSlots;
        }

        public long getImageID() {
            return imageID;
        }

        public void setImageID(long imageID) {
            this.imageID = imageID;
        }


    }
}
