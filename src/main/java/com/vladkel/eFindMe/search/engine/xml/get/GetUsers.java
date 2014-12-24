package com.vladkel.eFindMe.search.engine.xml.get;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vladkel.eFindMe.search.engine.model.User;

public class GetUsers {
	
	private static final Logger log = LoggerFactory.getLogger(GetUsers.class);

	private static final String location = "data/xml/users";
	
	private Map<String, User> users;
	

	public GetUsers(){
		super();
	}
	
	
	private void loadUsers(){
		
		users = new HashMap<String, User>();
		
		File directory = new File(getLocation());
		File[] fileslist = directory.listFiles();
		
		for(File file : fileslist){
			if(file.isDirectory()){
				if(!file.getName().equalsIgnoreCase("toto")){
					loadUser(file.getName());
					log.info("User " + file.getName() + " loaded.");
				}
			}
		}
	}
	
	/**
	 * @param fullName ==> userDir.getName()
	 */
	private void loadUser(String fullName){
		User user = new GetUser(fullName).getUser();
		users.put(user.getId(), user);
	}
	
	
	public static String getLocation() {
		return location;
	}
	
	public List<User> getUsersAsList() {
		return new ArrayList<User>(getUsers().values());
	}
	
	public Map<String, User> getUsers() {
		if(users == null){
			loadUsers();
		}
		return users;
	}

	public void setUsers(Map<String, User> users) {
		this.users = users;
	}
}
