package com.vladkel.eFindMe.search.engine.model;

import java.util.List;

public class User {
	
	private String id;

	private String name;
	
	private String nickName;
	
	private List<Url> urlsToLookFor;
	
	private List<Url> trustedUrls;
	
	private List<Url> unknownUrls;
	
	private List<Url> badUrls;
	
	
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

	public List<Url> getTrustedUrl() {
		return trustedUrls;
	}

	public void setTrustedUrl(List<Url> trustedUrl) {
		this.trustedUrls = trustedUrl;
	}

	public List<Url> getTrustedUrls() {
		return trustedUrls;
	}

	public void setTrustedUrls(List<Url> trustedUrls) {
		this.trustedUrls = trustedUrls;
	}

	public List<Url> getUnknownUrls() {
		return unknownUrls;
	}

	public void setUnknownUrls(List<Url> unknownUrls) {
		this.unknownUrls = unknownUrls;
	}

	public List<Url> getBadUrls() {
		return badUrls;
	}

	public void setBadUrls(List<Url> badUrls) {
		this.badUrls = badUrls;
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
