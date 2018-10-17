package com.amit.cruddemobackend.DAO;

import java.util.List;

import com.amit.cruddemobackend.model.User;

public interface UserDAO {
	//To save ,update and delete
	public boolean saveUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(String email);
	
	//To select user
	public User getUserByEmail(String email);
	public List<User> getAllActiveUser();
	
	// To Authenticate user
	public User validate(String email,String password);

}
