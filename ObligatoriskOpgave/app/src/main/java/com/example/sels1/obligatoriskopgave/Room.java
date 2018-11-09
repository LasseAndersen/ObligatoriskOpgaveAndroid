package com.example.sels1.obligatoriskopgave;

public class Room {
    private String name;
    private String description;
    private String remarks;
    private int id;
    private int capacity;
    private int buildingId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public Room(String name, String description, String remarks, int id, int capacity, int buildingId) {
        this.name = name;
        this.description = description;
        this.remarks = remarks;
        this.id = id;
        this.capacity = capacity;
        this.buildingId = buildingId;
    }

    public String ToString(){
        return "Rum navn: " + name + " rum id: " + id + " i bygning: " + buildingId;
    }
}
