package edu.secourse.patientportal.models;

/**
 * Represents a user within the patient portal system.
 * Stores user account details such as username, email, name, role, and password.
 */
public class User {
    private int account_number;
    private String username;
    private String email_address;
    private String role;
    private String name;
    private String password;

    /**
     * Constructs a new User object with the specified parameters.
     *
     * @param account_number the unique account number associated with the user
     * @param username the username used for logging in
     * @param email_address the user's email address
     * @param role the user's assigned role (e.g., patient, admin, doctor)
     * @param name the full name of the user
     * @param password the user's account password
     */
    public User(int account_number, String username, String email_address, String role, String name, String password) {
        this.account_number = account_number;
        this.username = username;
        this.email_address = email_address;
        this.role = role;
        this.name = name;
        this.password = password;
    }

    /**
     * Returns the user's account number.
     *
     * @return the account number
     */
    public int getAccountNumber() {
        return account_number;
    }

    /**
     * Sets the user's account number.
     *
     * @param account_number the unique account number to assign
     */
    public void setAccountNumber(int account_number) {
        this.account_number = account_number;
    }

    /**
     * Returns the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for this user.
     *
     * @param username the username to assign
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the user's email address.
     *
     * @return the email address
     */
    public String getEmailAddress() {
        return email_address;
    }

    /**
     * Sets the user's email address.
     *
     * @param email_address the email address to assign
     */
    public void setEmailAddress(String email_address) {
        this.email_address = email_address;
    }

    /**
     * Returns the user's role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the user's role.
     *
     * @param role the role to assign
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Returns the user's full name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's full name.
     *
     * @param name the name to assign
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the user's password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password the password to assign
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
