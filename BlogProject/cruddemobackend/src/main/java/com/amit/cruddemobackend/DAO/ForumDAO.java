package com.amit.cruddemobackend.DAO;

import java.util.List;

import com.amit.cruddemobackend.model.Forum;

public interface ForumDAO {
	
	//To save,update and delete.
	public boolean createForum(Forum forum);
	public boolean updateForum(Forum forum);
	public boolean deleteForum(int id);
	
	//To get forum and forum list
	public Forum getForumById(int id);
	public List<Forum> getForumList();

}
