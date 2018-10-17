package com.blogmiddleware.RestController;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amit.cruddemobackend.DAO.UserDAO;
import com.amit.cruddemobackend.model.User;

@RestController
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	
	@PostMapping("/sign-in")
	public ResponseEntity<?> validateUser(@RequestBody User user,HttpSession httpSession)
	{
	String email = user.getEmail();
	String password = user.getPassword();
	User vUser = userDAO.validate(email,password);
	if(vUser==null) {
		return new ResponseEntity<User>(vUser,HttpStatus.NOT_FOUND);
	}else {
		vUser.setOnline(true);
		userDAO.updateUser(vUser);
		httpSession.setAttribute("email", vUser.getEmail() );
		System.out.println("Session Id" + httpSession.getId());
		System.out.println("Session Attribute" + httpSession.getAttribute("email"));
		System.out.println("Created On" + httpSession.getCreationTime());
		return new ResponseEntity<User>(vUser,HttpStatus.OK);
		}	
	}
	
	
	@PostMapping("/sign-out")
	public ResponseEntity<?> logout(HttpSession httpSession){
		String email = (String)httpSession.getAttribute("email");
		if(email==null) {
			return new ResponseEntity<String>("{\"message\":\"Unauthorized access please sign-in\"}",HttpStatus.UNAUTHORIZED);
		}else
		{
			User user = userDAO.getUserByEmail(email);
			user.setOnline(false);
			userDAO.updateUser(user);
			httpSession.removeAttribute("email");
			httpSession.invalidate();
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
	}
	
	
	
	@PostMapping("/registerUser")
	public ResponseEntity<?> registerUser(@RequestBody User user)
	{
		user.setRole('U');
		if(userDAO.saveUser(user))
			return new ResponseEntity<String>("{\"message\":\"Registered user successfully\"}",HttpStatus.OK);
		else
			return new ResponseEntity<String>("User not added",HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping("/updateUser/{email}")
	public ResponseEntity<?> updateUser(@PathVariable("email") String email,@RequestBody User user)
	{
		System.out.println("Update user"+email+".com");
		System.out.println(email+".com");
		User uUser = userDAO.getUserByEmail(email+".com");
		if(uUser==null) {
			return new ResponseEntity<String>("Can not update user",HttpStatus.NOT_FOUND);
		}
		uUser.setEmail(user.getEmail());
		uUser.setPassword(user.getPassword());
		uUser.setFirstname(user.getFirstname());
		uUser.setLastname(user.getLastname());
		uUser.setMobile(user.getMobile());
		uUser.setRole(user.getRole());
		userDAO.updateUser(uUser);
		return new ResponseEntity<String>("User updated successfully",HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteUser/{email}")
	public ResponseEntity<?> deleteUser(@PathVariable("email") String email)
	{
		System.out.println("Delete user"+ email +".com");
		User user = userDAO.getUserByEmail(email + ".com");
				if(user==null) {
					System.out.println("No user with email" + email + "found to delete");
					return new ResponseEntity<String>("No user found to delete",HttpStatus.NOT_FOUND);
				}
				else {
					userDAO.deleteUser(email);
					return new ResponseEntity<String>("User with email" + email + "deleted successfully",HttpStatus.OK);
				}
	}
	
	
	@GetMapping("/getUser/{email}")
	public ResponseEntity<?> getUser(@PathVariable("email") String email)
	{
		User gUser = userDAO.getUserByEmail(email +".com");
		if(gUser==null) {
			System.out.println("No user found" +email);
			return new ResponseEntity<User>(gUser,HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<User>(gUser,HttpStatus.OK);
		}
	}
	
	
	@GetMapping("/getAllUser")
	public ResponseEntity<?> getAllUser()
	{
	List<User> userList= userDAO.getAllActiveUser();
	if(userList==null) {
		return new ResponseEntity<String>("No user added",HttpStatus.NOT_FOUND);
	}
	else {
		return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
	}
	}

}
