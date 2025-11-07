package edu.secourse.patientportal.models;

public class User {
    private int account_number;
    private String username;
    private String email_address;
    private String role;
    private String name;
    private String password;

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
    // Constructor
    public User(int account_number, String username, String email_address, String role, String name, String password) {
        this.account_number = account_number;
        this.username = username;
        this.email_address = email_address;
        this.role = role;
        this.name = name;
        this.password = password;
    }

    // Getter and Setter for account_number
    public int getAccountNumber() {
        return account_number;
    }

    public void setAccountNumber(int account_number) {
        this.account_number = account_number;
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
    // Getter and Setter for email_address
    public String getEmailAddress() {
        return email_address;
    }

    public void setEmailAddress(String email_address) {
        this.email_address = email_address;
    }

    // Getter and Setter for role
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


