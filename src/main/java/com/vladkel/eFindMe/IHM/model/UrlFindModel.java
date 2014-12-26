package com.vladkel.eFindMe.IHM.model;

import java.util.ArrayList;
import java.util.List;

public class UrlFindModel {
	private String url;
	private List<String> keywords;
	
	public UrlFindModel()
	{
		keywords = new ArrayList<String>();
	}
	
	public UrlFindModel(String url,List<String> keyWords)
	{
		this.url = url;
		this.keywords = keyWords;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public List<String> getKeyWords() {
		return keywords;
	}

	public void setKeyWords(List<String> keywords) {
		this.keywords = keywords;
	}
}
