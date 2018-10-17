package com.amit.cruddemobackend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="Blog_table")
public class Blog {
	@Id
	@GeneratedValue
	private int blogId;
	private String blogTitle;
	@Lob
	private String blogContent;
	
	
	private String postedBy;
	
	private String postedOn;
	
	private String status;
	private int liked;
	private int disliked;

	
	public Blog() {
		
		this.postedOn = new Date().toString();
	}
	public int getDisliked() {
		return disliked;
	}
	public void setDisliked(int disliked) {
		this.disliked = disliked;
	}
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getBlogContent() {
		return blogContent;
	}
	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
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
		this.postedOn = new Date().toString();
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getLiked() {
		return liked;
	}
	public void setLiked(int liked) {
		this.liked = liked;
	}
	
}
