package com.vladkel.eFindMe.search.engine.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
	
	private String id;

	private String name;
	
	private String nickName;
	
	private List<Url> urlsToLookFor;
	
	private Map<String, Url> urls;
	
	private List<Matches> matches;
	
	
	public User(){
		super();
	}
	
	public User(String name){
		super();
		setName(name);
	}
	
	public User(String name, String id){
		super();
		setName(name);
		setId(id);
	}
	
	public void initLists(){
		urlsToLookFor = new ArrayList<Url>();
		urls = new HashMap<String, Url>();
		setMatches(new ArrayList<Matches>());
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	
	public List<Matches> getMatches() {
		return matches;
	}

	public void setMatches(List<Matches> matches) {
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


	
	

}
