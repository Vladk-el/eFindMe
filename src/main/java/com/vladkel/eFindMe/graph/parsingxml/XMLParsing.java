package com.vladkel.eFindMe.graph.parsingxml;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.vladkel.eFindMe.search.engine.SearchEngine;
import com.vladkel.eFindMe.search.engine.model.User;

public class XMLParsing {
	
	private static final String XML_FILE = "data/xml/users/";
	private Integer indiceUser;
	
	
	public XMLParsing(Integer indiceUser){
		super();
		this.indiceUser = indiceUser;
	}
	
	public String getXMLFileInString(){
		StringBuilder sb = new StringBuilder();
		try {
            List<String> lines = Files.readAllLines(Paths.get(getXmlFile()),
                    Charset.defaultCharset());
            for (String line : lines) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return sb.toString();
	}
	
	public String getXmlFile() {		
		User user = SearchEngine.getInstance().getConfs().getUsers().get(indiceUser.toString());
		
		return XML_FILE + "/" + user.getFirstname().toLowerCase().replaceAll(" ", "") + "_" + user.getName().toLowerCase().replaceAll(" ", "") + "/results.xml";
	}
}