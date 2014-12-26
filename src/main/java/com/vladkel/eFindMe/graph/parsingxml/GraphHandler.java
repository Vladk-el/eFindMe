package com.vladkel.eFindMe.graph.parsingxml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import com.vladkel.eFindMe.search.engine.model.Match;
import com.vladkel.eFindMe.search.engine.model.Trust;
import com.vladkel.eFindMe.search.engine.model.Url;

public class GraphHandler implements ContentHandler {
	
	private String CurrentNode;
	
	private StringBuffer buffer;
	private List<String> parents = new ArrayList<String>();
		
	private Url link;
	private Match matche;
	
	public GraphHandler()
	{
		GraphXML.getInstance().getUrls().clear();
		GraphXML.getInstance().getMatches().clear();
	}
	
	public void startDocument() throws SAXException {
	}
	
	public void startElement(String uri, String localName, String name, Attributes atts) throws SAXException {
		buffer = new StringBuffer(); // need to be here
				
		if(localName.equalsIgnoreCase("link")){
			CurrentNode = "link";
			link = new Url();
		}
		
		if(localName.equalsIgnoreCase("edge")){
			CurrentNode = "edge";
			matche = new Match();
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		parents.remove(localName); // need to be here
	
		if(CurrentNode == "link")
		{
			if(localName.equalsIgnoreCase("id")){
				link.setId(buffer.toString());
			}
			
			if(localName.equalsIgnoreCase("name")){
				link.setName(buffer.toString());
			}
			
			if(localName.equalsIgnoreCase("url")){
				link.setUrl(buffer.toString());
			}
			
			if(localName.equalsIgnoreCase("link")){
				GraphXML.getInstance().getUrls().put(Integer.parseInt(link.getId()), link);
			}
		}
		
		if(CurrentNode == "edge")
		{	
			if(localName.equalsIgnoreCase("id")){
				matche.setIdSource(buffer.toString());
			}
			
			if(localName.equalsIgnoreCase("id1")){
				matche.setIdLink(buffer.toString());
			}
			
			if(localName.equalsIgnoreCase("trust")){
				
				if(buffer.toString().equalsIgnoreCase("trusted"))
					matche.setTrust(Trust.Trusted);
				
				else if(buffer.toString().equalsIgnoreCase("Unknown"))
					matche.setTrust(Trust.Unknown);
				
				else
					matche.setTrust(Trust.Bad);	
			}
			
			if(localName.equalsIgnoreCase("edge")){
				GraphXML.getInstance().getMatches().add(matche);
			}
		}
	}

	public void endDocument() throws SAXException {
		
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		String lecture = new String(ch, start, length);
		if (buffer != null)
			buffer.append(lecture);
	}
	
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