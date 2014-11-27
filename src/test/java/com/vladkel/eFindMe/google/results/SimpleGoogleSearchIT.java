package com.vladkel.eFindMe.google.results;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import com.google.gson.Gson;


public class SimpleGoogleSearchIT {

	public SimpleGoogleSearchIT(){};	
	
	public static void webAPI(){
		try{
			String address = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
			String query = "Caroline Dossal";
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
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello SimpleGoogleSearchIT !");
		
		webAPI();
		
	}

}
