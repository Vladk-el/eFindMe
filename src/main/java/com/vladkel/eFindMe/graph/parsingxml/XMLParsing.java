package com.vladkel.eFindMe.graph.parsingxml;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.xml.sax.XMLReader;

import com.vladkel.eFindMe.graph.XmlDetailFile;

public class XMLParsing {
	
	private static final String XML_FILE = "data/xml/users/";
	
	public XMLParsing(XmlDetailFile detailFile, int indiceUser){
		super();
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
	
	public static String getXmlFile() {
		// Treatement recup id user ...
		
		return XML_FILE + "/toto/results.xml";
	}
}