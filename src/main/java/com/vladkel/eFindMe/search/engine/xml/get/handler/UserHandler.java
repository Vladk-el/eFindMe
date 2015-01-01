package com.vladkel.eFindMe.search.engine.xml.get.handler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import com.vladkel.eFindMe.search.engine.model.Url;
import com.vladkel.eFindMe.search.engine.model.User;

public class UserHandler implements ContentHandler {
	
	private static final Logger log = LoggerFactory.getLogger(UserHandler.class);
	
	private StringBuffer buffer;
	private List<String> parents = new ArrayList<String>();
	
	private User user;
	private Url url;
	
	
	public User getUser(){
		return this.user;
	}

	/**
	 * Start reading document
	 */
	public void startDocument() throws SAXException {
		user = new User();
	}
	
	/**
	 * When you enter in a node
	 */
	public void startElement(String uri, String localName, String name, Attributes atts) throws SAXException {
		buffer = new StringBuffer(); // need to be here
		
		if(localName.equalsIgnoreCase("url")){
			if(parents.get(parents.size() - 1).equalsIgnoreCase("urlsToLookFor")){
				url = new Url();
			}
		}
		
		parents.add(localName); // need to be here
	}
	
	/**
	 * When go out of a node
	 */
	public void endElement(String uri, String localName, String qName) throws SAXException {
		parents.remove(localName); // need to be here
		
		if(parents.size() > 0){
			// User infos
			if(parents.get(parents.size() - 1).equalsIgnoreCase("user")){
				if(localName.equalsIgnoreCase("id")){
					user.setId(buffer.toString());
				}
				if(localName.equalsIgnoreCase("name")){
					user.setName(buffer.toString());
				}
				if(localName.equalsIgnoreCase("firstname")){
					user.setFirstname(buffer.toString());
				}
				if(localName.equalsIgnoreCase("email")){
					user.setEmail(buffer.toString());
				}
			}
			
			// Users urls
			if(parents.get(parents.size() - 1).equalsIgnoreCase("url")){
				if(localName.equalsIgnoreCase("id")){
					url.setId(buffer.toString());
				}
				if(localName.equalsIgnoreCase("name")){
					url.setName(buffer.toString());
				}
				if(localName.equalsIgnoreCase("description")){
					url.setDescription(buffer.toString());
				}
				if(localName.equalsIgnoreCase("url")){
					url.setUrl(buffer.toString());
				}
			}
			
			// Users urls
			if(parents.get(parents.size() - 1).equalsIgnoreCase("urlsToLookFor")){
				if(localName.equalsIgnoreCase("url")){
					user.getUrlsToLookFor().add(url);
				}
			}
		}
	}

	/**
	 * End of the reading
	 */
	public void endDocument() throws SAXException {
		
	}
	
	/**
	 * Allows you to know where you are. Don't modify this method
	 */
	public void characters(char[] ch, int start, int length) throws SAXException {
		String lecture = new String(ch, start, length);
		if (buffer != null)
			buffer.append(lecture);
	}
	
	
	
	/**
	 * Others are useless, don't look at it
	 */
	
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
		
	}

	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub
		
	}

}
