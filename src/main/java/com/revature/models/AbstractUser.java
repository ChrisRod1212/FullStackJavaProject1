package com.revature.models;

import java.util.Objects;

/**
 * This AbstractUser class defines a minimum functionality for
 * interacting with users in the ERS application.
 *
 * All users in this application must at least have:
 * <ul>
 *     <li>ID</li>
 *     <li>Username</li>
 *     <li>Password</li>
 *     <li>Role</li>
 * </ul>
 *
 * Additional fields can be added to the concrete {@link com.revature.models.User} class.
 *
 * @author Center of Excellence
 */

public class AbstractUser {

    private int userId;
    private String username;
    private String password;
    private int role;
    private String firstname;
    private String lastname;
    private String email;
    private long phone;
    private String state;
    private int zip;
    private String address;


    public AbstractUser() {
        super();
    }

    public AbstractUser(int userId, String username, String password, int role, String firstname, String lastname, String email, long phone, String state, int zip, String address) {
        super();
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.state = state;
        this.zip = zip;
        this.address = address;
    }

    public AbstractUser(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public AbstractUser(String username, String password, String firstname, String lastname, String email, long phone, String state, int zip, String address, int role) {
        super();
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.state = state;
        this.zip = zip;
        this.address = address;
        this.role = role;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getuserId() {

        return userId;
    }

    public void setId(int userId) {

        this.userId = userId;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {

        return role;
    }

    public void setRole(int role) {

        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractUser that = (AbstractUser) o;
        return userId == that.userId && Objects.equals(username, that.username) && Objects.equals(password, that.password) && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, role, firstname, lastname, email, phone, state, zip, address);
    }

    @Override
    public String toString() {
        return "AbstractUser{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role + '\'' +
                ", firstname=" + firstname + '\'' +
                ", lastname=" + lastname + '\'' +
                ", email=" + email + '\'' +
                ", phone=" + phone + '\'' +
                ", state=" + state + '\'' +
                ", zip=" + zip + '\'' +
                ", address=" + address +
                '}';
    }

}
