package com.vladkel.eFindMe.search.engine;

import com.vladkel.eFindMe.search.engine.conf.SearchEngineConfs;
import com.vladkel.eFindMe.search.engine.model.User;

public class SearchEngine {

	private SearchEngineConfs confs;
	
	
	/**
	 * Methods
	 */
	
	public void search(String id){
		/**
		 * Search a people and update his graph
		 * check all url and call detailledSearch
		 */
	}
	
	private void detailledSearch(User user){
		/**
		 * Load html page one by one and check if it contains given urls
		 */
	}
	
	public void showGraph(String id){
		/**
		 * Show the people's graph
		 */
	}
	
	
	/**
	 * Getters and setters
	 */

	public SearchEngineConfs getConfs() {
		return confs;
	}

	public void setConfs(SearchEngineConfs confs) {
		this.confs = confs;
	}
	
}
