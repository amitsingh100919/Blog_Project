package com.amit.cruddemobackend.test;



import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.amit.cruddemobackend.DAO.JobDAO;
import com.amit.cruddemobackend.model.Job;



public class JobDAOTest {
	private static AnnotationConfigApplicationContext context=null;
	private static JobDAO jobDAO=null;
	private static Job job=null;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context= new AnnotationConfigApplicationContext();
		context.scan("com.amit.cruddemobackend");
		context.refresh();
		jobDAO=(JobDAO)context.getBean("jobDAO");
		job=new Job();
	}

	@Test
	public void testCreateJob() {
		job.setJobDescription("aaaaaaa");
		job.setCompany("ibm");
		job.setSalary(10000);
		job.setLocation("lucknow");
		job.setDesignation("CEO");
		assertTrue("Job record not inserted",jobDAO.createJob(job));
	}

	@Ignore
	@Test
	public void testUpdateJob() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testDeleteJob() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetJobById() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetJobList() {
		fail("Not yet implemented");
	}

}
