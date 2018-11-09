package com.example.sels1.obligatoriskopgave;

import java.io.Serializable;

public class Reservation implements Serializable {

    private String fromTimeString;
    private String toTimeString;
    private String userId;
    private String purpose;
    private String id;

    public String getFromTimeString() {
        return fromTimeString;
    }

    public void setFromTimeString(String fromTimeString) {
        this.fromTimeString = fromTimeString;
    }

    public String getToTimeString() {
        return toTimeString;
    }

    public void setToTimeString(String toTimeString) {
        this.toTimeString = toTimeString;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String[] date;

    public Reservation(String fromTimeString, String toTimeString, String userId, String purpose, String id) {
        this.fromTimeString = fromTimeString;
        this.toTimeString = toTimeString;
        this.userId = userId;
        this.purpose = purpose;
        this.id = id;
    }


    @Override
    public String toString() {
        return "lokale id: " + id + " Reserveret fra: " + fromTimeString + " til " + toTimeString + " af bruger: " + userId;
    }
}
