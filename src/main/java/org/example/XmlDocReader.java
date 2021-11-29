package org.example;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XmlDocReader {

    private DocumentBuilder docBuilder;
    private Document document;

    private String pathDoc;

    public XmlDocReader(String pathDoc){
        this.pathDoc = pathDoc;
    }

    public String getPathDoc() {
        return pathDoc;
    }

    public void setPathDoc(String pathDoc) {
        this.pathDoc = pathDoc;
    }

    public void setDocBuilder() throws ParserConfigurationException, IOException, SAXException {
        docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }

    public Document getDocument() {
        return document;
    }

    public boolean isHasPath(){
        return !pathDoc.equals("");
    }

    public void setDocument() throws IOException, SAXException {
        if (isHasPath())
            document = docBuilder.parse(pathDoc);
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public DocumentBuilder getDocBuilder(){
        return docBuilder;
    }
}
