package com.amit.cruddemobackend.DAO;

import java.util.List;

import com.amit.cruddemobackend.model.Blog;

public interface BlogDAO {
	
	// To save ,update and delete blog
	public boolean createBlog(Blog blog);
	public boolean updateBlog(Blog blog);
	public boolean deleteBlog(Blog blog);
	
	// To select blog and get list of blog
	public Blog getBlogById(int id);
	public List<Blog> approvedBlogList();
	public List<Blog> notApprovedBlogList();
	
	public boolean incLikes(int blogId);
	public boolean disLikes(int blogId);
}
