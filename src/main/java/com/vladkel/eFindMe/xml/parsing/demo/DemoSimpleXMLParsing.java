package com.vladkel.eFindMe.xml.parsing.demo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.xml.sax.XMLReader;

import com.vladkel.eFindMe.xml.parsing.demo.handler.DemoAnnuaireHandler;
import com.vladkel.eFindMe.xml.parsing.demo.model.Annuaire;
import com.vladkel.eFindMe.xml.parsing.reader.XmlReader;

public class DemoSimpleXMLParsing {
	
	/**
	 * Simple demo for fill an Annuaire object with a xml file
	 */
	
	private static final String XML_FILE = "src/main/resources/xml/annuaire.xml";
	
	
	public DemoSimpleXMLParsing(){
		super();
	}
	
	public String getXMLFileInString(){
		StringBuilder sb = new StringBuilder();
		try {
            List<String> lines = Files.readAllLines(Paths.get(getXmlFile()),
                    Charset.defaultCharset());
            for (String line : lines) {
                //System.out.println(line);
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return sb.toString();
	}
	
	
	public void demo(){
		
		String response = this.getXMLFileInString();
		
		XmlReader reader = new XmlReader(new DemoAnnuaireHandler());
		
		Annuaire annuaire = ((DemoAnnuaireHandler)reader.read(response)).getAnnuaire();

		annuaire.showYourself();
		
	}

	public static String getXmlFile() {
		return XML_FILE;
	}
}
