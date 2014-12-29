package com.vladkel.eFindMe.xml.parsing.reader;

import java.io.StringReader;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XmlReader {

    private ContentHandler contentHandler;

    public XmlReader(ContentHandler handler) {
        contentHandler = handler;
    }

    public ContentHandler read(String content) {

        if(content==null)
            return null;

        XMLReader reader;
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(contentHandler);
            reader.parse(new InputSource(new StringReader(content)));
            return contentHandler;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}