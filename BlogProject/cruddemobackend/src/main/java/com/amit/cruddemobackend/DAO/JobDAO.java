package com.amit.cruddemobackend.DAO;

import java.util.List;

import com.amit.cruddemobackend.model.Job;

public interface JobDAO {
	
	//To save,update and delete
	public boolean createJob(Job job);
	public boolean updateJob(Job job);
	public boolean deleteJob(int id);
	
	//To get job and job list
	public Job getJobById(int id);
	public List<Job> getJobList();

}
