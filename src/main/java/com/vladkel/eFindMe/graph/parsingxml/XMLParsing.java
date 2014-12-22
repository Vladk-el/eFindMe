package com.vladkel.eFindMe.graph.parsingxml;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.xml.sax.XMLReader;

import com.vladkel.eFindMe.graph.XmlDetailFile;

public class XMLParsing {
	
	private static String XML_FILE = "src/main/resources/xml/annuaire.xml";
	
	public XMLParsing(XmlDetailFile detailFile){
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
		return XML_FILE;
	}
}