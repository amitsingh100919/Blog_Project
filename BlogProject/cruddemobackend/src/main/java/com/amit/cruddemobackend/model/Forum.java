package com.amit.cruddemobackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Forum_table")
@SequenceGenerator(name="jobidseq",sequenceName="job_id_seq")
public class Forum {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="jobidseq")
	private int jobId;
	private String title;
	private String forumcontent;
	private String postedBy;
	private String postedOn;
	private String userName;
	private String status;
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getForumContent() {
		return forumcontent;
	}
	public void setForumContent(String forumcontent) {
		this.forumcontent = forumcontent;
	}
	public String getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
	public String getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(String postedOn) {
		this.postedOn = postedOn;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
