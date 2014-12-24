package com.vladkel.eFindMe.search.engine.xml.get;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vladkel.eFindMe.search.engine.model.Match;
import com.vladkel.eFindMe.search.engine.model.Url;
import com.vladkel.eFindMe.search.engine.model.User;
import com.vladkel.eFindMe.search.engine.xml.get.handler.MatchesHandler;
import com.vladkel.eFindMe.search.engine.xml.get.handler.UrlsHandler;
import com.vladkel.eFindMe.search.engine.xml.get.handler.UserHandler;
import com.vladkel.eFindMe.xml.parsing.reader.XmlReader;

public class GetUser {

	private static final Logger log = LoggerFactory.getLogger(GetUser.class);
	
	private static final String location = "data/xml/users/";
	
	private String fullName;
	
	
	/**
	 * @param fullName ==> user.name + "_" + user.firstname
	 */
	public GetUser(String fullName){
		this.fullName = fullName;
	}
	
	
	/**
	 * Methods
	 */
	
	public User getUser(){
		String response = this.getXMLFileInString("/user.xml");
		
		XmlReader reader = new XmlReader(new UserHandler());
		
		User user = ((UserHandler)reader.read(response)).getUser();
		
		
		response = this.getXMLFileInString("/results.xml");
		
		reader = new XmlReader(new MatchesHandler());
		
		List<Match> matches = ((MatchesHandler)reader.read(response)).getMatches();
		
		user.setMatches(matches);
		
		
		response = this.getXMLFileInString("/results.xml");
		
		reader = new XmlReader(new UrlsHandler());
		
		Map<String, Url> urls = ((UrlsHandler)reader.read(response)).getUrls();
		
		user.setUrls(urls);
		
		return user;
	}
	
	public String getXMLFileInString(String file, boolean doLog){
		StringBuilder sb = new StringBuilder();
		try {
            List<String> lines = Files.readAllLines(Paths.get(getLocation() + file),
                    Charset.defaultCharset());
            for (String line : lines) {
                if(doLog)
                	log.info(line);
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return sb.toString();
	}
	
	public String getXMLFileInString(String file){
		return getXMLFileInString(file, false);
	}
	
	
	/**
	 * Getters and setters
	 */

	public String getLocation() {
		return location  + getFullName();
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
}
