package com.vladkel.eFindMe.search.engine.model;


public class Match {
	
	private String idSource;
	
	private String idLink;
	
	private Trust trust;
	

	public Match(){
		super();
	}
	
	public Match(String idSource, String idLink, Trust trust){
		super();
		this.idSource = idSource;
		this.idLink = idLink;
		this.trust = trust;
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
	
	public void setTrust(String trust) {
		if(trust.equalsIgnoreCase("Trusted"))
			this.trust = Trust.Trusted;
		else if(trust.equalsIgnoreCase("Bad"))
			this.trust = Trust.Bad;
		else 
			this.trust = Trust.Unknown;
	}
}
