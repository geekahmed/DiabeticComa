package tech.geekahmed.diabeticcoma.Models;

import android.location.Location;

import com.google.firebase.Timestamp;

public class History {
    private String description;
    private Timestamp timestamp;
    private Location location;

    public History(String description, Timestamp timestamp, Location location) {
        this.description = description;
        this.timestamp = timestamp;
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "History{" +
                "description='" + description + '\'' +
                ", timestamp=" + timestamp +
                ", location=" + location +
                '}';
    }
}
