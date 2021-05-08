package tech.geekahmed.diabeticcoma.Models;

import android.location.Location;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Location currentLocation;
    private String name, email, phoneNumber, password, raspi;
    private List<History> history;
    private List<EmergencyNumber> emergencyNumbers;


    public User(Location currentLocation, String name, String email, String raspi, String phoneNumber, String password, List<History> history, List<EmergencyNumber> emergencyNumbers) {
        this.currentLocation = currentLocation;
        this.name = name;
        this.email = email;
        this.raspi = raspi;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.history = history;
        this.emergencyNumbers = emergencyNumbers;
    }
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }
    public User(){

    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRaspi() {
        return raspi;
    }

    public void setRaspi(String raspi) {
        this.raspi = raspi;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

    public List<EmergencyNumber> getEmergencyNumbers() {
        return emergencyNumbers;
    }

    public void setEmergencyNumbers(ArrayList<EmergencyNumber> emergencyNumbers) {
        this.emergencyNumbers = emergencyNumbers;
    }

    @Override
    public String toString() {
        return "User{" +
                "currentLocation=" + currentLocation +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", raspi='" + raspi + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", history=" + history +
                ", emergencyNumbers=" + emergencyNumbers +
                '}';
    }
}
