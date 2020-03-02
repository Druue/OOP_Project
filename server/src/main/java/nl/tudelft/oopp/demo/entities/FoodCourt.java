package nl.tudelft.oopp.demo.entities;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "FoodCourt")
public class FoodCourt {

    @Column(name = "FoodList")
    private List<String> foodlist;

    @Column(name = "BuildingNumber")
    private long BuildingNumber;

    public FoodCourt(List foodlist, long BuildingNumber) {
        this.foodlist = foodlist;
        this.BuildingNumber = BuildingNumber;
    }

    public List<String> getFoodlist() {
        return this.foodlist;
    }

    public long getBuildingNumber() {
        return this.BuildingNumber;
    }

}
