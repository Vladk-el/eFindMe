package com.vladkel.eFindMe.xml.parsing.template;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class TemplateXMLHandler implements ContentHandler {
	
	/**
	 * This is only the struture you have to had.
	 * Don't edit this class, make a other one to test.
	 * See xml.parsing.demo for an complete example
	 */
	
	
	private StringBuffer buffer;
	private List<String> parents = new ArrayList<String>();


	/**
	 * Start reading document
	 */
	public void startDocument() throws SAXException {
		
	}
	
	/**
	 * When you enter in a node
	 */
	public void startElement(String uri, String localName, String name, Attributes atts) throws SAXException {
		buffer = new StringBuffer(); // need to be here
		
	}
	
	/**
	 * When go out of a node
	 */
	public void endElement(String uri, String localName, String qName) throws SAXException {
		parents.remove(localName); // need to be here
		
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
