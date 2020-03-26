package nl.tudelft.oopp.api.models;

/**
 * A generic class representing information to be sent to the server
 * when adding a new room or a new bike to a building. The class has:
 *      1) buildingID - representing the id of the building to add the new {@link Reservable} to.
 *      2) reservable - the new room or bike to add to the building with id = buildingID
 * The class uses generic type parameter T to avoid conversions on the server from API class
 *      Reservable to Server class Reservable.
 */
public class NewReservableInfo<T> {

    private Long buildingID;

    private T reservableToAdd;

    public NewReservableInfo() {}

    public NewReservableInfo(Long buildingID, T reservableToAdd) {
        this.buildingID = buildingID;
        this.reservableToAdd = reservableToAdd;
    }

    public Long getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(Long buildingID) {
        this.buildingID = buildingID;
    }

    public T getReservableToAdd() {
        return reservableToAdd;
    }

    public void setReservableToAdd(T reservableToAdd) {
        this.reservableToAdd = reservableToAdd;
    }
}
