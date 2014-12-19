package com.vladkel.eFindMe.google.results;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.vladkel.eFindMe.google.search.GoogleSearch;


public class SimpleGoogleSearchIT {
	
	private static final Logger log = LoggerFactory.getLogger(SimpleGoogleSearchIT.class);

	public SimpleGoogleSearchIT(){};	
	
	public static void dirtyWebAPI(){
		try{
			for (int r = 0; r < 20; r = r + 4) {
				String address = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&start=" + r + "&q=";
				String query = "Diégo Da Costa";
				String charset = "UTF-8";
		 
				//URL url = new URL("https://www.google.fr/webhp?&ion=1&espv=2&ie=UTF-8#safe=off&q=eliott%20laversin");
				URL url = new URL(address + URLEncoder.encode(query, charset));
				System.out.println(url.toString());
				Reader reader = new InputStreamReader(url.openStream(), charset);
				GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);
		 
				int total = results.getResponseData().getResults().size();
				System.out.println("total: " + total);
		 
				// Show title and URL of each results
				for(int i = 0; i <= total-1; i++){
					System.out.println("Title: " + results.getResponseData().getResults().get(i).getTitle());
					System.out.println("URL: " + results.getResponseData().getResults().get(i).getUrl() + "\n");
				}
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void cleanWebAPI(){
		GoogleSearch gs = new GoogleSearch(50, "Diégo Da Costa", true);
		
		List<Result> results = gs.getResults();
		
		for(Result r : results){
			log.debug("Title: " + r.getTitle());
			log.debug("URL: " + r.getUrl() + "\n");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello SimpleGoogleSearchIT !");
		
		//dirtyWebAPI();
		
		new SimpleGoogleSearchIT().cleanWebAPI();
		
	}

}
