package com.vladkel.eFindMe.IHM.model;

public class UrlFindModel {
	private String url;
	private String trust;
	
	public UrlFindModel()
	{
		super();
	}
	
	public UrlFindModel(String url, String trust)
	{
		this.url = url;
		this.trust = trust;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTrust() {
		return trust;
	}

	public void setTrust(String trust) {
		this.trust = trust;
	}
}
