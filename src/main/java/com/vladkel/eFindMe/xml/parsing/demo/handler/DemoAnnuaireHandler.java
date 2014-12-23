package com.vladkel.eFindMe.xml.parsing.demo.handler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import com.vladkel.eFindMe.xml.parsing.demo.model.Annuaire;
import com.vladkel.eFindMe.xml.parsing.demo.model.Personne;

public class DemoAnnuaireHandler implements ContentHandler {
	
	/**
	 * Simple handler for class Annuaire
	 */
	
	private StringBuffer buffer;
	private List<String> parents = new ArrayList<String>();
	
	private Annuaire annuaire;
	private Personne personne;
	
	
	public Annuaire getAnnuaire(){
		return this.annuaire;
	}

	/**
	 * Start reading document
	 */
	public void startDocument() throws SAXException {
		annuaire = new Annuaire();
	}
	
	/**
	 * When you enter in a node
	 */
	public void startElement(String uri, String localName, String name, Attributes atts) throws SAXException {
		buffer = new StringBuffer(); // need to be here
		
		if(localName.equalsIgnoreCase("personne")){
			personne = new Personne();
			if(atts != null){
				if(atts.getLength() > 0){
					if(atts.getValue("id") != null){
						personne.setId(Integer.parseInt(atts.getValue("id")));
					}
				}
			}
		}
		
		parents.add(localName); // need to be here
	}
	
	/**
	 * When go out of a node ==> "buffer" contains the content of the node
	 */
	public void endElement(String uri, String localName, String qName) throws SAXException {
		parents.remove(localName); // need to be here
		
		if(localName.equalsIgnoreCase("nom")){
			personne.setNom(buffer.toString());
		}
		if(localName.equalsIgnoreCase("prenom")){
			personne.setPrenom(buffer.toString());
		}
		if(localName.equalsIgnoreCase("adresse")){
			personne.setAdresse(buffer.toString());
		}
		
		if(localName.equalsIgnoreCase("personne")){
			annuaire.getPersonnes().add(personne);
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
