package com.vladkel.eFindMe.search.engine.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class User {
		
	private String id;

	private String name;
	
	private String firstname;
	
	private List<Url> urlsToLookFor;
	
	private Map<String, Url> urls;
	
	private List<Match> matches;
	
	
	public User(){
		super();
		initLists();
	}
	
	public User(String name){
		super();
		setName(name);
		initLists();
	}
	
	public User(String name, String id){
		super();
		setName(name);
		setId(id);
		initLists();
	}
	
	public void initLists(){
		urlsToLookFor = new ArrayList<Url>();
		urls = new HashMap<String, Url>();
		setMatches(new ArrayList<Match>());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public List<Url> getUrlsToLookFor() {
		return urlsToLookFor;
	}

	public void setUrlsToLookFor(List<Url> urlsToLookFor) {
		this.urlsToLookFor = urlsToLookFor;
	}
	
	public Map<String, Url> getUrls() {
		return urls;
	}

	public void setUrls(Map<String, Url> urls) {
		this.urls = urls;
	}
	
	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	public void readSelfXMLFile(){
		/**
		 * Here we'll directly fill an user with his xml file
		 */
	}
	
	public void writeSelfXMLFile(){
		/**
		 * Here we'll directly save and update an user with his xml file
		 */
	}
	
	public String toString(){
		return new Gson().toJson(this);
	}


	
	

}
