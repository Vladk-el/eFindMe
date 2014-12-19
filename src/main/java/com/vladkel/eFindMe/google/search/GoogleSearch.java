package com.vladkel.eFindMe.google.search;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.vladkel.eFindMe.google.results.GoogleResults;
import com.vladkel.eFindMe.google.results.Result;

public class GoogleSearch {
	
	private static final Logger log = LoggerFactory.getLogger(GoogleSearch.class);

	private int nbRequests;
	
	private String query;
	
	private String charset = "UTF-8";
	
	private boolean showLog = false;
	
	private static final String address = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&start=ToReplace&q=";

	private static final String toReplace = "ToReplace";
	
	/**
	 * Constructors
	 */
	
	public GoogleSearch(){
		super();
	}
	
	public GoogleSearch(int nbRequests, String query){
		super();
		this.nbRequests = nbRequests;
		this.query = query;
	}
	
	public GoogleSearch(int nbRequests, String query, boolean showLog){
		super();
		this.nbRequests = nbRequests;
		this.query = query;
		this.showLog = showLog;
	}
	
	
	/**
	 * Methods
	 */
	
	public List<Result> getResults(int nbRequests){
		this.nbRequests = nbRequests;
		return this.getResults();
	}
	
	public List<Result> getResults(int nbRequests, boolean showLog){
		setNbRequests(nbRequests);
		setShowLog(showLog);
		return this.getResults();
	}
	
	public List<Result> getResults(){
		
		List<Result> resultsList = new ArrayList<Result>();
		
		try{
			for (int r = 0; r < nbRequests; r = r + 4) {
				
				String address = this.getAddress().replaceAll(this.getToReplace(), Integer.toString(r));
		 
				URL url = new URL(address + 
								  URLEncoder.encode(query, charset)
							  );
				if(getShowLog()) log.info("url : " + url.toString());
				
				Reader reader = new InputStreamReader(url.openStream(), charset);
				GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);
		 
				int total = results.getResponseData().getResults().size();
				if(getShowLog()) log.info("Total : " + total);
				
				for(int i = 0; i <= total-1; i++){
					resultsList.add(results.getResponseData().getResults().get(i));
					// Show title and URL of each results
					if(getShowLog()){
						log.info("Title: " + results.getResponseData().getResults().get(i).getTitle());
						log.info("URL: " + results.getResponseData().getResults().get(i).getUrl() + "\n");
					}
					
				}
				
			}
			
			return resultsList;
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return resultsList;
	}
	
	
	
	
	
	
	/**
	 * Getters and setters
	 */
	
	public int getNbRequests() {
		return nbRequests;
	}

	public void setNbRequests(int nbRequests) {
		this.nbRequests = nbRequests;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public boolean isShowLog() {
		return showLog;
	}

	public boolean getShowLog() {
		return showLog;
	}
	
	public void setShowLog(boolean showLog) {
		this.showLog = showLog;
	}

	public String getAddress() {
		return address;
	}

	public String getToReplace() {
		return toReplace;
	}


	

}
