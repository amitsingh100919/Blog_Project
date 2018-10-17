package com.blogmiddleware.RestController;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amit.cruddemobackend.DAO.JobDAO;
import com.amit.cruddemobackend.DAO.UserDAO;
import com.amit.cruddemobackend.model.Job;
import com.amit.cruddemobackend.model.User;

@RestController
public class JobController {
	
	@Autowired
	JobDAO jobDAO;
	@Autowired
	private UserDAO userDAO;
	@PostMapping("/createJob")
	public ResponseEntity<?> createJob(@RequestBody Job job,HttpSession httpSession)
	{
		String email =(String)httpSession.getAttribute("email");
		// CHECK FOR AUTHENTICATION
		if(email==null) {
			return new ResponseEntity<String>("{\"message\":\"Unauthorized access please sign-in\"}",HttpStatus.UNAUTHORIZED);
		}
		// CHECK FOR AUTHORIZATION[ROLE]
		User user = userDAO.getUserByEmail(email);
		if(user.getRole()!='A')
		{
			return new ResponseEntity<String>("{\"message\":\"Access denied you are not authorized to post a job\"}",HttpStatus.UNAUTHORIZED);
		}try {
			System.out.println("Create job");
			jobDAO.createJob(job);
			
		}catch(Exception ex){
			return new ResponseEntity<String>("{\"message\":\"Job not created \"}",HttpStatus.NOT_FOUND);
		}
		
		
			return new ResponseEntity<String>("{\"message\":\"Blog created successfully\"}",HttpStatus.OK);
		
	}
	
	
	@PostMapping("/updateJob/{jobId}")
	public ResponseEntity<?> updateJob(@PathVariable("jobId") int jobId,@RequestBody Job job)
	{
		System.out.println("Update job");
		Job ujob = jobDAO.getJobById(jobId);
		if(ujob==null) {
			return new ResponseEntity<String>("{\"message\":\"Job not updated\"}",HttpStatus.NOT_FOUND);
		}
		ujob.setJobDescription(job.getJobDescription());
		ujob.setCompany(job.getCompany());
		ujob.setSalary(job.getSalary());
		ujob.setDesignation(job.getDesigntaion());
		ujob.setLocation(job.getLocation());
		jobDAO.updateJob(ujob);
		return new ResponseEntity<String>("{\"message\":\"Job updated successfully\"}",HttpStatus.OK);
	}
	
	
	@PostMapping("/deleteJob/{jobId}")
	public ResponseEntity<?> deleteJob(@PathVariable("jobId") int jobId)
	{
		System.out.println("Delete job");
		Job djob = jobDAO.getJobById(jobId);
		if(djob==null)
		{
			return new ResponseEntity<String>("{\"message\":\"No job found to delete \" }",HttpStatus.NOT_FOUND);
		}
		else
		{
			jobDAO.deleteJob(jobId);
			return new ResponseEntity<String>("{\"message\":\"Job deleted successfully\"}",HttpStatus.OK);
		}
	}
	
	
	@GetMapping("/getJob/{jobId}")
	public ResponseEntity<?> getJob(@PathVariable("jobId") int jobId)
	{
		Job gjob = jobDAO.getJobById(jobId);
		if(gjob == null)
		{
			System.out.println("No job found with id" + jobId);
			return new ResponseEntity<Job>(gjob,HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<Job>(gjob,HttpStatus.OK);
		}
	}
	
	
	@GetMapping("/getAllJobs")
	public ResponseEntity<?> getAllJobs()
	{
		List<Job> jobList = jobDAO.getJobList();
		if(jobList == null)
		{
			return new ResponseEntity<String>("{\"message\":\"There is no job added\"}",HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<List<Job>>(jobList,HttpStatus.OK);
		}
	}

}
