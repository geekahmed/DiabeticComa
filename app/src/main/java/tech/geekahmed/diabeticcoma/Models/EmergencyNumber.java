package tech.geekahmed.diabeticcoma.Models;

public class EmergencyNumber {

    private String number, description, id;

    public EmergencyNumber(String number, String description, String id) {
        this.number = number;
        this.description = description;
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "EmergencyNumber{" +
                "number='" + number + '\'' +
                ", description='" + description + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
