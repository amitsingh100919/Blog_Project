package com.amit.cruddemobackend.DAOImpl;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.amit.cruddemobackend.DAO.NotificationDAO;
import com.amit.cruddemobackend.model.Notification;
@Repository("notificationDAO")
@Transactional
public class NotificationDAOImpl implements NotificationDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public boolean addNotification(Notification notification) {
		try {
			sessionFactory.getCurrentSession().save(notification);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		
	}

	public boolean updateNotification(int id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Notification notification = session.get(Notification.class,id);
			notification.setViewed(true);
			session.update(notification);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean deleteNotification(Notification notification) {
		try {
			sessionFactory.getCurrentSession().delete(notification);
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}

	public Notification getNotification(int id) {
		try {
			return sessionFactory.getCurrentSession().get(Notification.class,id);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public List<Notification> listNotificationNotViewed(String email) {
	try {
		return sessionFactory.getCurrentSession().createQuery("from Notification where email=:email and viewed=0").setParameter("email", email).list();
	}
	catch(Exception ex) {
		ex.printStackTrace();
		return null;
	}
	}

}
