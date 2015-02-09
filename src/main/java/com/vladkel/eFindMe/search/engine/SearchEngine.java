package com.vladkel.eFindMe.search.engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vladkel.eFindMe.bing.results.Result;
import com.vladkel.eFindMe.bing.search.BingSearch;
import com.vladkel.eFindMe.search.engine.conf.SearchEngineConfs;
import com.vladkel.eFindMe.search.engine.model.Match;
import com.vladkel.eFindMe.search.engine.model.Trust;
import com.vladkel.eFindMe.search.engine.model.Url;
import com.vladkel.eFindMe.search.engine.model.User;
import com.vladkel.eFindMe.utils.ws.http.GetHelper;

public class SearchEngine {
	
	private static final Logger log = LoggerFactory.getLogger(SearchEngine.class);

	private SearchEngineConfs confs;
	
	public User currentUser;
		
	private static SearchEngine instance = null;

	public static SearchEngine getInstance() {
			
		if(instance == null) {
			instance = new SearchEngine();
	    }
			
		return instance;
	}	
	
	public SearchEngine(){
		super();
		confs = new SearchEngineConfs();
	}
	
	/**
	 * Methods
	 */

	public void updateConf(){
		confs.loadUsers();
	}
	
	public void search(String id){
		/**
		 * Search a people and update his graph
		 * check all url and call detailledSearch
		 */
		
		User user = confs.getUsers().get(id);
		
		BingSearch bing = new BingSearch(user.getName() + " " + user.getFirstname());
		
		List<Result> results = bing.getResults();
		
		for(Result result : results){
			try{
				detailledSearch(user, result);
			}catch(Exception e){
				log.error("Exception on detailledSearch ", e);
			}
			
		}
		log.info("Search finished !!!");
	}
	
	private void detailledSearch(User user, Result result) throws Exception{
		/**
		 * Load html page one by one and check if it contains given urls
		 */
		
		String response = null;
		GetHelper helper = null;
		
		Url newUrl = new Url();
			newUrl.setId(result.getID());
			newUrl.setName(result.getTitle());
			newUrl.setDescription(result.getDescription());
			newUrl.setUrl(result.getUrl());
			
		if(result.getUrl().startsWith("http")){
			helper = new GetHelper(10000);
		}
		else if(result.getUrl().startsWith("https")){
			helper = new com.vladkel.eFindMe.utils.ws.https.GetHelper(10000);
		}
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-type", "text/html");
		
		response = helper.get(result.getUrl(), headers, false);
		
		if(response != null){
			boolean addToMap = false;
			
			for(Url url : user.getUrlsToLookFor()){
				if(response.contains(url.getUrl())){
					user.getMatches().add(new Match(
							url.getId(),
							newUrl.getId(),
							Trust.Trusted
							));
					addToMap = true;
				}
				else if(response.contains(user.getName() + " " + user.getFirstname()) ||
						response.contains(user.getFirstname() + " " + user.getName())){
					user.getMatches().add(new Match(
							url.getId(),
							newUrl.getId(),
							Trust.Unknown
							));
					addToMap = true;
				}
			}
			
			if(addToMap)
				user.getUrls().put(newUrl.getId(), newUrl);
		}
	}
	
	/**
	 * Maybe not here
	 */
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
