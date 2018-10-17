package com.amit.cruddemobackend.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amit.cruddemobackend.DAO.UserDAO;
import com.amit.cruddemobackend.model.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {
	@Autowired
    SessionFactory sessionFactory;
	public boolean saveUser(User user) {
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	public boolean updateUser(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	public boolean deleteUser(String email) {
		try {
			if(getUserByEmail(email)!=null){
				sessionFactory.getCurrentSession().delete(getUserByEmail(email));
				return true;
			}else {
				System.out.println("There is no user with email address");
				return false;
			}
			
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	public User getUserByEmail(String email) {
		try {
			return sessionFactory.getCurrentSession().get(User.class,email);
			
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	public List<User> getAllActiveUser() {
		try {
			return sessionFactory.getCurrentSession().createQuery("from User where isActive=true").list();
			
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	public User validate(String email, String password) {
		try {
			User usr=(User)sessionFactory.getCurrentSession().createQuery("from User where email=:email and password=:password")
			.setParameter("email", email)
			.setParameter("password", password).getSingleResult();
			
			return usr;
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
