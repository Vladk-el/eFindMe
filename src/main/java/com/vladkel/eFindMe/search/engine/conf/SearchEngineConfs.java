package com.vladkel.eFindMe.search.engine.conf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vladkel.eFindMe.bing.search.BingSearch;
import com.vladkel.eFindMe.search.engine.model.User;
import com.vladkel.eFindMe.search.engine.xml.get.GetUsers;

public class SearchEngineConfs {

	/**
	 * For the moment, simple static class for users conf
	 */
		
	private Map<String, User> users;
	
	private BingSearch searcher;
	
	private List<String> availableApis;
	
	
	public SearchEngineConfs(){
		super();
		users = new HashMap<String, User>();
		availableApis = new ArrayList<String>();
		loadUsers();
	}
	
	
	/**
	 * Methods
	 */
	
	private void loadUsers(){
		users = new GetUsers().getUsers();
	}

	
	/**
	 * Getters and setters
	 */

	public Map<String, User> getUsers() {
		return users;
	}

	public void setUsers(Map<String, User> users) {
		this.users = users;
	}

	public BingSearch getSearcher() {
		return searcher;
	}

	public void setSearcher(BingSearch searcher) {
		this.searcher = searcher;
	}

	public List<String> getAvailableApis() {
		return availableApis;
	}

	public void setAvailableApis(List<String> availableApis) {
		this.availableApis = availableApis;
	}
}
