package tech.geekahmed.diabeticcoma.Models;

import android.location.Location;

import com.google.firebase.Timestamp;

public class History {
    private String description, location;
    private Timestamp timestamp;

    public History(String description, String location, Timestamp timestamp) {
        this.description = description;
        this.location = location;
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
