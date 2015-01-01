package com.vladkel.eFindMe.search.engine.xml.set;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.vladkel.eFindMe.search.engine.model.Match;
import com.vladkel.eFindMe.search.engine.model.Url;
import com.vladkel.eFindMe.search.engine.model.User;

public class SetUser {

	private static final Logger log = LoggerFactory.getLogger(SetUser.class);
	
	private final static String path = "data/xml/users/";
	
	private String fullPath;

	private User user;
	
	public SetUser(User user){
		this.setUser(user);
	}
	
	public boolean saveUser(){
		return createRepertory() && writeUser() && writeResults();
	}
	
	private boolean createRepertory(){
		try{
			fullPath = getPath() + 
					user.getFirstname().toLowerCase() + 
					"_" + 
					user.getName().toLowerCase().replaceAll(" ", "") + 
					"/";
			
			File dir = new File(fullPath);
			if(!dir.exists()){
				return dir.mkdirs();
			}
			else
				return true;
		}catch(Exception e){
			log.error("Eror on createRepertory() : ", e);
		}
		return false;
	}
	
	private boolean writeUser(){
		
		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("user");
			doc.appendChild(rootElement);
			
			// id
			Element id = doc.createElement("id");
			id.appendChild(doc.createTextNode(user.getId()));
			rootElement.appendChild(id);
			
			// name
			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode(user.getName()));
			rootElement.appendChild(name);
			
			// firstname
			Element firstname = doc.createElement("firstname");
			firstname.appendChild(doc.createTextNode(user.getFirstname()));
			rootElement.appendChild(firstname);
			
			// email
			Element email = doc.createElement("email");
			email.appendChild(doc.createTextNode(user.getEmail()));
			rootElement.appendChild(email);
			
			
			// urlsToLookFor
			Element urlsToLookFor = doc.createElement("urlsToLookFor");
			rootElement.appendChild(urlsToLookFor);
			
			for(Url myUrl : user.getUrlsToLookFor()){
				// url
				Element url = doc.createElement("url");
				urlsToLookFor.appendChild(url);
				
				// urlId
				Element urlId = doc.createElement("id");
				urlId.appendChild(doc.createTextNode(myUrl.getId()));
				url.appendChild(urlId);
				
				// urlId
				Element urlName = doc.createElement("name");
				urlName.appendChild(doc.createTextNode(myUrl.getName()));
				url.appendChild(urlName);
				
				// urlDescription
				Element urlDescription = doc.createElement("description");
				urlDescription.appendChild(doc.createTextNode(myUrl.getDescription()));
				url.appendChild(urlDescription);
				
				// urlUrl
				Element urlUrl = doc.createElement("url");
				urlUrl.appendChild(doc.createTextNode(myUrl.getUrl()));
				url.appendChild(urlUrl);
			}
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(getFullPath() + "user.xml"));
	 
			transformer.transform(source, result);
	 
			System.out.println("File user.xml saved!");
			
			return true;
		}catch(Exception e){
			log.error("Error on writting user's files : ", e);
		}
		
		return false;
	}
	
	private boolean writeResults(){
		
		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("results");
			doc.appendChild(rootElement);
			
			// links
			Element links = doc.createElement("links");
			rootElement.appendChild(links);
			
			for(Url myUrl : user.getUrls().values()){
				// url
				Element link = doc.createElement("link");
				links.appendChild(link);
				
				// id
				Element id = doc.createElement("id");
				id.appendChild(doc.createTextNode(myUrl.getId()));
				link.appendChild(id);
				
				// name
				Element name = doc.createElement("name");
				name.appendChild(doc.createTextNode(myUrl.getName()));
				link.appendChild(name);
				
				// url
				Element url = doc.createElement("url");
				url.appendChild(doc.createTextNode(myUrl.getUrl()));
				link.appendChild(url);
			}
			
			// graph
			Element graph = doc.createElement("graph");
			rootElement.appendChild(graph);
			
			for(Match match : user.getMatches()){
				// match
				Element edge = doc.createElement("edge");
				graph.appendChild(edge);
				
				// id
				Element id = doc.createElement("id");
				id.appendChild(doc.createTextNode(match.getIdSource()));
				edge.appendChild(id);
				
				// id1
				Element name = doc.createElement("name");
				name.appendChild(doc.createTextNode(match.getIdLink()));
				edge.appendChild(name);
				
				// trust
				Element url = doc.createElement("url");
				url.appendChild(doc.createTextNode(match.getTrust().toString()));
				edge.appendChild(url);

			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(getFullPath() + "results.xml"));
	 
			transformer.transform(source, result);
	 
			System.out.println("File results.xml saved!");
			
			return true;
		}catch(Exception e){
			log.error("Error on writting user's files : ", e);
		}
		
		return false;
	}

	
	/**
	 * Getters and setters
	 */
	
	public static String getPath() {
		return path;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
