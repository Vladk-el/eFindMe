package com.vladkel.eFindMe.utils.ws.xml;

import java.io.StringReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XmlReader {

    private static final Logger log = LoggerFactory.getLogger(XmlReader.class);

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
            log.error("",e);
        }
        return null;
    }


}
