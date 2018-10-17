package com.amit.cruddemobackend.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amit.cruddemobackend.DAO.ForumDAO;
import com.amit.cruddemobackend.model.Forum;

@Transactional
@Repository("forumDAO")
public class ForumDAOImpl implements ForumDAO{
	
	@Autowired
	SessionFactory sessionFactory;
	public boolean createForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().save(forum);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean updateForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean deleteForum(int id) {
		try {
			if(getForumById(id)!= null) {
				sessionFactory.getCurrentSession().delete(getForumById(id)!= null);
				return true;
			}else {
				System.out.println("There is no forum with is id");
				return false;
				}
		}
			catch(Exception ex) {
				ex.printStackTrace();
				return false;
			}
		
	}

	public Forum getForumById(int id) {
		try {
			return sessionFactory.getCurrentSession().get(Forum.class,id);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public List<Forum> getForumList() {
		try {
			return sessionFactory.getCurrentSession().createQuery("from forum").list();
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
