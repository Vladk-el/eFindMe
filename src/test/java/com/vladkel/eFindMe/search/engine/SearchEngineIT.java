package com.vladkel.eFindMe.search.engine;

import java.util.ArrayList;
import java.util.List;

import com.vladkel.eFindMe.search.engine.conf.SearchEngineConfs;
import com.vladkel.eFindMe.search.engine.model.Url;
import com.vladkel.eFindMe.search.engine.model.User;

public class SearchEngineIT {

	public static void main(String [] args){
		
		//realTest();
		
		manualTest();
	}
	
	public static void realTest(){
		
		SearchEngine engine = new SearchEngine();
		
		String idUser = "0";
		
		engine.search(idUser); // search for Eliott Laversin
		
		System.out.println(engine.getConfs().getUsers().get(idUser));
	}
	
	public static void manualTest(){
		SearchEngineConfs sec = new SearchEngineConfs();
		
		List<Url> urls = new ArrayList<Url>();
			Url fb = new Url();
				fb.setId("0");
				fb.setName("Facebook Eliott Laversin");
				fb.setDescription("Page Facebook d'Eliott Laversin");
				fb.setUrl("https://www.facebook.com/eliott.laversin");
			Url github = new Url();
				github.setId("1");
				github.setName("GitHub Eliott Laversin");
				github.setDescription("Page GitHub d'Eliott Laversin");
				github.setUrl("https://github.com/Vladk-el");
		urls.add(fb);
		urls.add(github);
		
		User eliott = new User();
			 eliott.setName("Laversin");
			 eliott.setFirstname("Eliott");
			 eliott.setId("0");
			 eliott.setUrlsToLookFor(urls);
			 
		
		sec.getUsers().put(eliott.getId(), eliott);
		
		SearchEngine engine = new SearchEngine();
		engine.setConfs(sec);
		engine.search(eliott.getId());
		
		System.out.println(engine.getConfs().getUsers().get(eliott.getId()));
	}
	
}
