package edu.secourse.patientportal.services;

public class User {
    private int sessionID;
    private String username;
    private String address;
    private String dob;

    /**
     * @return the sessionID
     */
    public int getSessionID() {
        return sessionID;
    }

    /**
     * Sets sessionID
     */
    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the date of birth
     */
    public String getDob() {
        return dob;
    }

    /**
     * Sets DOB
     */
    public void setDob(String dob) {
        this.dob = dob;
    }
}
