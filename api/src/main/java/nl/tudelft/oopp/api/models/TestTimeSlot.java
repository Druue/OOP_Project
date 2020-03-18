package nl.tudelft.oopp.api.models;

public class TestTimeSlot {
    int index;
    boolean isAvailable;

    public TestTimeSlot(int index, boolean isAvailable) {
        this.index = index;
        this.isAvailable = isAvailable;
    }

    public int getIndex() {
        return index;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
