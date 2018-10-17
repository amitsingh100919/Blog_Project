package com.blogmiddleware.RestController;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.amit.cruddemobackend.DAO.NotificationDAO;
import com.amit.cruddemobackend.model.Notification;

@RestController
public class NotificationController {
	
	@Autowired
	NotificationDAO notificationDAO;
	
	@GetMapping("/getNotification")
	public ResponseEntity<?> getNotification(HttpSession httpSession)
	{
		String email = (String)httpSession.getAttribute("email");
		if(email==null) {
			return new ResponseEntity<String>("{\"message\":\"Unauthorized access please sign-in\"}",HttpStatus.UNAUTHORIZED);
		}
		List<Notification> notifications = notificationDAO.listNotificationNotViewed(email);
		return new ResponseEntity<List<Notification>>(notifications,HttpStatus.OK);
	}

	@GetMapping("/getNotify/{id}")
	public ResponseEntity<?> getNotify(@PathVariable("id") int id,HttpSession httpSession)
	{
		String email = (String)httpSession.getAttribute("email");
		if(email==null) {
			return new ResponseEntity<String>("{\"message\":\"Unauthorized access please sign-in\"}",HttpStatus.UNAUTHORIZED);
		}
		Notification notification = notificationDAO.getNotification(id);
		return new ResponseEntity<Notification>(notification,HttpStatus.OK);
	}
}
