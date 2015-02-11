package com.vladkel.eFindMe.search.engine.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.vladkel.eFindMe.search.engine.xml.set.SetUser;

public class User {
		
	private String id;

	private String name;
	
	private String firstname;
	
	private String email;

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
	
	public void setEmail(String email) {
		this.email = email;
	}


	public String getEmail() {
		return email;
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

	public void readSelfXMLFiles(){
		/**
		 * Here we'll directly fill an user with his xml file
		 */
	}
	
	public void writeSelfXMLFiles(){
		new SetUser(this).saveUser();
	}
	
	public void removeSelfXMLFiles(){
		new SetUser(this).removeUser();
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		sb.append("id : " + this.id + "\n");
		sb.append("name : " + this.name + "\n");
		sb.append("firstname : " + this.firstname + "\n");
		sb.append("email : " + this.email + "\n");
		
		sb.append("urlsToLookFor : \n");
		for(Url url : urlsToLookFor){
			sb.append("\t" + new Gson().toJson(url) + "\n");
		}
		
		sb.append("urls : \n");
		for(Url url : urls.values()){
			sb.append("\t" + new Gson().toJson(url) + "\n");
		}
		
		sb.append("matches : \n");
		for(Match macth : matches){
			sb.append("\t" + new Gson().toJson(macth) + "\n");
		}

		//return new Gson().toJson(this);
		return sb.toString();
	}
	
	public static void createUser(User user)
	{
		new SetUser(user).saveUser();
		// Demo on src/test/java/com.vladkel.eFindMe.xml.parsing.user.SetUserIt
	}
}
