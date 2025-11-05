package edu.secourse.patientportal.models;

public class User {
    private int sessionID;
    private String username;
    private String address;
    private String dob;

    // Constructor
    public User(int sessionID, String username, String address, String dob) {
        this.sessionID = sessionID;
        this.username = username;
        this.address = address;
        this.dob = dob;
    }

    // Getter and Setter for sessionID
    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    // Getter and Setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and Setter for address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Getter and Setter for dob
    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
