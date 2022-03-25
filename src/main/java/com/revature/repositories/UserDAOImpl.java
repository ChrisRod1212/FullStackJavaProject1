package com.revature.repositories;

import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     */
    @Override
    public User getByUserId(int userId){
        User user = null;

        try (Connection conn = ConnectionFactory.getConnection()){
            //retrieve active connection from database
            String sql = "SELECT * FROM ers_users WHERE ers_users_id = ?;";

            //prepare sql statement
            PreparedStatement ps = conn.prepareStatement(sql);

            //add userId into question mark in sql statement
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                user = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getLong(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getString(11));
            }
//                  public AbstractUser(int userId,     String username,            String password,            Role role,                  String firstname,           String lastname,                String email,           String phone,               String state,               int zip,                String address)
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return user;
    }

    /**
     * <ul>
     *     <li>Should Insert a new User record into the DB with the provided information.</li>
     *     <li>Should throw an exception if the creation is unsuccessful.</li>
     *     <li>Should return a User object with an updated ID.</li>
     * </ul>
     *
     * Note: The userToBeRegistered will have an id=0, and username and password will not be null.
     * Additional fields may be null.
     */
    @Override
    public void createUser(User user) {

        try(Connection conn = ConnectionFactory.getConnection()){
            String sql =
                    "insert into ers_users (ERS_USERNAME , ERS_PASSWORD , USER_FIRST_NAME , USER_LAST_NAME , USER_EMAIL , USER_PHONE , USER_STATE , USER_ZIP, USER_ADDRESS, USER_ROLE_ID) " +
                            "values (?,?,?,?,?,?,?,?,?,?);";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstname());
            ps.setString(4, user.getLastname());
            ps.setString(5, user.getEmail());
            ps.setLong(6, user.getPhone());
            ps.setString(7, user.getState());
            ps.setInt(8, user.getZip());
            ps.setString(9, user.getAddress());
            ps.setInt(10, user.getRole());

            ps.executeUpdate();

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    @Override
    public User getByUsername(String username){

        User user = null;
        try (Connection conn = ConnectionFactory.getConnection()){
            //retrieve active connection from database
            String sql = "SELECT * FROM ers_users WHERE ers_username = ?;";

            //prepare sql statement
            PreparedStatement ps = conn.prepareStatement(sql);

            //add username into question mark in sql statement
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(10),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getLong(11),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9));
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return user;
    }

    @Override
    public User validateCredentials(String username, String password) {
        User user = null;

        try (Connection conn = ConnectionFactory.getConnection()){
            //retrieve active connection from database
            String sql = "SELECT * FROM ers_users WHERE ers_username = ? AND ers_password = ?;";

            //prepare sql statement
            PreparedStatement ps = conn.prepareStatement(sql);

            //add userId into question mark in sql statement
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                user = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getLong(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getString(11));
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return user;
    }
}
