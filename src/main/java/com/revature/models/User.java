package com.revature.models;

/**
 * This concrete User class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>First Name</li>
 *     <li>Last Name</li>
 *     <li>Email</li>java larger
 *     <li>Phone Number</li>
 *     <li>Address</li>
 * </ul>
 *
 */
public class User extends AbstractUser {

    public User() {
        super();
    }

    /**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractUser} class.
     * If other fields are needed, please create additional constructors.
     */
    public User(int userId, String username, String password, int role, String firstname, String lastname, String email, long phone, String state, int zip, String address) {
        super(userId, username, password, role, firstname, lastname, email, phone, state, zip, address);
    }

    public User(String username, String password){
        super(username, password);
    }

    public User(String username, String password, String firstname, String lastname, String email, long phone, String state, int zip, String address, int role) {
        super(username,password,firstname,lastname,email,phone,state,zip,address,role);
    }
}
