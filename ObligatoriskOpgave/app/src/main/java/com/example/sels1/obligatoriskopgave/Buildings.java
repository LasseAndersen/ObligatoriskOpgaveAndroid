package com.example.sels1.obligatoriskopgave;

public class Buildings {

    private String name;
    private String address;
    private int id;
    private int cityid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
    }

    public Buildings(String name, String address, int id, int cityid) {
        this.name = name;
        this.address = address;
        this.id = id;
        this.cityid = cityid;
    }

    public String ToString(){
        return "Denne bygning: " + name + "Adresse: "  + address + "Id for bygningen: " + id;
    }
}
