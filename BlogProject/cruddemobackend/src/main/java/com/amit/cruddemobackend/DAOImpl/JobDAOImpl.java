package com.amit.cruddemobackend.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amit.cruddemobackend.DAO.JobDAO;
import com.amit.cruddemobackend.model.Job;

@Repository("jobDAO")
@Transactional
public class JobDAOImpl implements JobDAO{
	
	@Autowired
	SessionFactory sessionFactory;
	public boolean createJob(Job job) {
		try {
			sessionFactory.getCurrentSession().save(job);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean updateJob(Job job) {
		try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean deleteJob(int id) {
		try {
			if(getJobById(id)!= null) {
				sessionFactory.getCurrentSession().delete(getJobById(id)!= null);
				return true;
			}else {
				System.out.println("There is no job with this id");
				return false;
			}
		}
			catch(Exception ex) {
				ex.printStackTrace();
				return false;
			}
		
	}

	public Job getJobById(int id) {
		try {
			return sessionFactory.getCurrentSession().get(Job.class, id);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public List<Job> getJobList() {
		try {
			return sessionFactory.getCurrentSession().createQuery("from Job").list();
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
