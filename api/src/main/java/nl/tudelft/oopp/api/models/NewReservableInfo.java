package nl.tudelft.oopp.api.models;

/**
 * A class representing information to be sent to the server
 * when adding a new room or a new bike to a building. The class has:
 *      1) buildingID - representing the id of the building to add the new {@link Reservable} to.
 *      2) reservable - the new room or bike to add to the building with id = buildingID
 */
public class NewReservableInfo {

    private Long buildingID;

    private Reservable reservableToAdd;

    public NewReservableInfo() {}

    public NewReservableInfo(Long buildingID, Reservable reservableToAdd) {
        this.buildingID = buildingID;
        this.reservableToAdd = reservableToAdd;
    }

    public Long getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(Long buildingID) {
        this.buildingID = buildingID;
    }

    public Reservable getReservableToAdd() {
        return reservableToAdd;
    }

    public void setReservableToAdd(Reservable reservableToAdd) {
        this.reservableToAdd = reservableToAdd;
    }
}
