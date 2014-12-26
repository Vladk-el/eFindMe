package com.vladkel.eFindMe.IHM.Controller;

import com.vladkel.eFindMe.graph.parsingxml.GraphXML;
import com.vladkel.eFindMe.search.engine.model.User;

public class CurrentUser {

	private User currentUser;
	
	private static CurrentUser instance = null;
	public static CurrentUser getInstance() {
		
		if(instance == null) {
			instance = new CurrentUser();
	    }
		
		return instance;
	}
	
	public CurrentUser(User user) {
		currentUser = user;
	}
	
	public CurrentUser() {
		
		currentUser = new User();
	}
	
	public User getUser()
	{
		return currentUser;
	}
	
	public void setCurrentUser(User user) {
		
		currentUser = user;
	}
}