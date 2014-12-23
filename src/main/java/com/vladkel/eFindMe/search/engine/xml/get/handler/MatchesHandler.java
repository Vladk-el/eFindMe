package com.vladkel.eFindMe.search.engine.xml.get.handler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import com.vladkel.eFindMe.search.engine.model.Match;

public class MatchesHandler implements ContentHandler {
	
	private static final Logger log = LoggerFactory.getLogger(MatchesHandler.class);
	
	private StringBuffer buffer;
	private List<String> parents = new ArrayList<String>();
	
	private List<Match> matches;
	private Match match;
	
	
	public List<Match> getMatches(){
		return this.matches;
	}

	/**
	 * Start reading document
	 */
	public void startDocument() throws SAXException {
		matches = new ArrayList<Match>();
	}
	
	/**
	 * When you enter in a node
	 */
	public void startElement(String uri, String localName, String name, Attributes atts) throws SAXException {
		buffer = new StringBuffer(); // need to be here
		
		if(localName.equalsIgnoreCase("edge")){
			if(parents.get(parents.size() - 1).equalsIgnoreCase("graph")){
				match = new Match();
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
			if(parents.get(parents.size() - 1).equalsIgnoreCase("edge")){
				if(localName.equalsIgnoreCase("id")){
					match.setIdSource(buffer.toString());
				}
				if(localName.equalsIgnoreCase("id1")){
					match.setIdLink(buffer.toString());
				}
				if(localName.equalsIgnoreCase("trust")){
					match.setTrust(buffer.toString());
				}
			}
			
			// Users urls
			if(parents.get(parents.size() - 1).equalsIgnoreCase("graph")){
				if(localName.equalsIgnoreCase("edge")){
					matches.add(match);
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
