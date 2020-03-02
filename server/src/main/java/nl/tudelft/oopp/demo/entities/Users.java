package nl.tudelft.oopp.demo.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Users")
public class Users {

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
    public Building(long id, String name, long dateTime, boolean hasFoodCourt, double availableTimeSlots, long imageID){
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
