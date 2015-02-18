package com.vladkel.eFindMe.search.engine;

import java.util.ArrayList;
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
		user.setUrls(new HashMap<String, Url>());
		user.setMatches(new ArrayList<Match>());

		for(Result result : results){
			try{
				detailledSearch(user, result);
			}catch(Exception e){
				log.error("Exception on detailledSearch ", e);
			}
			
		}
		log.info("Search finished !!!");
		log.info(user.getUrls().size() + " urls founded");
	}
	
	private void detailledSearch(User user, Result result) throws Exception{
		
		Url newUrl = new Url();
			newUrl.setId(result.getID());
			newUrl.setName(result.getTitle());
			newUrl.setDescription(result.getDescription());
			newUrl.setUrl(result.getUrl());
            newUrl.setTrust(Trust.Unknown);
			
		log.info("URL TESTED ==> " + newUrl);
			
		boolean addToMap = false;
		boolean needToContinue = true;
		
		String response = null;
		
		for(Url url : user.getUrlsToLookFor()){
			
			// Check Url
			response = newUrl.getUrl().toLowerCase();
			if(response.contains(url.getUrl().toLowerCase()) || response.equalsIgnoreCase(url.getUrl())){
				user.getMatches().add(new Match(
						url.getId(),
						newUrl.getId(),
						Trust.Trusted
						));
				addToMap = true;
				needToContinue = false;
			}
            else if(newUrl.getName().toLowerCase().contains(url.getUrl().toLowerCase())){
                user.getMatches().add(new Match(
                        url.getId(),
                        newUrl.getId(),
                        Trust.Trusted
                ));
                addToMap = true;
                needToContinue = false;
            }
            else if(newUrl.getDescription().toLowerCase().contains(url.getUrl().toLowerCase())){
                user.getMatches().add(new Match(
                        url.getId(),
                        newUrl.getId(),
                        Trust.Trusted
                ));
                addToMap = true;
                needToContinue = false;
            }
		}

        // Check Url
        response = newUrl.getUrl().toLowerCase();
        if(response.contains(user.getName().toLowerCase().replaceAll(" ", "") + "." + user.getFirstname().toLowerCase().replaceAll(" ", "") ) ||
                response.contains(user.getFirstname().toLowerCase().replaceAll(" ", "")  + "." + user.getName().toLowerCase().replaceAll(" ", "") )){
            addToMap = true;
        }

        if(needToContinue){
            // Check Name
            response = newUrl.getName().toLowerCase();
            if(response.contains(user.getEmail().toLowerCase())){
                addToMap = true;
            }
            else if(response.contains(user.getName().toLowerCase()) &&
                    response.contains(user.getFirstname().toLowerCase())){
                addToMap = true;
            }
        }

        if(needToContinue){
            // Check Description
            response = newUrl.getDescription().toLowerCase();
            if(response.contains(user.getEmail().toLowerCase())){
                addToMap = true;
            }
            else if(response.contains(user.getName().toLowerCase() + " " + user.getFirstname().toLowerCase()) ||
                    response.contains(user.getFirstname().toLowerCase() + " " + user.getName().toLowerCase())){
                addToMap = true;
            }
        }
		
		if(needToContinue)
			veryDetailledSearch(user, newUrl, addToMap);
		else if(addToMap){
			user.getUrls().put(newUrl.getId(), newUrl);
			log.info("MATCH \\O/ --> " + newUrl + " : " + newUrl.getUrl());
		}
		
	}
	
	private void veryDetailledSearch(User user, Url newUrl, boolean addToMap){
		
		String response = null;
		GetHelper helper = null;
		
		if(newUrl.getUrl().startsWith("http")){
			helper = new GetHelper(5000);
		}
		else if(newUrl.getUrl().startsWith("https")){
			helper = new com.vladkel.eFindMe.utils.ws.https.GetHelper(5000);
		}
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-type", "text/html");
		
		response = helper.get(newUrl.getUrl(), headers, false);
		
		if(response != null){
			response = response.toLowerCase();
			
			for(Url url : user.getUrlsToLookFor()){
				if(response.contains(url.getUrl().toLowerCase())){
					user.getMatches().add(new Match(
							url.getId(),
							newUrl.getId(),
							Trust.Trusted
							));
					addToMap = true;
				}
			}

            if(response.contains(user.getEmail().toLowerCase())){
                addToMap = true;
            }
            else if(response.contains(user.getName().toLowerCase() + " " + user.getFirstname().toLowerCase()) ||
                    response.contains(user.getFirstname().toLowerCase() + " " + user.getName().toLowerCase())){
                addToMap = true;
            }
			
			if(addToMap){
				user.getUrls().put(newUrl.getId(), newUrl);
				log.info("MATCH \\O/ --> " + newUrl + " : " + newUrl.getUrl());
			}
				
		}
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
