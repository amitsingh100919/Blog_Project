package com.amit.cruddemobackend.DAO;

import java.util.List;

import com.amit.cruddemobackend.model.Notification;

public interface NotificationDAO {
	
	public boolean addNotification(Notification notification);
	public boolean updateNotification(int id);
	public boolean deleteNotification(Notification notification);
	
	public Notification getNotification(int id);
	public List<Notification> listNotificationNotViewed(String email);

}
