package com.amit.cruddemobackend.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amit.cruddemobackend.DAO.BlogDAO;
import com.amit.cruddemobackend.model.Blog;

@Transactional
@Repository("blogDAO")
public class BlogDAOImpl implements BlogDAO {
	
	@Autowired
	SessionFactory sessionFactory ;
	
	public boolean createBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().save(blog);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		
	}

	public boolean updateBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean deleteBlog(Blog blog) {
		try {
			
				sessionFactory.getCurrentSession().delete(blog);
				return true;
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public Blog getBlogById(int id) {
		try {
			return sessionFactory.getCurrentSession().get(Blog.class,id);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public List<Blog> approvedBlogList() {
		
		try {
			return sessionFactory.getCurrentSession().createQuery("from Blog where status='A'").list();
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public List<Blog> notApprovedBlogList() {
		try {
			return sessionFactory.getCurrentSession().createQuery("from Blog where status='NA'").list();
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public boolean incLikes(int blogId) {
		try {
			Blog blog = this.getBlogById(blogId);
			blog.setLiked(blog.getLiked()+1);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean disLikes(int blogId) {
		try {
			Blog blog = this.getBlogById(blogId);
			blog.setDisliked(blog.getDisliked()+1);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
