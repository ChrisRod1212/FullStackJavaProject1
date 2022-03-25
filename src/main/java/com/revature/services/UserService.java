package com.revature.services;

import java.util.Optional;

import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.repositories.UserDAOImpl;

/**
 * The UserService should handle the processing and retrieval of Users for the ERS application.
 *
 * {@code getByUsername} is the only method required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create User</li>
 *     <li>Update User Information</li>
 *     <li>Get Users by ID</li>
 *     <li>Get Users by Email</li>
 *     <li>Get All Users</li>
 * </ul>
 */
public class UserService {

	private UserDAO userDAO;

	public UserService(UserDAO userDAO){

		this.userDAO = userDAO;
	}

	public UserService(){

		this.userDAO = new UserDAOImpl();
	}
	/**
	 *     Should retrieve a User with the corresponding username or an empty optional if there is no match.
     */
	public User getByUserId(int userId) {
		User user = this.userDAO.getByUserId(userId);
		return user;
	}

	public User getByUsername(String username) {
		User user = this.userDAO.getByUsername(username);
		return user;
	}



	public User validateCredentials(String username, String password){
		User user = this.userDAO.getByUsername(username);

		if(user == null) {
			return null;
		}
		if(!password.equals(user.getPassword()))
			return null;

		return user;
	}

	public Boolean createUser(User user){
		User userFromDb = userDAO.getByUsername(user.getUsername());

		if(userFromDb != null){
			return Boolean.FALSE;
		}
		this.userDAO.createUser(user);
		return Boolean.TRUE;
	}
}
