package com.vladkel.eFindMe.graph.parsingxml;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.vladkel.eFindMe.search.engine.model.Match;
import com.vladkel.eFindMe.search.engine.model.Url;

public class GraphXML {
	
	private static GraphXML instance = null;

	public static GraphXML getInstance() {
		
		if(instance == null) {
			instance = new GraphXML();
	    }
		
		return instance;
	}
	
	private Map<String,Url> urls = new HashMap<String,Url>();
	private List<Match> matches = new ArrayList<Match>();
		
	public GraphXML(){
		super();
	}
	
	public Url getUrl(String indice)
	{
		return urls.get(indice);
	}
	
	public Match getMatch(String indiceUrl)
	{
		for(Match m : matches)
		{
			if(m.getIdLink().equals(indiceUrl))
				return m;
		}
		
		return null;
	}
	
	public Map<String,Url> getUrls() {
		return urls;
	}

	public void setUrls(Map<String,Url> urls) {
		this.urls = urls;
	}
	
	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
	
	public void getDataXml(int indiceUser)
	{
		XMLParsing parsing = new XMLParsing(indiceUser);	
		String response = parsing.getXMLFileInString();		
		XmlReader reader = new XmlReader(new GraphHandler());
		reader.read(response);
	}
	
	public void showYourself(){
		
		for(Entry<String, Url> url : urls.entrySet()) {
		    Url valueUrl = url.getValue();
		    
		    System.out.println("Id : " + valueUrl.getId());
		    System.out.println("Url : " + valueUrl.getUrl());
		}
		
		System.out.println("Matches : ");
		
		for(Match matche : matches)
		{
			System.out.println("Source : " + matche.getIdSource());
			System.out.println("Destination : " + matche.getIdLink());
		}
	}
}
