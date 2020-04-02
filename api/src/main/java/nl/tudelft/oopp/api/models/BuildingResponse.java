package nl.tudelft.oopp.api.models;

import java.util.List;

public class BuildingResponse {

    private List<Building> buildingList;

    public BuildingResponse(List<Building> buildingList) {
        this.buildingList = buildingList;
    }

    public BuildingResponse(){}

    public List<Building> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List<Building> buildingList) {
        this.buildingList = buildingList;
    }
}
