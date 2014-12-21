package com.vladkel.eFindMe.search.engine.model;

import com.vladkel.eFindMe.bing.results.Trust;

public class Matches {
	
	private String idSource;
	
	private String idLink;
	
	private Trust trust;

	
	public Matches(String idSource, String idLink, Trust trust){
		super();
	}
	
	public Matches(){
		super();
	}
	
	public String getIdSource() {
		return idSource;
	}

	public void setIdSource(String idSource) {
		this.idSource = idSource;
	}

	public String getIdLink() {
		return idLink;
	}

	public void setIdLink(String idLink) {
		this.idLink = idLink;
	}

	public Trust getTrust() {
		return trust;
	}

	public void setTrust(Trust trust) {
		this.trust = trust;
	}
}
